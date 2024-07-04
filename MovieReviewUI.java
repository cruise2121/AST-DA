
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MovieReviewUI {

    private StarReviewSystem reviewSystem;
    private RecommendationSystem recommendationSystem;

    private JFrame frame;
    private JTextField movieField;
    private JTextField ratingField;
    private JTextArea outputArea;
    private JTextField thresholdField;

    public MovieReviewUI() {
        reviewSystem = new StarReviewSystem();
        recommendationSystem = new RecommendationSystem(reviewSystem);

        frame = new JFrame("Movie Review and Recommendation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        JLabel movieLabel = new JLabel("Movie Name:");
        movieField = new JTextField();

        JLabel ratingLabel = new JLabel("Rating (1-5):");
        ratingField = new JTextField();

        JButton addButton = new JButton("Add Rating");
        addButton.addActionListener(new AddButtonListener());

        JLabel thresholdLabel = new JLabel("Recommendation Threshold:");
        thresholdField = new JTextField();

        JButton recommendButton = new JButton("Get Recommendations");
        recommendButton.addActionListener(new RecommendButtonListener());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputPanel.add(movieLabel);
        inputPanel.add(movieField);
        inputPanel.add(ratingLabel);
        inputPanel.add(ratingField);
        inputPanel.add(addButton);
        inputPanel.add(new JLabel());  // Empty cell
        inputPanel.add(thresholdLabel);
        inputPanel.add(thresholdField);
        inputPanel.add(recommendButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String movie = movieField.getText();
            int rating;

            try {
                rating = Integer.parseInt(ratingField.getText());
                reviewSystem.addRating(movie, rating);
                outputArea.append("Added rating " + rating + " for movie " + movie + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid rating. Please enter a number between 1 and 5.\n");
            } catch (IllegalArgumentException ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        }
    }

    private class RecommendButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double threshold;

            try {
                threshold = Double.parseDouble(thresholdField.getText());
                List<String> recommendations = recommendationSystem.getRecommendedMovies(threshold);
                outputArea.append("Recommended Movies (threshold: " + threshold + "):\n");

                for (String movie : recommendations) {
                    outputArea.append(movie + "\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid threshold. Please enter a valid number.\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieReviewUI::new);
    }
}
