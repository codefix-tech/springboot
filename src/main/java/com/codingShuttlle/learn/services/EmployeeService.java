package com.codingShuttlle.learn.services;


import com.codingShuttlle.learn.dto.EmployeeDTO;
import com.codingShuttlle.learn.entities.EmployeeEntity;
import com.codingShuttlle.learn.reprositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }



    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);

    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {

       Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);

        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));

    }

    public List<EmployeeDTO> getAlLEmployees() {

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());

    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

      public boolean isExitsEmployeeById (Long employeeId){
             return employeeRepository.existsById(employeeId);
      }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExitsEmployeeById(employeeId);
        if(!exists) return false;
       employeeRepository.deleteById(employeeId);
       return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExitsEmployeeById( employeeId);
        if(!exists) return null ;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
         updates.forEach((field,value) -> {
             Field fieldTOBeUpdated = org.springframework.data.util.ReflectionUtils.getRequiredField(EmployeeEntity.class,field);
             fieldTOBeUpdated.setAccessible(true);
             ReflectionUtils.setField(fieldTOBeUpdated,employeeEntity,value);
        });
             return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
