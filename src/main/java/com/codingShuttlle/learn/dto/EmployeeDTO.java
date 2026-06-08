package com.codingShuttlle.learn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class EmployeeDTO {

        private Long id;
        private String name;
        private String email;
        private Integer age;
        private LocalDate dateOfJoining;
        @JsonProperty("isActive")
        private Boolean isActive;




}
