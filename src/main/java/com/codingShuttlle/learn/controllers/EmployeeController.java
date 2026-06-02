package com.codingShuttlle.learn.controllers;

import com.codingShuttlle.learn.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

//    @GetMapping(path = "/myname")
//    public String getMyName(){
//        return "My Name is Nishant Kumar";
//    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeDTO employeeById(@PathVariable(name = "employeeId") Long Id){

        return new EmployeeDTO(Id,"Nishant kumar","nishant@gmail.com",20, LocalDate.of(2026,6,2),true);

    }

   @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){

        return "Hi My Age Is" + age + " " + sortBy;

    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        inputEmployee.setId(100L);
        return inputEmployee;

    }


}
