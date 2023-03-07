package com.company.cris.service;

import com.company.cris.view.response.PostCodeFallbackResponse;
import com.company.cris.view.response.PostCodeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostCodeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostCodeService.class);
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String VIA_CEP_PREFIX = "http://viacep.com.br/ws/";
    private final static String AWESOME_API_PREFIX = "https://cep.awesomeapi.com.br/json/";

    @CircuitBreaker(name = "postCodeFromViaCep", fallbackMethod = "getFromFallback")
    public ResponseEntity<PostCodeResponse> get(final String postCode) {
        LOGGER.info("Getting data from viacep, postcode: {}", postCode);
        StringBuilder url = new StringBuilder(VIA_CEP_PREFIX)
                .append(postCode)
                .append("/json");
        return restTemplate.exchange(
                url.toString(), HttpMethod.GET, null, PostCodeResponse.class);
    }

    private ResponseEntity<PostCodeResponse> getFromFallback(final String postCode, Throwable e) {
        LOGGER.info("Getting data from awesomeapi, postcode: {}", postCode);
        String url = AWESOME_API_PREFIX + postCode;
        PostCodeFallbackResponse postCodeFallbackResponse = restTemplate.exchange(
                url, HttpMethod.GET, null, PostCodeFallbackResponse.class).getBody();
        PostCodeResponse postCodeResponse = new PostCodeResponse(postCodeFallbackResponse.city_ibge(),
                postCodeFallbackResponse.address(),
                postCodeFallbackResponse.district(),
                postCodeFallbackResponse.city(),
                postCodeFallbackResponse.state());
        return new ResponseEntity<>(postCodeResponse, HttpStatus.OK);
    }
}
