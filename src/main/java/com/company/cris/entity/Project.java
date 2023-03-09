package com.company.cris.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal value;
    private BigDecimal cost;
    @Column(name = "START_DATE")
    private LocalDate startDate;
    @Column(name = "END_DATE")
    private LocalDate endDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_DEPARTMENT")
    private Department department;

    public Project() {}

    public Project(String name, BigDecimal value, BigDecimal cost,
                   LocalDate startDate, LocalDate endDate, Department department) {
        this.name = name;
        this.value = value;
        this.cost = cost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id.equals(project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
