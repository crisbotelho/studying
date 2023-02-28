package com.company.cris.service;

import com.company.cris.entity.Budget;
import com.company.cris.entity.Department;
import com.company.cris.repository.BudgetRepository;
import com.company.cris.repository.ProjectRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    BudgetRepository budgetRepository;
    @Mock
    ProjectRepository projectRepository;
    @InjectMocks
    private DepartmentService departmentService = new DepartmentService();

    @Test
    public void givenProjectCostWhenLessThenDepartmentBudgetThenGreenStatus() {
        Department department = new Department();
        department.setName("Product");
        department.setNumber(new BigInteger("10"));
        department.setId(1l);

        Budget budget = new Budget();
        budget.setDepartment(department);
        budget.setId(1l);
        budget.setValue(new BigDecimal("100000"));
        budget.setStartDate(LocalDate.of(2022, 6, 1));
        budget.setStartDate(LocalDate.of(2022, 12, 31));

        when(budgetRepository.findByDepartment(new Department(1l))).thenReturn(budget);
        when(projectRepository.findCostSumByProjectAndDepartmentAndDatePeriod(1l,
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31) )).thenReturn(new BigDecimal(90000));
        Assertions.assertSame("GREEN", departmentService.getBudgetStatus(department.getId(),
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31)));
    }

    @Test
    public void givenProjectCostWhenGreaterUpTo10PercentageThanDepartmentBudgetThenYellowStatus() {
        Department department = new Department();
        department.setName("Financial");
        department.setNumber(new BigInteger("10"));
        department.setId(2l);

        Budget budget = new Budget();
        budget.setDepartment(department);
        budget.setId(2l);
        budget.setValue(new BigDecimal("200000"));
        budget.setStartDate(LocalDate.of(2022, 6, 1));
        budget.setStartDate(LocalDate.of(2022, 12, 31));


        when(budgetRepository.findByDepartment(new Department(2l))).thenReturn(budget);
        when(projectRepository.findCostSumByProjectAndDepartmentAndDatePeriod(2l,
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31) )).thenReturn(new BigDecimal(210000));

        Assertions.assertSame("YELLOW", departmentService.getBudgetStatus(department.getId(),
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31)));
    }

    @Test
    public void givenProjectCostWhenGreaterIn10PercentageThanDepartmentBudgetThenRedStatus() {
        Department department = new Department();
        department.setName("Marketing");
        department.setNumber(new BigInteger("10"));
        department.setId(3l);

        Budget budget = new Budget();
        budget.setDepartment(department);
        budget.setId(3l);
        budget.setValue(new BigDecimal("200000"));
        budget.setStartDate(LocalDate.of(2022, 6, 1));
        budget.setStartDate(LocalDate.of(2022, 12, 31));

        when(budgetRepository.findByDepartment(new Department(3l))).thenReturn(budget);
        when(projectRepository.findCostSumByProjectAndDepartmentAndDatePeriod(3l,
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31) )).thenReturn(new BigDecimal(221000));

        Assertions.assertSame("RED", departmentService.getBudgetStatus(department.getId(),
                LocalDate.of(2022, 01, 01),
                LocalDate.of(2022, 12, 31)));
    }
}
