package com.company.cris.view;

import com.company.cris.entity.Employee;
import com.company.cris.repository.EmployeeProjectRepository;
import com.company.cris.repository.EmployeeRepository;
import com.company.cris.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody EmployeeRequest employeeRequest) {
        String uuid = employeeService.save(employeeRequest);

        return new ResponseEntity<String>(uuid, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/department/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listByIdDepartment(@PathVariable Long id) {
        List<Employee> employeeList = employeeProjectRepository.findAllEmployeeByDepartment(id);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(path = "/name/{name}")
    public ResponseEntity<List<Employee>> ListByName(@PathVariable String name) {
        List<Employee> employeeList = employeeRepository.findByName(name);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(path = "/supervisor/{uuid}")
    public ResponseEntity<List<Employee>> ListBySupervisor(@PathVariable String uuidSupervisor) {
        List<Employee> employeeList = employeeRepository.findBySupervisor(uuidSupervisor);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }
}
