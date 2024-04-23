package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name="Zip Code Information from http://www.zippopotam.us/")
@Slf4j
@RestController
@RequestMapping("/api/zipcode")

public class ZipCodeController {
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ZipCodeQueryService ZipCodeQueryService;

    @Operation(summary = "Get info of a zipcode", description = "JSON return format documented here: https://api.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getZipCodeInfo(
        @Parameter(name = "zipcode", description="zipcode of santa barabra", example = "93117") @RequestParam String zipcode
    ) throws JsonProcessingException {
        log.info("getZipCodeInfo: zipcode={}", zipcode);
        String result = ZipCodeQueryService.getJSON(zipcode);
        return ResponseEntity.ok().body(result);
    }
}