package com.codingShuttlle.learn.controllers;

import com.codingShuttlle.learn.dto.EmployeeDTO;
import com.codingShuttlle.learn.entities.EmployeeEntity;
import com.codingShuttlle.learn.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/myname")
//    public String getMyName(){
//        return "My Name is Nishant Kumar";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO employeeById(@PathVariable(name = "employeeId") Long Id){

        return employeeService.getEmployeeById(Id);

    }

   @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false) Integer age,
                                @RequestParam(required = false) String sortBy){

        return employeeService.getAlLEmployees();

    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO  inputEmployee){
       return employeeService.createNewEmployee(inputEmployee);

    }


}
