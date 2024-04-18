package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.JokeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Jokes from https://v2.jokeapi.dev/")
@Slf4j
@RestController
@RequestMapping("/api/jokes")
public class JokeController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    JokeQueryService jokeQueryService;
    @Operation(summary = "Get jokes for a given category and amount")
    /* description = "Valid categories are: Any, Misc, Programming, Dark, Pun, Spooky, Christmas" */
    @GetMapping("/get")

    public ResponseEntity<String> getJokes(
        /* possible categories are:"Any, Misc, Programming, Dark, Pun, Spooky, Christmas */
        @Parameter(name="amount", description="amount of jokes to get, e.g. '1' or '2'", example="'1' or '2'") @RequestParam String numJokes,
        @Parameter(name="category", description="category of joke", example="'Programming' or 'Spooky'") @RequestParam String category
    ) throws JsonProcessingException {
        log.info("getJokes: category={} numJokes={}", category, numJokes);
        String result = jokeQueryService.getJSON(category, numJokes);
        return ResponseEntity.ok().body(result);
    }
}
