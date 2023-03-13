package com.company.cris.view;

import com.company.cris.service.PostCodeService;
import com.company.cris.view.response.PostCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/postcode")
public class PostCodeController {

    @Autowired
    private PostCodeService postCodeService;

    @RequestMapping(method = RequestMethod.GET, path = "/{postCode}")
    public ResponseEntity<PostCodeResponse> getDetail(@PathVariable
                                                          @Valid
                                                          @NotEmpty
                                                          @Pattern(regexp = "[0-9]")
                                                          String postCode)
    throws ConstraintViolationException {
        return ResponseEntity.ok(postCodeService.get(postCode));
    }
}
