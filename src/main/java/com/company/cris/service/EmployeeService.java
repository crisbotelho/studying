package com.company.cris.service;


import com.company.cris.entity.Address;
import com.company.cris.entity.Employee;
import com.company.cris.exception.EmployeeAlreadyExistException;
import com.company.cris.exception.EmployeeNotFoundException;
import com.company.cris.repository.EmployeeProjectRepository;
import com.company.cris.repository.EmployeeRepository;
import com.company.cris.view.AddressRequest;
import com.company.cris.view.request.EmployeeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeProjectRepository employeeProjectRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public String save(EmployeeRequest employeeRequest) throws EmployeeAlreadyExistException {
        checkDuplicated(employeeRequest.cpf());
        Employee employee = new Employee.EmployeeBuilder(employeeRequest.name(),
                employeeRequest.cpf(),
                employeeRequest.birthDate(),
                employeeRequest.gender(),
                employeeRequest.salary())
                .setUuid(UUID.randomUUID().toString())
                .build();
        Address address = getAddress(employeeRequest.address(), employee);
        employee.setAddress(address);
        setSupervisor(employeeRequest, employee);
        LOGGER.info("Saving the user: " + employee.getUuid().toString());
        return employeeRepository.save(employee).getUuid();
    }

    private void setSupervisor(EmployeeRequest employeeRequest, Employee employee) {
        if(employeeRequest.supervisorId() != null) {
            Employee supervisor = employeeRepository.findById(employeeRequest.supervisorId()).get();
            employee.setSupervisor(supervisor);
        }
    }

    private Address getAddress(AddressRequest addressRequest, Employee employee) {
        Address address = new Address();
        address.setCity(addressRequest.city());
        address.setCountry(addressRequest.country());
        address.setNumber(addressRequest.number());
        address.setStreet(addressRequest.street());
        address.setPostalCode(addressRequest.postalCode());
        address.setEmployee(employee);
        return address;
    }

    public List<Employee> listByIdDepartment(Long idDepartment) {
        List<Employee> employeeList = employeeProjectRepository.findAllEmployeeByDepartment(idDepartment);
        if(employeeList.isEmpty()) {
            throw new EmployeeNotFoundException("Do not exist employee in " + idDepartment + " department");
        } else {
            return employeeList;
        }
    }

    public List<Employee> ListByName(String name) {
        List<Employee> employeeList = employeeRepository.findByName(name);
        if(employeeList.isEmpty()) {
            throw new EmployeeNotFoundException("Do not exist employee with name: " + name );
        } else {
            return employeeList;
        }
    }

    public List<Employee> ListBySupervisor(String uuidSupervisor) {
        List<Employee> employeeList = employeeRepository.findBySupervisor(uuidSupervisor);

        if(employeeList.isEmpty()) {
            throw new EmployeeNotFoundException("Do not exist Supervisor with uuid: " + uuidSupervisor );
        } else {
            return employeeList;
        }
    }

    public Employee getByUuid(String uuid) {
        Employee employee = employeeRepository.findByUuid(uuid);

        if(employee == null) {
            throw new EmployeeNotFoundException("Do not exist Employee with uuid: " + uuid );
        } else {
            return employee;
        }
    }

    private void checkDuplicated(String cpf) throws EmployeeAlreadyExistException {
        if(employeeRepository.existsByCpf(cpf)) {
            throw new EmployeeAlreadyExistException("The CPF: " + cpf + " already exists!");
        }
    }
}
