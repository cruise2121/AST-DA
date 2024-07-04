
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MovieGenreUI {

    private MovieManager movieManager;
    private JFrame frame;
    private JComboBox<String> genreComboBox;
    private JTextArea movieListArea;

    public MovieGenreUI() {
        movieManager = new MovieManager();

        frame = new JFrame("Movie Genre Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel genreLabel = new JLabel("Select Genre:");
        topPanel.add(genreLabel);

        String[] genres = {"Action", "Comedy", "Drama", "Horror", "Sci-Fi", "Romance", "Adventure"};
        genreComboBox = new JComboBox<>(genres);
        genreComboBox.addActionListener(new GenreComboBoxListener());
        topPanel.add(genreComboBox);

        movieListArea = new JTextArea();
        movieListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(movieListArea);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class GenreComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedGenre = (String) genreComboBox.getSelectedItem();
            List<Movie> movies = movieManager.getMoviesByGenre(selectedGenre);
            movieListArea.setText("");
            for (Movie movie : movies) {
                movieListArea.append(movie.getTitle() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieGenreUI::new);
    }
}
