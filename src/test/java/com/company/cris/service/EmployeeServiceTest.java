package com.company.cris.service;

import com.company.cris.entity.Employee;
import com.company.cris.repository.EmployeeRepository;
import com.company.cris.view.AddressRequest;
import com.company.cris.view.request.EmployeeRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void givenEmployeeRequest_whenSave_thenSaveEmployee() {
        //arrange
        ArgumentCaptor<Employee> capturedEmployee = ArgumentCaptor.forClass(Employee.class);
        Employee employee = new Employee.EmployeeBuilder("Maria",
                "08821916688",
                LocalDate.of(1988, 10, 2),
                "Feminino", new BigDecimal("5000")).build();

        AddressRequest addressRequest = new AddressRequest("Belo Horizonte",
                "Brazil",
                "Barao de Guaxupe",
                672l,
                "30530160");

        EmployeeRequest employeeRequest = new EmployeeRequest("Maria",
                LocalDate.of(1988, 10, 2),
                "08821916688",
                "Feminino",
                new BigDecimal("5000"),
                null,
                addressRequest);

        when(employeeRepository.save(any())).thenReturn(employee);
        //action
        employeeService.save(employeeRequest);
        //assertions
        verify(employeeRepository).save(capturedEmployee.capture());
        Employee employeeCapturedValue = capturedEmployee.getValue();
        Assertions.assertEquals(employeeRequest.name(), employeeCapturedValue.getName());
        Assertions.assertNotNull(employeeCapturedValue.getUuid());
    }

}
