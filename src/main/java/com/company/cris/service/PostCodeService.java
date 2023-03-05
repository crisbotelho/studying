package com.company.cris.service;

import com.company.cris.view.PostCodeResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostCodeService {

    private final String viaCepPrefix = "http://viacep.com.br/ws/";

    public ResponseEntity<PostCodeResponse> get(final String postCode) {
        RestTemplate restTemplate = new RestTemplate();
        StringBuilder url = new StringBuilder(viaCepPrefix)
                .append(postCode)
                .append("/json");
        ResponseEntity<PostCodeResponse> response
                = restTemplate.exchange(
                        url.toString(), HttpMethod.GET, null, PostCodeResponse.class);
        return response;
    }
}
