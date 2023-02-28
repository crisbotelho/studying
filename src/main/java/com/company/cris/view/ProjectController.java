package com.company.cris.view;

import com.company.cris.entity.Project;
import com.company.cris.repository.EmployeeProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @RequestMapping(path = "/employee/{uuid}")
    public ResponseEntity<List<Project>> ListByEmployee(@PathVariable String uuid) {
        List<Project> projectList = employeeProjectRepository.findAllProjectsByEmployee(uuid);

        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }
}
