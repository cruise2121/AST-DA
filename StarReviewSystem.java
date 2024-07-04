import java.util.HashMap;
import java.util.Map;

public class StarReviewSystem {

    private Map<String, Double> movieRatings;
    private Map<String, Integer> ratingCounts;

    public StarReviewSystem() {
        movieRatings = new HashMap<>();
        ratingCounts = new HashMap<>();
    }

    public void addRating(String movie, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        movieRatings.put(movie, movieRatings.getOrDefault(movie, 0.0) + rating);
        ratingCounts.put(movie, ratingCounts.getOrDefault(movie, 0) + 1);
    }

    public double getAverageRating(String movie) {
        if (!movieRatings.containsKey(movie)) {
            return 0.0;
        }
        return movieRatings.get(movie) / ratingCounts.get(movie);
    }

    public Map<String, Double> getMovieRatings() {
        return movieRatings;
    }
}
