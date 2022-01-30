package com.example.demo.service;

import com.example.demo.model.department.Department;
import com.example.demo.model.employee.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.employee.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
public class EmployeeServiceImplTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @DisplayName("Create new employee with valid job position test")
    public void shouldCreateNewEmployeeWithGivenJobPositionTest() {
        String firstName = "Tester";
        String lastName = "TestSmith";
        String sex = "M";
        LocalDate birthDate = LocalDate.parse("2001-10-09");
        String personalId = "01300946113";
        String email = "tst@mail.com";
        String jobPosition = "Software Engineer";

        Optional<Employee> actualEmployee = employeeService.addEmployee(firstName, lastName, sex,
                birthDate, personalId, email, jobPosition);

        assertTrue(actualEmployee.isPresent());
    }

    @Test
    @DisplayName("Not create new employee with valid job position test")
    public void shouldNotCreateNewEmployeeWithGivenJobPositionTest() {
        String firstName = "Tester";
        String lastName = "TestSmith";
        String sex = "M";
        LocalDate birthDate = LocalDate.parse("2001-10-09");
        String personalId = "01300946113";
        String email = "tst@mail.com";
        String jobPosition = "Test job";

        Optional<Employee> actualEmployee = employeeService.addEmployee(firstName, lastName, sex,
                birthDate, personalId, email, jobPosition);

        assertTrue(actualEmployee.isEmpty());
    }

    @Test
    @DisplayName("Change department supervisorId to null by updating jobPosition test")
    public void shouldBeNullSupervisorIdOfDepartmentAfterChangeEmployeeJobPositionTest() {
        String firstName = "Tester";
        String lastName = "TestSmith";
        String sex = "M";
        LocalDate birthDate = LocalDate.parse("2001-10-09");
        String personalId = "01300946113";
        String email = "tst@mail.com";
        String jobPosition = "Software Engineer";
        Employee employee = employeeService.addEmployee(firstName, lastName, sex,
                birthDate, personalId, email, jobPosition).get();
        Department department = departmentRepository.findById((long) 1).get();
        department.setSupervisorId((employee.getId()));

        employeeService.updateJobPositionById(employee.getId(), "Test Job");

        assertNull(departmentRepository.findById((long) 1
                ).get().getSupervisorId()
        );
    }
}
