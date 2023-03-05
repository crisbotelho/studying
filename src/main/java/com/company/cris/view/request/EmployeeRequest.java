package com.company.cris.view.request;

import com.company.cris.view.AddressRequest;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeRequest(String name,
                              LocalDate birthDate,
                              String cpf,
                              String gender,
                              BigDecimal salary,
                              Long supervisorId,
                              AddressRequest address) { }