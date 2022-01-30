package com.example.demo.service.department;

import com.example.demo.model.department.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Optional<Department> findDepartmentFromJobPosition(String position) {
        List<Department> departments = departmentRepository.findAll();

        for (Department department: departments) {
            if (Arrays.asList(department.getJobPosition()).contains(position)) {
                return Optional.of(department);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> setSupervisorForDepartment(Long departmentId, Long employeeId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (department.isEmpty()) {
            return Optional.empty();
        }
        department.get().setSupervisorId(employeeId);
        return Optional.of(departmentRepository.save(department.get()));
    }

    @Override
    public boolean isSupervisor(Long employeeId) {
        return departmentRepository.findBySupervisorId(
                employeeId).isPresent();
    }
}
