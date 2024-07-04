
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieManager {

    private List<Movie> movies;

    public MovieManager() {
        movies = new ArrayList<>();
        initializeMovies();
    }

    private void initializeMovies() {
        movies.add(new Movie("salaar", "Action"));
        movies.add(new Movie("john wick", "Action"));
        movies.add(new Movie("vikram", "Action"));
        movies.add(new Movie("hangover", "Comedy"));
        movies.add(new Movie("dictator", "Comedy"));
        movies.add(new Movie("jathiratnalu", "Comedy"));
        movies.add(new Movie("jersey", "Drama"));
        movies.add(new Movie("bahubali", "Drama"));
        movies.add(new Movie("IABA", "Drama"));
        movies.add(new Movie("hereditary", "Horror"));
        movies.add(new Movie("conjuring", "Horror"));
        movies.add(new Movie("it", "Horror"));
        movies.add(new Movie("kalki", "Sci-Fi"));
        movies.add(new Movie("Interstellar", "Sci-Fi"));
        movies.add(new Movie("Inception", "Sci-Fi"));
        movies.add(new Movie("before sunrise", "Romance"));
        movies.add(new Movie("fidaa", "Romance"));
        movies.add(new Movie("arjun reddy", "Romance"));
        movies.add(new Movie("indiana jones", "Adventure"));
        movies.add(new Movie("tenet", "Adventure"));
    }

    public List<Movie> getMoviesByGenre(String genre) {
        return movies.stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
