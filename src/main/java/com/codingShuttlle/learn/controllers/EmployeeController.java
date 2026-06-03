package com.codingShuttlle.learn.controllers;

import com.codingShuttlle.learn.dto.EmployeeDTO;
import com.codingShuttlle.learn.entities.EmployeeEntity;
import com.codingShuttlle.learn.reprositories.EmployeeRepository;
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

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeEntity employeeById(@PathVariable(name = "employeeId") Long Id){

        return employeeRepository.findById(Id).orElse(null);

    }

   @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required = false) Integer age,
                                @RequestParam(required = false) String sortBy){

        return employeeRepository.findAll();

    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployee){
       return employeeRepository.save(inputEmployee);

    }


}
