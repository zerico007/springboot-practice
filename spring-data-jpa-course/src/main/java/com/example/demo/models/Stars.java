package com.example.demo.models;

public class Stars {

    private final int person_id;
    private final int movie_id;

    public Stars(int person_id, int movie_id) {
        this.person_id = person_id;
        this.movie_id = movie_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public int getMovie_id() {
        return movie_id;
    }
}
