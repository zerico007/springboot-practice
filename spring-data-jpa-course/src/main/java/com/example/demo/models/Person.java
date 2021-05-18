package com.example.demo.models;

import java.math.BigDecimal;

public class Person {

    private final String name;

    private final Integer Id;

    private final BigDecimal birth;

    public Person(String name, Integer id, BigDecimal birth) {
        this.name = name;
        Id = id;
        this.birth = birth;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return Id;
    }

    public BigDecimal getBirth() {
        return birth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", Id=" + Id +
                ", birth=" + birth +
                '}';
    }
}
