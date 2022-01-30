package com.example.demo.service.employee;

import com.example.demo.model.employee.Employee;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface EmployeeService {
    /**
     * Save employee to the database.
     * @param firstName - First name of employee.
     * @param lastName - Last name of employee.
     * @param sex - Employee's sex.
     * @param birthDate - Birth date of employee.
     * @param personalId - Personal id number of employee.
     * @param email - Email of employee.
     * @param jobPosition - Job position of employee.
     * @return Newly created employee.
     */
    Optional<Employee> addEmployee(String firstName, String lastName,
                                   String sex, LocalDate birthDate,
                                   String personalId, String email,
                                   String jobPosition);

    /**
     * Delete employee with given id.
     * @param id - Id of employee to be deleted
     */
    void deleteEmployee(Long id);
    Optional<Employee> findEmployeeById(Long id);

    /**
     * Find all employees that have given job position.
     * @param position - Job position to be filtered by.
     * @param page - Number of page to obtain.
     * @param pageSize - Number how many employees should be returned per page.
     * @return Page of employees.
     */
    Page<Employee> findEmployeesByJobPosition(String position, int page, int pageSize);

    /**
     * Find all employees in the database.
     * @param page - Number of page to obtain.
     * @param pageSize - Number how many employees should be returned per page.
     * @return Page of employees.
     */
    Page<Employee> findAllEmployees(int page, int pageSize);
    /**
     * Update job position for employee with given id.
     * @param id - Id of employee to be updated.
     * @param jobPosition - new position.
     * @return Updated Employee entity or empty if employee was not found.
     */
    Optional<Employee> updateJobPositionById(Long id, String jobPosition);
    /**
     * Update email for employee with given id.
     * @param id - Id of employee to be updated.
     * @param email - new email.
     * @return Updated Employee entity or empty if employee was not found.
     */
    Optional<Employee> updateEmailById(Long id, String email);

    /**
     * Find employee by given personal id.
     * @param personalId - Personal id.
     * @return Employee entity or empty if employee was not found.
     */
    Optional<Employee> findEmployeeByPersonalId(String personalId);

    /**
     * Find employee by given email.
     * @param email - Email of employee
     * @return Employee entity or empty if employee was not found
     */
    Optional<Employee> findEmployeeByEmail(String email);
}
