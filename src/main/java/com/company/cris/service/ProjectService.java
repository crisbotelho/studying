package com.company.cris.service;

import com.company.cris.entity.Department;
import com.company.cris.entity.Project;
import com.company.cris.exception.DepartmentNotFoundException;
import com.company.cris.repository.DepartmentRepository;
import com.company.cris.repository.ProjectRepository;
import com.company.cris.view.request.ProjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectService.class);
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public Project save(ProjectRequest projectRequest) throws DepartmentNotFoundException{
        Optional<Department> department = departmentRepository.findById(projectRequest.departmentId());
        if(department.isEmpty()) {
            throw new DepartmentNotFoundException("Department: " + projectRequest.departmentId() + " does not exist!");
        } else {
            Project project = new Project(projectRequest.name(), projectRequest.value(), projectRequest.cost(),
                    projectRequest.startDate(), projectRequest.endDate(), department.get());
            return projectRepository.save(project);
        }
    }
}
