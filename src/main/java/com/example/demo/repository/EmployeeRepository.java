package com.example.demo.repository;

import com.example.demo.model.employee.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Override
    void deleteById(Long id);

    @Override
    Optional<Employee> findById(Long id);

    Page<Employee> findAll();

    Optional<Employee> findByPersonalId(String personalId);

    Optional<Employee> findByEmail(String email);
    Page<Employee> findAllByJobPosition(String jobPosition, Pageable pageable);

}
