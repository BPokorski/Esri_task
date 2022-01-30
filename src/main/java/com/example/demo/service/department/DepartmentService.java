package com.example.demo.service.department;

import com.example.demo.model.department.Department;

import java.util.Optional;

public interface DepartmentService {
    /**
     * Find department, which job position belongs to.
     * @param jobPosition - Name of position.
     * @return Found department or empty if there is no department with that position.
     */
    Optional<Department> findDepartmentFromJobPosition(String jobPosition);

    /**
     * Find department by given id.
     * @param id - Id of department.
     * @return Department or empty if no departments were found.
     */
    Optional<Department> findDepartmentById(Long id);

    /**
     * Set supervisor id as id of given employee.
     * @param departmentId - Id of department in which save supervisor.
     * @param employeeId - Id of employee who will be supervisor.
     * @return Updated department entity or empty if department wasn't found.
     */
    Optional<Department> setSupervisorForDepartment(Long departmentId, Long employeeId);

    /**
     * Check if employee is a supervisor.
     * @param employeeId - Id of employee to be checked.
     * @return True/false.
     */
    boolean isSupervisor(Long employeeId);
}
