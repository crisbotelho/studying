package com.company.cris.repository;

import com.company.cris.entity.Employee;
import com.company.cris.entity.EmployeeProject;
import com.company.cris.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeProjectRepository extends CrudRepository<EmployeeProject, Long> {

    @Query("SELECT pe.employee from EmployeeProject as pe  " +
            "where pe.project.department.id = :departmentId AND pe.project.endDate is null ")
    public List<Employee> findAllEmployeeByDepartment(@Param("departmentId") Long departmentId);

    @Query("SELECT ep.project from EmployeeProject as ep where ep.employee.uuid = :uuidEmployee AND ep.project.endDate is not null")
    public List<Project> findAllProjectsByEmployee(@Param("uuidEmployee") String uuidEmployee);
}
