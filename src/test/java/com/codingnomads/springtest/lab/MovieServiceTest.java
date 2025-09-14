package com.codingnomads.springtest.lab;


import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.repository.MovieRepository;
import com.codingnomads.springtest.lab.service.MovieServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieServiceTest {

    @Mock
    MovieRepository movieRepo;

    @InjectMocks
    MovieServiceImpl movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    public void testGetAllMovies() {
        List<Movie> expectedMovies = Arrays.asList(
                Movie.builder().name("The Shawshank Redemption").rating(9.3).build(),
                Movie.builder().name("The Pursuit of Happyness").rating(8.0).build());

        when(movieRepo.findAll())
                .thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.getAllMovies();
        assertThat(expectedMovies).isEqualTo(actualMovies);
    }

    @Test
    @Order(2)
    public void testGetAllMoviesWithLargeMinRating() {
        double minRating = 9.0;
        List<Movie> expectedMovies = Collections.singletonList(
                Movie.builder().name("The Shawshank Redemption").rating(9.3).build());

        when(movieRepo.findByRatingGreaterThanEqual(minRating))
                .thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.getMoviesWithMinRating(minRating);
        assertThat(expectedMovies).isEqualTo(actualMovies);

    }

    @Test
    @Order(3)
    public void testGetAllMoviesWithSmallMinRating() {
        // re-test with small rating
        double minRating = 1.0;
        List<Movie> expectedMovies = Arrays.asList(
                Movie.builder().name("The Shawshank Redemption").rating(9.3).build(),
                Movie.builder().name("The Pursuit of Happyness").rating(8.0).build());

        when(movieRepo.findByRatingGreaterThanEqual(minRating))
                .thenReturn(expectedMovies);

        List<Movie> actualMovies = movieService.getMoviesWithMinRating(minRating);
        assertThat(expectedMovies).isEqualTo(actualMovies);
    }

}
