package com.govt.covid.support.query.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@Service
public class CovidSupportService {
    @Value("${data.service.url}")
    private String serviceUri;

    @Autowired
    private RestTemplate restTemplate;

    public String getDetailsBasedOnDate(String date) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUri+"ap1/v1/get-date-info")
                .queryParam("date", date);
        return queryDataService(builder);

    }

    public String getDetailsBasedOnState(String state) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUri+"ap1/v1/get-state-info")
                .queryParam("state", state);
        return queryDataService(builder);
    }

    public String getDetailsBasedOnDateAndState(String state, String date) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(serviceUri + "ap1/v1/pinpoint-state")
                .queryParam("state", state)
                .queryParam("date", date);
        return queryDataService(builder);
    }

    private String queryDataService(UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);
        return response.getBody();
    }
}
