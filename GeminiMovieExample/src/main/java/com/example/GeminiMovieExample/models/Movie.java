package com.example.GeminiMovieExample.models;

import com.google.genai.types.GenerateContentResponse;

public class Movie {
    private String title;
    private int rating;
    private String description;
    private String cast;
    private String averageRating;

    String newline = System.lineSeparator();

    public Movie(String title, int rating, String description, String cast, String averageRating) {
        this.title = title;
        this.rating = rating;
        this.description = description;
        this.cast = cast;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return
                "Title: " + title + newline +
                "Rating: " + rating + newline +
                "Description: " + description + newline;
    }
}
