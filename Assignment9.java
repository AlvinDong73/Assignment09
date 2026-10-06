/**
 * Name:
 * Date:
 */
public class Assignment9 {

    public static void main(String[] args) {
        MovieTracker tracker = new MovieTracker();

        // Create a few movies
        Movie inception = new Movie("Inception", 148, 2010, "PG-13");
        Movie up = new Movie("Up", 96,  2009, "PG");
        Movie jaws = new Movie("Jaws", 124, 1975, "PG");
        Movie matrix = new Movie("The Matrix", 136, 1999, "R");
        Movie frozen = new Movie("Frozen", 102, 2013, "PG");

        // Record which movies each user has watched
        tracker.addMovie("alice", inception);
        tracker.addMovie("alice", up);
        tracker.addMovie("alice", matrix);

        tracker.addMovie("bob", jaws);
        tracker.addMovie("bob", matrix);
        
        tracker.addMovie("jane", frozen);
        tracker.addMovie("jane", frozen);

        // getMovies returns the set of movies for one user
        System.out.println("=== getMovies ===");
        System.out.println("alice: " + tracker.getMovies("alice"));
        System.out.println("bob: " + tracker.getMovies("bob"));

        // Jane does not have any duplicate movies
        System.out.println("jane: " + tracker.getMovies("jane"));

        // hasWatched returns whether or not a user has seen a particular movie
        System.out.println("=== hasWatched ===");
        System.out.println("Has alice seen Inception? " + tracker.hasWatched("alice", inception));
        System.out.println("Has alice seen Frozen? " + tracker.hasWatched("alice", frozen));
        System.out.println("Has bob seen Inception? " + tracker.hasWatched("bob", inception));
    }
}
