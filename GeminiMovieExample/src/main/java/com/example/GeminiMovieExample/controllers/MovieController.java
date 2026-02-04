package com.example.GeminiMovieExample.controllers;
import com.example.GeminiMovieExample.models.Movie;
import org.springframework.web.bind.annotation.*;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.apache.http.HttpException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final List<Movie> movies = new ArrayList<>();
    Client client = new Client();

    @GetMapping
    public List<Movie> getAllMovies() {
        return movies;
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        String request = "Please write a concise 2-3 sentence description of the movie: " + movie.getTitle();
        try {
            GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", request, null);
            movie.setDescription(response.text());
        } catch (IOException | HttpException e) {
            System.err.println("Error communicating with Gemini");
        }

        movies.add(movie);
        return movie;
    }
}
