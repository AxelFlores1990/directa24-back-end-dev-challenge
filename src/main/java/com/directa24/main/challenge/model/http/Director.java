package com.directa24.main.challenge.model.http;

import java.util.Objects;

public class Director {

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}