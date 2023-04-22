package COMP380.LAGA.Hotel_API.controller;

import COMP380.LAGA.Hotel_API.dto.EmployeeHotelDto;
import COMP380.LAGA.Hotel_API.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @CrossOrigin
    @GetMapping("/with-hotel-info")
    public List<EmployeeHotelDto> getEmployeesWithHotelInfo() {
        return employeeRepository.findAllEmployeesWithHotelInfo();
    }
}
