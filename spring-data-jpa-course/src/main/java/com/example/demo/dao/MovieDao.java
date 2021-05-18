package com.example.demo.dao;

import com.example.demo.models.Movie;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MovieDao {

    List<Movie> getAllMovies();

    String insertMovie(Movie movie) throws SQLException;

    void deleteMovie(Integer id);

    Optional<Movie> selectMovieById(Integer id);

    Optional<List<Movie>> selectMovieByTitle(String title);

    void updateMovie(Integer id, Movie movie);
}
