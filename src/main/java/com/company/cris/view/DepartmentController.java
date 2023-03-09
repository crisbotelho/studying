package com.company.cris.view;

import com.company.cris.service.DepartmentService;
import com.company.cris.view.request.DepartmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody @Validated DepartmentRequest departmentRequest) {

        departmentService.save(departmentRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/startDate/{startDate}/endDate/{endDate}")
    public ResponseEntity<String> getStatus(@PathVariable Long id,
                                            @PathVariable String startDate,
                                            @PathVariable String  endDate) {
        return ResponseEntity.ok(departmentService.getBudgetStatus(id, startDate, endDate));
    }

}
