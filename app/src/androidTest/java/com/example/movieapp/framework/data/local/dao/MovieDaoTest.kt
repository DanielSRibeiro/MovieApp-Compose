package com.example.movieapp.framework.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.movieapp.framework.data.local.MovieDatabase
import com.example.movieapp.framework.data.local.entity.MovieEntity
import com.google.common.base.Verify
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class MovieDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: MovieDatabase

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun should_return_empty_list_when_there_is_no_movie() = runTest {
        // Arrange - Nothing

        // Act
        val movies = database.movieDao().getMovies().first()

        // Assert
        Assert.assertEquals(0, movies.size)
    }

    @Test
    fun should_return_list_of_order_by_id() = runTest {
        // Arrange

        val movies = listOf(
            MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1"),
            MovieEntity(movieId = 2, title = "Homem de Ferro", imageUrl = "Url2"),
            MovieEntity(movieId = 3, title = "Homem de Ferro", imageUrl = "Url3"),
            MovieEntity(movieId = 4, title = "Homem de Ferro", imageUrl = "Url4"),
        )

        // Act
        database.movieDao().insertMovies(movies)

        val result = database.movieDao().getMovies().first()

        // Assert
        Assert.assertEquals(4, result.size)
    }

    @Test
    fun should_return_correct_movie_list_by_id() = runTest {
        // Arrange
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")
        database.movieDao().insertMovie(movieEntity)
        val movies = database.movieDao().getMovies().first()
        val movieClick = movies[0]

        // Act
        val movieId = database.movieDao().getMovie(movieClick.movieId)

        // Assert
        Assert.assertEquals(movieId?.title, movieEntity.title)
    }

    @Test
    fun should_insert_movies_successfully() = runTest {
        // Arrange
        val movies = listOf(
            MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1"),
            MovieEntity(movieId = 2, title = "Homem de Ferro", imageUrl = "Url2"),
            MovieEntity(movieId = 3, title = "Homem de Ferro", imageUrl = "Url3"),
            MovieEntity(movieId = 4, title = "Homem de Ferro", imageUrl = "Url4"),
        )

        // Act
        database.movieDao().insertMovies(movies)

        // Assert
        val insertedMovies = database.movieDao().getMovies().first()

        Assert.assertEquals(4, insertedMovies.size)
        Verify.verify(insertedMovies.containsAll(movies))
    }

    @Test
    fun should_insert_a_movie_successfully() = runTest {
        // Arrange
        val movie = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")

        // Act
        database.movieDao().insertMovie(movie)

        // Assert
        val moviesLocal = database.movieDao().getMovies().first()

        Assert.assertEquals(moviesLocal[0].movieId, movie.movieId)
    }


    @Test
    fun should_return_favorite_movie_when_movie_is_marked_as_favorite() = runTest {
        // Arrange
        val movie = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")

        // Act
        database.movieDao().insertMovie(movie)

        // Assert
        val isFavorite = database.movieDao().isFavorite(movie.movieId)

        Assert.assertNotEquals(isFavorite, null)
    }

    @Test
    fun should_return_not_favorite_movie_when_movie_is_unchecked() = runTest {
        // Arrange
        val movie = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")

        // Act
        database.movieDao().insertMovie(movie)

        // Assert
        val isFavorite = database.movieDao().isFavorite(2)

        Assert.assertEquals(isFavorite, null)
    }

    @Test
    fun should_update_a_movie_successfully() = runTest {
        // Arrange
        val movie = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")
        database.movieDao().insertMovie(movie)
        val allMovies = database.movieDao().getMovies().first()
        val updateMovie = allMovies[0].copy(title = "Homem de Ferro 2")

        // Act
        database.movieDao().insertMovie(updateMovie)

        // Assert
        val movies = database.movieDao().getMovies().first()
        Assert.assertEquals(movies[0].title, updateMovie.title)
    }

    @Test
    fun should_delete_a_movie_successfully() = runTest {
        // Arrange
        val movieEntity = MovieEntity(movieId = 1, title = "Homem de Ferro", imageUrl = "Url1")
        database.movieDao().insertMovie(movieEntity)
        val allMovies = database.movieDao().getMovies().first()

        // Act
        database.movieDao().deleteMovie(allMovies[0])

        // Assert
        val movie = database.movieDao().getMovie(allMovies[0].movieId)
        Assert.assertEquals(movie, null)
    }
}