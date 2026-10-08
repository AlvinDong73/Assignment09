# `HashSet` vs `ArrayList`

If we kept track of the movies that a user watched via an ArrayList, then
the collection would not automatically prevent duplicates from being added
to the movies that a user has watched. This is because HashSets are sets,
which ignore any duplicate elements that would have been added to them.
ArrayLists, on the other hand, allow duplicate items.

The advantage to using HashSets over ArrayLists is that you don't have to
preemptively check if a given movie is in the collection of movies a user has
seen before adding it. The data structure ensures that no duplicates are
present in this collection. HashSets are also significantly faster than
ArrayLists when it comes to checking if a user has seen a particular movie,
since HashSets utilize hashing to check for containment in O(1) time. Checking
if an ArrayList contains a certain movie would be O(n) time, which gets slower
if a user has seen a lot of movies.

However, a downside to using a HashSet is that the data structure does not
know the order in which elements were added to it. If we wanted to know the
order in which a user saw the movies they saw and we cared about if a user
saw a movie more than once, then an ArrayList would be appropriate. We lose
the speed of checking if a user has seen a particular movie via hashing,
but we can answer questions we would not have been able to otherwise, such
as "What was the first movie a user saw?".

# `HashMap<String, HashSet<Movie>>` vs `HashMap<Movie, HashSet<String>>`

In this program, we represented the movies a user has watched by
using a HashMap that maps a username to a set of movies they have seen.

This decision makes it very convenient to find what movies a particular
user has seen. However, in order to find what users have seen a given
movie, we would have to iterate over the keys of this HashMap and check
if the given movie is in the set of movies they have seen. One hypothetical
implementation of such a method would look like
```java
public HashSet<String> getSeen(Movie movie) {
    HashSet<String> users = new HashSet<>();
    for (String user : watchedmovies.keySet()) {
        if (watchedMovies.get(user).contains(movie)) {
            users.add(user);
        }
    }
    return users;
}
```
This is a lot more complex compared to `movieTracker.getmovies()`. The `getMovies`
method is also significantly faster, since in order to get all the users that have
seen a particular movie, you have to iterate over all the keys of the HashMap,
get the set of movies a user has seen, check if the given movie is in the set,
then add it to a set of users that will be returned after the end of the loop.

In contrast, using a HashMap that maps a movie to all users that have seen it
could allow us to answer this question faster and more simply. We would just
need to get the set of users that have seen the given movie, which is convenient
since we would be using a HashMap whose keys are movies. This has a tradeoff, as
the previous question of what movies has a particular user seen now becomes
more difficult. If we chose to represent the user data this way, then
`getMovies` would look similar to the hypothetical `getSeen` method above,
whereas `getSeen` would look like the `getMovies` method currently in MovieTracker.java.

It is important to note that `hasWatched` is not significantly affected by
this decision, since we could get the set of users that have seen the given
movie and then check if the given user is in this set.

If the efficiency of both `getMovies` and `getSeen` are vital, then we could come to a
compromise and give the MovieTracker both a `HashMap<String, HashSet<Movie>> userToMovies`
and a `HashMap<Movie, HashSet<String>> movieToUsers` field. However, we are storing more data
than is necessary to answer either question. Furthermore, we introduce complexity elsewhere
in the program and in future additions, since any new operation that changes the set of movies
a user has seen will have to modify both `userToMovies` and `movieToUsers` in order to ensure
that code relying on `getMovies` and `getSeen` is accurate.
