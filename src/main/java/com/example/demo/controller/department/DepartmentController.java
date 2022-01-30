package com.example.demo.controller.department;

import com.example.demo.model.department.Department;
import com.example.demo.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PutMapping("/department/{id}/supervisor")
    public ResponseEntity<?> setSupervisorForDepartment(@PathVariable Long id,
                                                        @RequestParam Long supervisorId) {
            return ResponseEntity.ok(departmentService.setSupervisorForDepartment(id,supervisorId));
    }
    @GetMapping("/department/{id}")
    public ResponseEntity<?> getDepartmentWithId(@PathVariable Long id) {

        Optional<Department> department = departmentService.findDepartmentById(id);
        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        } else {
            return ResponseEntity.badRequest().body("Department not found.");
        }
    }

}
