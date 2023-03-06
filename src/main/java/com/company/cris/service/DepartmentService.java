package com.company.cris.service;

import com.company.cris.entity.Budget;
import com.company.cris.entity.Department;
import com.company.cris.repository.BudgetRepository;
import com.company.cris.repository.ProjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    private static final String TEN_PERCENT = "1.1";
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ProjectRepository projectRepository;
    public String getBudgetStatus(Long departmentId, LocalDate startDate, LocalDate endDate){
        LOGGER.info("Getting budget status for department: {}", departmentId);
        Budget budget = budgetRepository.findByDepartment(new Department(departmentId));
        BigDecimal costProjects = projectRepository
                .findCostSumByProjectAndDepartmentAndDatePeriod(departmentId, startDate, endDate);
        return getBudgetStatus(budget.getValue(), costProjects);
    }

    private String getBudgetStatus(BigDecimal departmentBudget, BigDecimal costProjects) {
        if(costProjects.compareTo(departmentBudget) <= 0) {
            return BudgetStatusEnum.GREEN.name();
        } else if (departmentBudget.multiply(
                new BigDecimal(TEN_PERCENT)).compareTo(costProjects) >= 0) {
            return BudgetStatusEnum.YELLOW.name();
        } else{
            return BudgetStatusEnum.RED.name();
        }
    }
}
