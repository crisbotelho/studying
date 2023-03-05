package com.company.cris.view;

import com.company.cris.entity.Department;
import com.company.cris.repository.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody @Validated DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.name());
        department.setNumber(departmentRequest.number());

        departmentRepository.save(department);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
