package com.example.demo.controller.employee;

import com.example.demo.model.employee.Employee;
import com.example.demo.service.employee.EmployeeService;
import com.example.demo.utils.PersonalIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestParam String firstName,
                                      @RequestParam String lastName,
                                      @RequestParam String personalId,
                                      @RequestParam String email,
                                      @RequestParam String jobPosition) throws ParseException {


        if (personalId.length() != 11) {
            return ResponseEntity.badRequest().body("Personal id should have 11 digits.");
        }
        try {
            Long.parseLong(personalId);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Personal id should only contains digits.");
        }

        if (!PersonalIdUtils.isValidPersonalId(personalId)) {
            return ResponseEntity.badRequest().body("Invalid personal id.");
        }
        if(employeeService.findEmployeeByPersonalId(personalId).isPresent()) {
            return ResponseEntity.badRequest().body("Personal id already taken");
        }

        if (employeeService.findEmployeeByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email already taken.");
        }
        String sex = PersonalIdUtils.getSexFromPersonalId(personalId);
        LocalDate birthDate = PersonalIdUtils.getDateFromPersonalId(personalId);

        Optional<Employee> employee = employeeService.addEmployee(firstName, lastName,
                sex, birthDate,
                personalId, email, jobPosition);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return ResponseEntity.badRequest().body("Invalid employee data.");
        }
    }

    @GetMapping("/employee")
    public ResponseEntity<?> getAllEmployee(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(employeeService.findAllEmployees(page, size));
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            return new ResponseEntity<>("Employee not found.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully.");
    }

    @GetMapping("/employee/job_position")
    public ResponseEntity<?> getEmployeesByJobPosition(@RequestParam String jobPosition,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(employeeService.findEmployeesByJobPosition(jobPosition, page,size));
    }

    @PutMapping("/employee/{id}/job_position")
    public ResponseEntity<?> changeEmployeeJobPosition(@PathVariable Long id,
                                                       @RequestParam String jobPosition) {
        Optional<Employee> employee = employeeService.updateJobPositionById(id, jobPosition);

        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid id or job position.");
        } else {
            return ResponseEntity.ok(employee.get());
        }
    }

    @PutMapping("/employee/{id}/email")
    public ResponseEntity<?> changeEmployeeEmail(@PathVariable Long id,
                                                       @RequestParam String email) {
        Optional<Employee> employee = employeeService.updateEmailById(id, email);

        if (employee.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid id or email.");
        } else {
            return ResponseEntity.ok(employee.get());
        }
    }
}
