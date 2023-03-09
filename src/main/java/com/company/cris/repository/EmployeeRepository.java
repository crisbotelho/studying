package com.company.cris.repository;


import com.company.cris.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee as e WHERE e.supervisor.uuid = :uuidSupervisor")
    List<Employee> findBySupervisor(@Param("uuidSupervisor") String uuidSupervisor);

    Boolean existsByCpf(String cpf);

    Employee findByUuid(String uuid);


}
