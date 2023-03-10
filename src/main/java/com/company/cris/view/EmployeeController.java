package com.company.cris.view;

import com.company.cris.entity.Employee;
import com.company.cris.exception.EmployeeAlreadyExistException;
import com.company.cris.exception.EmployeeNotFoundException;
import com.company.cris.service.EmployeeService;
import com.company.cris.view.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody @Valid EmployeeRequest employeeRequest)
            throws EmployeeAlreadyExistException{
        String uuid = employeeService.save(employeeRequest);

        return new ResponseEntity<String>(uuid, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/department/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> listByIdDepartment(@PathVariable Long id)
            throws EmployeeNotFoundException {
        List<Employee> employeeList = employeeService.listByIdDepartment(id);

        return new ResponseEntity<>(employeeList, HttpStatus.OK);
    }

    @RequestMapping(path = "/name/{name}")
    public ResponseEntity<List<Employee>> ListByName(@PathVariable String name)
            throws EmployeeNotFoundException{
        return ResponseEntity.ok(employeeService.ListByName(name));
    }

    @RequestMapping(path = "/supervisor/{uuid}")
    public ResponseEntity<List<Employee>> ListBySupervisor(@PathVariable String uuid)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.ListBySupervisor(uuid));
    }

    @RequestMapping(path = "/{uuid}")
    public ResponseEntity<Employee> getByUuid(@PathVariable String uuid)
            throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getByUuid(uuid));
    }

}
