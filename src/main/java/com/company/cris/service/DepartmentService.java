package com.company.cris.service;

import com.company.cris.entity.Budget;
import com.company.cris.entity.Department;
import com.company.cris.repository.BudgetRepository;
import com.company.cris.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ProjectRepository projectRepository;
    public String getBudgetStatus(Long departmentId, LocalDate startDate, LocalDate endDate){
        Budget budget = budgetRepository.findByDepartment(new Department(departmentId));
        BigDecimal costProjects = projectRepository
                .findCostSumByProjectAndDepartmentAndDatePeriod(departmentId, startDate, endDate);
        return getBudgetStatus(budget.getValue(), costProjects);
    }

    private String getBudgetStatus(BigDecimal departmentBudget, BigDecimal costProjects) {
        if(costProjects.compareTo(departmentBudget) <= 0) {
            return "GREEN";
        } else if (departmentBudget.multiply(new BigDecimal("1.1")).compareTo(costProjects) >= 0) {
            return "YELLOW";
        } else{
            return "RED";
        }
    }
}
