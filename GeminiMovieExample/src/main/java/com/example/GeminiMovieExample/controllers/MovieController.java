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
        String descriptionRequest = "Please write a concise 2-3 sentence description of the movie: " + movie.getTitle();
        try {
            GenerateContentResponse descriptionResponse = client.models.generateContent("gemini-2.0-flash-001", descriptionRequest, null);
            movie.setDescription(descriptionResponse.text());
        } catch (IOException | HttpException e) {
            System.err.println("Error communicating with Gemini");
        }

        String castRequest = "Please provide the names of the top 3 actors in this movie. Do not provide any filler or additional context, just the three names in a comma separated list. Please: " + movie.getTitle();
        try {
            GenerateContentResponse castResponse = client.models.generateContent("gemini-2.0-flash-001", castRequest, null);
            movie.setCast(castResponse.text());
        } catch (IOException | HttpException e) {
            System.err.println("Error communicating with Gemini");
        }

        String ratingRequest = "Can you find the average rotten tomatoes critics rating and the average IMDB rating for this movie and calculate an average and return back just the number? I want you to find both numbers and normalize them against a 100-point scale before you do any math. Once both scores are out of 100, divide the sum by 2 and in your response, please only provide the final number with no additional text.: " + movie.getTitle();
        try {
            GenerateContentResponse ratingResponse = client.models.generateContent("gemini-2.0-flash-001", ratingRequest, null);
            movie.setAverageRating(ratingResponse.text());
        } catch (IOException | HttpException e) {
            System.err.println("Error communicating with Gemini");
        }


        movies.add(movie);
        return movie;
    }
}
