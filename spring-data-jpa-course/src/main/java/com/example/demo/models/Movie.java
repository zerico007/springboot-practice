package com.example.demo.models;

import java.math.BigDecimal;
import java.util.List;

public class Movie {

    private final Integer id;

    private final String title;

    private final BigDecimal year;

    private final int actor;

    public Movie(Integer id, String title, BigDecimal year, int actor) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.actor = actor;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getYear() {
        return year;
    }

    public int getActor() {
        return actor;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
