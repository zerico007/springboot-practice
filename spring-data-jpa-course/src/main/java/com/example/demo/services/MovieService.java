package com.example.demo.services;

import com.example.demo.dao.MovieDao;
import com.example.demo.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieDao movieDao;

    @Autowired
    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public List<Movie> getAllMovies() {
        return movieDao.getAllMovies();
    }

    public String insertMovie(Movie movie) throws SQLException {
        return movieDao.insertMovie(movie);
    }

    public Optional<Movie> getMovieById(Integer id){
        return movieDao.selectMovieById(id);
    }

    public Optional<List<Movie>> getMovieByTitle(String title) {
        return movieDao.selectMovieByTitle(title);
    }

    public void deleteMovie(Integer id) {
        movieDao.deleteMovie(id);
    }

    public void updateMovie(Integer id, Movie movie) {
        movieDao.updateMovie(id, movie);
    }
}
