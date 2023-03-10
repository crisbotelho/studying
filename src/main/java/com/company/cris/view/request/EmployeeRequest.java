package com.company.cris.view.request;

import com.company.cris.view.AddressRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeRequest(@NotEmpty String name,
                              @NotNull LocalDate birthDate,
                              @NotEmpty String cpf,
                              @NotEmpty String gender,
                              @NotNull BigDecimal salary,
                              Long supervisorId,
                              AddressRequest address) { }
