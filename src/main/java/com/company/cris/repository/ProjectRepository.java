package com.company.cris.repository;

import com.company.cris.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Query("SELECT SUM(p.cost) FROM Project p " +
            "WHERE p.department.id = :departmentId " +
            "AND p.startDate >= :startDate AND p.endDate <= :endDate ")
    public BigDecimal findCostSumByProjectAndDepartmentAndDatePeriod(@Param("departmentId") Long departmentId,
                                                              @Param("startDate") LocalDate startDate,
                                                              @Param("endDate")LocalDate endDate);
}
