package com.company.cris.service;

import com.company.cris.view.response.PostCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PostCodeServiceTest {

    private PostCodeService postCodeService = new PostCodeService();

    private static final String POST_CODE = "30530160";

    @Test
    public void givenPostCodeWhenGetThenSuccess() {
        //arrange

        //action
        ResponseEntity<PostCodeResponse> postCodeResponseResponseEntity = postCodeService.get(POST_CODE);
        //assert
        Assertions.assertEquals(HttpStatus.OK, postCodeResponseResponseEntity.getStatusCode());
    }
}
