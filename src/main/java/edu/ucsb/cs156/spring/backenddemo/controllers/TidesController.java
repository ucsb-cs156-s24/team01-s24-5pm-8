package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import edu.ucsb.cs156.spring.backenddemo.services.TidesQueryService;
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

@Tag(name="Tide info from NOAA")
@Slf4j
@RestController
@RequestMapping("/api/tides")
public class TidesController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired 
    TidesQueryService tidesQueryService;

    @Operation(summary = "Gets tide information over a specified period of time and station", description = "Tide information from NOAA's Tides and Currents API. JSON return format documented here: https://api.tidesandcurrents.noaa.gov/api/prod/datagetter?application=ucsb-cs156&begin_date={beginDate}&end_date={endDate}&station={station}&product=predictions&datum=mllw&units=english&time_zone=lst_ldt&interval=hilo&format=json")
    @GetMapping("/get")
    public ResponseEntity<String> getTideInfomation(
        @Parameter(name = "beginDate", description = "beginDate in format yyyymmdd",example = "20220901") @RequestParam String beginDate,
        @Parameter(name = "endDate", description = "endDate in format yyyymmdd",example = "20220903") @RequestParam String endDate,
        @Parameter (name = "station", example = "9414290") @RequestParam String station

    ) throws JsonProcessingException {
        log.info("getTideInformation: beginDate={} endDate={} station={}", beginDate, endDate, station);
        String result = tidesQueryService.getJSON(beginDate, endDate, station);
        return ResponseEntity.ok().body(result);
    }



}
