package com.impact.outside_in_tdd_demo.contract;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractContainerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void proposeContract() {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(contractJson, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/contracts", request, Map.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsKey("id");
    }

    @Test
    void proposeAndRetrieveContract() {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(contractJson, headers);

        ResponseEntity<Map> createResponse = restTemplate.postForEntity("/contracts", request, Map.class);
        String contractId = (String) createResponse.getBody().get("id");

        ResponseEntity<Map> getResponse = restTemplate.getForEntity("/contracts/" + contractId, Map.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().get("id")).isEqualTo(contractId);
        assertThat(getResponse.getBody().get("brandId")).isEqualTo("0ffeeb95-eb61-443a-98ea-5ba61f532bc4");
        assertThat(getResponse.getBody().get("partnerId")).isEqualTo("6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf");
        assertThat(getResponse.getBody().get("commissionPercentage")).isEqualTo(10.5);
    }

    @Test
    void contractCommission() {
        String contractJson = """
                {
                    "brandId": "0ffeeb95-eb61-443a-98ea-5ba61f532bc4",
                    "partnerId": "6ea4bf54-a8cc-4db5-b4d5-b6dac5154caf",
                    "commissionPercentage": 10.5
                }
                """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(contractJson, headers);

        ResponseEntity<Map> createResponse = restTemplate.postForEntity("/contracts", request, Map.class);
        String contractId = (String) createResponse.getBody().get("id");

        ResponseEntity<Map> commissionResponse = restTemplate.getForEntity(
                "/contracts/" + contractId + "/commission?saleAmount=1000&currency=SEK", Map.class);

        assertThat(commissionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(commissionResponse.getBody()).containsKey("commission");
    }


}
