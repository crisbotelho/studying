package com.company.cris.service;

import com.company.cris.view.response.PostCodeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostCodeServiceTest {

    private PostCodeService postCodeService = new PostCodeService();

    private static final String POST_CODE = "30530160";

    @Test
    public void givenPostCodeWhenGetThenSuccess() {
        //arrange

        //action
        PostCodeResponse postCodeResponse = postCodeService.get(POST_CODE);
        //assert
        Assertions.assertNotNull(postCodeResponse);
    }
}
