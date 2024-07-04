
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendationSystem {

    private StarReviewSystem reviewSystem;

    public RecommendationSystem(StarReviewSystem reviewSystem) {
        this.reviewSystem = reviewSystem;
    }

    public List<String> getRecommendedMovies(double threshold) {
        List<String> recommendations = new ArrayList<>();

        for (Map.Entry<String, Double> entry : reviewSystem.getMovieRatings().entrySet()) {
            String movie = entry.getKey();
            double averageRating = reviewSystem.getAverageRating(movie);

            if (averageRating >= threshold) {
                recommendations.add(movie);
            }
        }

        return recommendations;
    }
}
