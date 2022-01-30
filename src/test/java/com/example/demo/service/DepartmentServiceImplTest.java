package com.example.demo.service;

import com.example.demo.model.department.Department;
import com.example.demo.service.department.DepartmentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
public class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;

    @Test
    @DisplayName("Valid department for given job position test")
    public void shouldReturnDepartmentFromGivenJobPositionTest() {
        String jobPosition = "Software Engineer";
        Department expectedDepartment = departmentService.findDepartmentById((long) 1).get();

        Department actualDepartment = departmentService.findDepartmentFromJobPosition(jobPosition).get();

        assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    @DisplayName("Not found department for given position test")
    public void shouldNotReturnDepartmentFromGivenJobPositionTest() {
        String jobPosition = "Test job position";

        Optional<Department> actualDepartment = departmentService.findDepartmentFromJobPosition(jobPosition);

        assertTrue(actualDepartment.isEmpty());
    }

    @Test
    @DisplayName("Proper supervisor id set for department test")
    public void shouldSetProperSupervisorIdForDepartment() {
        Long departmentId = (long) 1;
        Long employeeId = (long) 1;

        departmentService.setSupervisorForDepartment(departmentId, employeeId);

        assertTrue(departmentService.isSupervisor(employeeId));
    }
}
