package com.company.cris.view;

import com.company.cris.service.PostCodeService;
import com.company.cris.view.response.PostCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/postcode")
public class PostCodeController {

    @Autowired
    private PostCodeService postCodeService;

    @RequestMapping(method = RequestMethod.GET, path = "/{postCode}")
    public ResponseEntity<PostCodeResponse> getDetail(@PathVariable @Valid @NotEmpty String postCode) {
        return ResponseEntity.ok(postCodeService.get(postCode));
    }
}
