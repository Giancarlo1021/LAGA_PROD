package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.EmployeeHotelDto;
import COMP380.LAGA.Hotel_API.model.Customer;
import COMP380.LAGA.Hotel_API.model.Employee;
import COMP380.LAGA.Hotel_API.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/with-hotel-info")
    public List<EmployeeHotelDto> getEmployeesWithHotelInfo() {
        return employeeRepository.findAllEmployeesWithHotelInfo();
    }


    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

    @CrossOrigin
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        employee.setRole(updatedEmployee.getRole());
        Employee savedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(savedEmployee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id, @PathVariable Integer employeeId) {
        Employee checkEmployee = employeeRepository.findById(employeeId).orElse(null);
        Employee employee = employeeRepository.findById(id).orElse(null);
        
        String fullCheckEmployeeName = checkEmployee.getFirstName() + " " + checkEmployee.getLastName();
        String fullEmployeeName = employee.getFirstName() + " " + employee.getLastName();
        
        if (checkEmployee != null && employee != null && fullCheckEmployeeName != fullEmployeeName) {
        	employeeRepository.delete(employee);
        	return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
