package com.company.cris.service;


import com.company.cris.entity.Address;
import com.company.cris.entity.Employee;
import com.company.cris.repository.EmployeeRepository;
import com.company.cris.view.AddressRequest;
import com.company.cris.view.request.EmployeeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Transactional(propagation = Propagation.REQUIRED)
    public String save(EmployeeRequest employeeRequest) {
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
}
