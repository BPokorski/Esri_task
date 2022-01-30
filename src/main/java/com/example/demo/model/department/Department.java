package com.example.demo.model.department;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Department")
public class Department {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="department_name")
    private String departmentName;

    @Column(name="job_position",columnDefinition ="text[]")
    @Type(type = "com.example.demo.model.department.CustomArrayType")
    private Object[] jobPosition;

    private Long supervisorId;

    public Department(String departmentName, String[] jobPosition, Long supervisorId) {
        this.departmentName = departmentName;
        this.jobPosition = jobPosition;
        this.supervisorId = supervisorId;
    }

    public Department() {
    }

    public Long getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Object[] getJobPosition() {
        return jobPosition;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setJobPosition(String[] jobPosition) {
        this.jobPosition = jobPosition;
    }
    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return id.equals(that.id) &&
                departmentName.equals(that.departmentName) &&
                Arrays.equals(jobPosition, that.jobPosition) &&
                Objects.equals(supervisorId, that.supervisorId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, departmentName, supervisorId);
        result = 31 * result + Arrays.hashCode(jobPosition);
        return result;
    }
}
