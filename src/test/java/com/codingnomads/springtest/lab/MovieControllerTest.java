/* CodingNomads (C)2024 */
package com.codingnomads.springtest.lab;

import com.codingnomads.springtest.TestUtil;
import com.codingnomads.springtest.lab.controller.MovieController;
import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.repository.MovieRepository;
import com.codingnomads.springtest.lab.service.MovieServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// entire spring application needed to create MovieRepository bean for integration tests
// otherwise @WebMvcTest with @ContextConfiguration is fine for the unit tests
@SpringBootTest(
        classes = SpringTestLab.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class MovieControllerTest {

    // real repo to clear and populate db for non-mocking tests
    @Autowired
    MovieRepository realRepo;

    // spy of implementing class needed to be able to call real methods of the concrete class
    // mockito does not allow calling real methods on abstract classes
    @SpyBean
    MovieServiceImpl mockMovieService;

    // manually create controller to control MockMvCRequests and prevent null errors for repo method calls
    @InjectMocks
    MovieController movieController;

    @Autowired
    TestRestTemplate testRestTemplate;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // initialize mocks and inject
        MockitoAnnotations.openMocks(this);
        // use standalone setup to get control over controller instantiation and initialization being tested
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        when(mockMovieService.getAllMovies()).thenCallRealMethod();
        when(mockMovieService.getMoviesWithMinRating(anyDouble())).thenCallRealMethod();
    }


    @Test
    @Order(1)
    public void testGetAllMoviesFailure() throws Exception {
        realRepo.deleteAll();
        mockMvc
                .perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));

        if (realRepo.findAll().isEmpty()) {
            realRepo.saveAll(Arrays.asList(
                    Movie.builder().name("The Shawshank Redemption").rating(9.3).build(),
                    Movie.builder().name("The Pursuit of Happyness").rating(8.0).build()));
        }

    }

    @Test
    @Order(2)
    public void testGetAllMoviesSuccess() throws Exception {
        mockMvc
                .perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"));
    }

    @Test
    @Order(3)
    public void testGetAllMoviesSuccessMockService() throws Exception {
        List<Movie> movies = Arrays.asList(
                Movie.builder().name("The Shawshank Redemption").rating(9.3).build(),
                Movie.builder().name("The Pursuit of Happyness").rating(8.0).build());

        when(mockMovieService.getAllMovies())
                .thenReturn(movies);

        mockMvc
                .perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("The Shawshank Redemption"))
                .andExpect(jsonPath("$[1].name").value("The Pursuit of Happyness"));
    }

    @Test
    @Order(4)
    public void testGetAllMoviesSuccessWithObject() throws Exception {

        byte[] response = mockMvc
                .perform(get("/all"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        List<Movie> returnedMovies = Arrays.asList(TestUtil.convertJsonBytesToObject(response, Movie[].class));

        assertThat(returnedMovies.size()).isEqualTo(2);
        assertThat(returnedMovies.get(0).getName()).isEqualTo("The Shawshank Redemption");
        assertThat(returnedMovies.get(1).getName()).isEqualTo("The Pursuit of Happyness");
    }

    @Test
    @Order(5)
    public void testGetMoviesWithMinRatingSuccess() throws Exception {

        double minRating = 9.0;
        ParameterizedTypeReference<List<Movie>> typeRef = new ParameterizedTypeReference<List<Movie>>() {};
        ResponseEntity<List<Movie>> response = testRestTemplate.exchange(
                "/rating?minRating=" + minRating, HttpMethod.GET, null, typeRef);
        List<Movie> returnedMovies = response.getBody();

        Assertions.assertNotNull(returnedMovies);
        assertThat(returnedMovies.size()).isEqualTo(1);
        assertThat(returnedMovies.get(0).getName()).isEqualTo("The Shawshank Redemption");


        byte[] byteResponse = mockMvc
                .perform(get("/rating")
                        .queryParam("minRating", String.valueOf(9.0)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsByteArray();

        List<Movie> otherMovies = Arrays.asList(TestUtil.convertJsonBytesToObject(byteResponse, Movie[].class));

        Assertions.assertNotNull(otherMovies);
        assertThat(otherMovies.size()).isEqualTo(1);
        assertThat(otherMovies.get(0).getName()).isEqualTo("The Shawshank Redemption");
    }

    @Test
    @Order(6)
    public void testGetMoviesWithMinRatingFailure() throws Exception {

        String expectedErrorMessage = "Custom exception message.";
        when(mockMovieService.getMoviesWithMinRating(anyDouble()))
                .thenThrow(new RuntimeException(expectedErrorMessage));

        MockHttpServletResponse response = mockMvc
                .perform(get("/rating")
                        .queryParam("minRating", String.valueOf(9.0)))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getResponse();

        String errorMessage = response.getContentAsString();

        assertThat(errorMessage).isNotNull();
        assertThat(errorMessage).isEqualTo(expectedErrorMessage);

    }

}
