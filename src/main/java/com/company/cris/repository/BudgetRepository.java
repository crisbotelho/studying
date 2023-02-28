package com.company.cris.repository;

import com.company.cris.entity.Budget;
import com.company.cris.entity.Department;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
    public Budget findByDepartment(Department department);
}
