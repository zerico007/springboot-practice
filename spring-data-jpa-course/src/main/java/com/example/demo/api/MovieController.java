package com.example.demo.api;

import com.example.demo.models.Movie;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/movies")
@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public String addMovie(@Valid @NonNull @RequestBody Movie movie) throws SQLException {
        return movieService.insertMovie(movie);
    }

    @GetMapping(path = "{id}")
    public Optional<Movie> getMovieById(@PathVariable("id") Integer id) {
        return movieService.getMovieById(id);
    }

    @GetMapping("/search")
    public List<Movie> getMovieByTitle(@RequestParam("title") String title) {
        return movieService.getMovieByTitle(title).orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateMovie(@PathVariable("id") Integer id, @Valid @NonNull @RequestBody Movie movieToUpdate) {
        movieService.updateMovie(id, movieToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
    }
}
