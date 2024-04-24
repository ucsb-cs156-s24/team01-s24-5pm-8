package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;

@RestClientTest(JokeQueryService.class)
public class JokeQueryServiceTests {

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private JokeQueryService jokeQueryService;

    @Test
    public void test_getJSON() {
        /* possible categories are:"Any, Misc, Programming, Dark, Pun, Spooky, Christmas */
        String category = "Misc";
        String amount = "1";
        
        String expectedURL = JokeQueryService.ENDPOINT.replace("{category}", category)
                                                     .replace("{amount}", amount);

        String fakeJsonResult = "{ \"fake\" : \"result\" }";

        this.mockRestServiceServer.expect(requestTo(expectedURL))
                                  .andExpect(header("Accept", MediaType.APPLICATION_JSON_VALUE))
                                  .andExpect(header("Content-Type", MediaType.APPLICATION_JSON_VALUE))
                                  .andRespond(withSuccess(fakeJsonResult, MediaType.APPLICATION_JSON));

        String actualResult = jokeQueryService.getJSON(category, amount);
        assertEquals(fakeJsonResult, actualResult);
    }
}
