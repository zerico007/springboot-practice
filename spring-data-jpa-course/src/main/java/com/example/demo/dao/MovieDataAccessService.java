package com.example.demo.dao;

import com.example.demo.models.Movie;
import com.example.demo.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static java.lang.Math.abs;

@Repository("movie")
public class MovieDataAccessService implements MovieDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> getAllMovies() {
        final String sql = "SELECT * from movies INNER JOIN stars on stars.movie_id = id ORDER BY year LIMIT 20";
        return jdbcTemplate.query(sql, ((resultSet, i) -> {
            Integer id = resultSet.getInt("id");
            BigDecimal year = resultSet.getBigDecimal("year");
            String title = resultSet.getString("title");
            int peopleId = resultSet.getInt("person_id");
            return new Movie(id, title, year, peopleId);
        }));
    }

    @Override
    public String insertMovie(Movie movie) {
        final String query = "INSERT INTO movies (id, title, year) VALUES (?, ?, ?)";
        Integer newMovieId = abs(new Random().nextInt());
        jdbcTemplate.update(query, new Object[]{newMovieId, movie.getTitle(), movie.getYear()}, new int[]{Types.INTEGER, Types.VARCHAR, Types.BIGINT});
        return String.format("Movie successfully created with id %s", newMovieId);
    }

    @Override
    public void deleteMovie(Integer id) {
        final String sql = "DELETE FROM movies WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<Movie> selectMovieById(Integer id) {
        final String query = "SELECT * FROM movies INNER JOIN stars ON movies.id = stars.movie_id WHERE id = ?";
        List<Movie> movies = jdbcTemplate.query(query, new Object[]{id}, (resultSet, i) -> {
            Integer movieId = resultSet.getInt("id");
            BigDecimal year = resultSet.getBigDecimal("year");
            String title = resultSet.getString("title");
            int peopleId = resultSet.getInt("person_id");

            return new Movie(movieId, title, year, peopleId);
        });
        System.out.print(movies);
        if(movies.size() != 0) {
            return Optional.ofNullable(movies.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Movie>> selectMovieByTitle(String title) {
        final String query = "SELECT * FROM movies INNER JOIN stars on stars.movie_id = movies.id WHERE lower(title) LIKE ?";
        String searchText = "%" + title.toLowerCase();
        List<Movie> movies = jdbcTemplate.query(query, new Object[]{searchText}, (resultSet, i) -> {
            Integer movieId = resultSet.getInt("id");
            BigDecimal year = resultSet.getBigDecimal("year");
            String movieTitle = resultSet.getString("title");
            int peopleId = resultSet.getInt("person_id");
            return new Movie(movieId, movieTitle, year, peopleId);
        });
        return Optional.of(movies);
    }

    @Override
    public void updateMovie(Integer id, Movie movie) {
        final String updateQuery = "UPDATE movies SET title = ?, year = ? WHERE id = ?";
        jdbcTemplate.update(updateQuery, movie.getTitle(), movie.getYear(), id);
    }


}
