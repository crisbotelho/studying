package com.company.cris.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uuid;
    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;
    private String cpf;
    private String gender;
    private String name;
    private BigDecimal salary;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_SUPERVISOR")
    private Employee supervisor;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADDRESS")
    private Address address;

    public Employee() {}

    public Employee(EmployeeBuilder employeeBuilder) {
        this.uuid = employeeBuilder.uuid;
        this.name = employeeBuilder.name;
        this.cpf = employeeBuilder.cpf;
        this.birthDate = employeeBuilder.birthDate;
        this.gender = employeeBuilder.gender;
        this.salary = employeeBuilder.salary;
        this.address = employeeBuilder.address;
        this.supervisor = employeeBuilder.supervisor;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) && uuid.equals(employee.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }

    public static class EmployeeBuilder {
        private String name;
        private String cpf;
        private LocalDate birthDate;
        private String gender;
        private BigDecimal salary;
        private String uuid;
        private Employee supervisor;
        private Address address;

        public EmployeeBuilder(String name, String cpf, LocalDate birthDate,
                               String gender, BigDecimal salary) {
            this.name = name;
            this.cpf = cpf;
            this.birthDate = birthDate;
            this.gender = gender;
            this.salary = salary;
        }

        public EmployeeBuilder setUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public EmployeeBuilder setSupervisor(Employee supervisor) {
            this.supervisor = supervisor;
            return this;
        }

        public EmployeeBuilder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }

    }
}
