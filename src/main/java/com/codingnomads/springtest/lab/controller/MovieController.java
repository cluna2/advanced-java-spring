/* CodingNomads (C)2024 */
package com.codingnomads.springtest.lab.controller;

import com.codingnomads.springtest.lab.entity.Movie;
import com.codingnomads.springtest.lab.service.MovieService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class MovieController {

    private MovieService movieService;

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/rating")
    public ResponseEntity<?> getMoviesWithMinRating(@RequestParam("minRating") double minRating) {
        try {
            List<Movie> matchingMovies = movieService.getMoviesWithMinRating(minRating);
            return ResponseEntity.ok(matchingMovies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
