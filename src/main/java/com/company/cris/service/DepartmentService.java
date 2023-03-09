package com.company.cris.service;

import com.company.cris.entity.Budget;
import com.company.cris.entity.Department;
import com.company.cris.repository.BudgetRepository;
import com.company.cris.repository.DepartmentRepository;
import com.company.cris.repository.ProjectRepository;
import com.company.cris.view.request.DepartmentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DepartmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);
    private static final String TEN_PERCENT = "1.1";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    BudgetRepository budgetRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public void save(DepartmentRequest departmentRequest) {
        LOGGER.info("Saving department: {}", departmentRequest.name());
        Department department = new Department();
        department.setName(departmentRequest.name());
        department.setNumber(departmentRequest.number());

        departmentRepository.save(department);
    }

    public String getBudgetStatus(Long departmentId, String startDate, String endDate){
        LOGGER.info("Getting budget status for department: {}", departmentId);
        LocalDate localDateStartDate = LocalDate.parse(startDate, FORMATTER);
        LocalDate localDateEndDate = LocalDate.parse(endDate, FORMATTER);
        Budget budget = budgetRepository.findByDepartment(new Department(departmentId));
        BigDecimal costProjects = projectRepository
                .findCostSumByProjectAndDepartmentAndDatePeriod(departmentId, localDateStartDate, localDateEndDate);
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
