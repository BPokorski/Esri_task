package com.example.demo.model.employee;

import com.example.demo.model.department.Department;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employee {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="personal_id")
    private String personalId;

    @Column(name="birth_date")
    private LocalDate birthDate;

    @Column(name="email")
    private String email;

    @Column(name="sex")
    private String sex;

    @Column(name="job_position")
    private String jobPosition;

    @ManyToOne(targetEntity = Department.class, fetch = FetchType.EAGER)
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    public Employee(String firstName, String lastName, String personalId,
                    LocalDate birthDate, String email, String sex,
                    String jobPosition, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalId = personalId;
        this.birthDate = birthDate;
        this.email = email;
        this.sex = sex;
        this.jobPosition = jobPosition;
        this.department = department;
    }

    public Employee() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id.equals(employee.id) &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                personalId.equals(employee.personalId) &&
                Objects.equals(birthDate, employee.birthDate) &&
                Objects.equals(email, employee.email) &&
                sex.equals(employee.sex) &&
                Objects.equals(jobPosition, employee.jobPosition) &&
                department.equals(employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, personalId, birthDate, email, sex, jobPosition, department);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalId() { return personalId;}

    public void setPersonalId(String personalId) {this.personalId = personalId;}

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }
}
