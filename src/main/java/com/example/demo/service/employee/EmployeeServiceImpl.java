package com.example.demo.service.employee;

import com.example.demo.model.department.Department;
import com.example.demo.model.employee.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Firstly search for department in which is job position, then save user to the database.
     * @param firstName {@inheritDoc}
     * @param lastName - {@inheritDoc}
     * @param sex - {@inheritDoc}
     * @param birthDate - {@inheritDoc}
     * @param personalId - {@inheritDoc}
     * @param email - {@inheritDoc}
     * @param jobPosition - {@inheritDoc}
     * @return Newly created employee or empty if no departments were found.
     */
    @Override
    public Optional<Employee> addEmployee(String firstName, String lastName,
                                          String sex, LocalDate birthDate,
                                          String personalId, String email,
                                          String jobPosition) {


        Optional<Department> department = departmentService.findDepartmentFromJobPosition(jobPosition);
        if (department.isPresent()) {
            Employee employee = new Employee(firstName, lastName, personalId,
                    birthDate, email, sex,
                    jobPosition, department.get());

            return Optional.of(employeeRepository.save(employee));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Check firstly if employee to be deleted is supervisor of any department.
     * If so, set supervisor_id of that department to null. Then delete employee;
     * @param id - Id of employee to be deleted
     */
    @Override
    public void deleteEmployee(Long id) {
        if (departmentService.isSupervisor(id)) {
            Optional<Employee> employee = employeeRepository.findById(id);

            departmentService.setSupervisorForDepartment(employee.get().getDepartment().getId(),
                    null);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Page<Employee> findEmployeesByJobPosition(String position, int page, int pageSize) {
        return employeeRepository.findAllByJobPosition(position, PageRequest.of(page, pageSize));
    }

    @Override
    public Page<Employee> findAllEmployees(int page, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(page, pageSize));
    }

    /**
     * Firstly check whether there is department with given job position.
     * Then check whether current employee is supervisor of the department and if
     * new department is the same as old.
     * If employee is supervisor and new job position is in other department, then
     * set value of supervisor_id of old department to null. Next update entity.
     * @param id - Id of employee to be updated.
     * @param jobPosition - new position.
     * @return Updated employee entity or empty
     * if employee or department for new job position weren't found
     */
    @Override
    public  Optional<Employee> updateJobPositionById(Long id, String jobPosition) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            Optional<Department> department = departmentService.findDepartmentFromJobPosition(jobPosition);

            if (department.isPresent()) {
                // Change department supervisor when employee was moved to other department
                if (departmentService.isSupervisor(employee.get().getId()) &&
                        !department.get().equals(employee.get().getDepartment())
                ) {
                    departmentService.setSupervisorForDepartment(
                            employee.get().getDepartment().getId(), null);
                }

                employee.get().setDepartment(department.get());
                employee.get().setJobPosition(jobPosition);

                return Optional.of(employeeRepository.save(employee.get()));
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> updateEmailById(Long id, String email) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employee.get().setEmail(email);
            return Optional.of(employeeRepository.save(employee.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> findEmployeeByPersonalId(String personalId) {
        return employeeRepository.findByPersonalId(personalId);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
}
