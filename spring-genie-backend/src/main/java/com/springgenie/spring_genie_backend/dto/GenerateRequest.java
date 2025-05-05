package com.springgenie.spring_genie_backend.dto;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateRequest {
    private String entityName;
    private Map<String, String> fields;
}
