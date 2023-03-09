package com.company.cris.view;

import com.company.cris.entity.Project;
import com.company.cris.exception.DepartmentNotFoundException;
import com.company.cris.exception.EmployeeNotFoundException;
import com.company.cris.repository.EmployeeProjectRepository;
import com.company.cris.service.ProjectService;
import com.company.cris.view.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Autowired
    private ProjectService projectService;

    @RequestMapping(path = "/employee/{uuid}")
    public ResponseEntity<List<Project>> ListByEmployee(@PathVariable String uuid) {
        List<Project> projectList = employeeProjectRepository.findAllProjectsByEmployee(uuid);

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Project> save(@RequestBody @Validated ProjectRequest projectRequest)
    throws DepartmentNotFoundException {
        return new ResponseEntity<>(projectService.save(projectRequest), HttpStatus.OK);
    }

    @ExceptionHandler(value = DepartmentNotFoundException.class)
    public ResponseEntity<String> DepartmentNotFoundException(DepartmentNotFoundException departmentNotFoundException) {
        return new ResponseEntity<String>(departmentNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
