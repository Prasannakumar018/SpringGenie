package com.springgenie.spring_genie_backend.controller;

import com.springgenie.spring_genie_backend.dto.GenerateRequest;
import com.springgenie.spring_genie_backend.service.CodeGenerationService;
import com.springgenie.spring_genie_backend.service.GPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
//
//@RestController
//@RequestMapping("/api/code")
//public class CodeGenerateController {
//
//    @Autowired
//    private final GPTService gptService;
//
//    @Autowired
//    private final CodeGenerationService codeGenerationService;
//
//    // Constructor Injection (best practice)
//    public CodeGenerateController(GPTService gptService) {
//        this.gptService = gptService;
//        this.codeGenerationService = codeGenerationService;
//    }
//
//    @PostMapping("/code")
//    public Map<String, String> generateCode(@RequestBody GenerateRequest request) {
//        return codeGenerationService.generateCode(request);
//    }
//
//    @PostMapping("/spring")
//    public ResponseEntity<String> generateSpringCode(@RequestBody GenerateRequest request) {
//        System.out.println("Received entityName: " + request.getEntityName());
//        System.out.println("Received fields: " + request.getFields());
//
//        try {
//            String code = gptService.generateSpringComponents(request.getEntityName(), request.getFields());
//            return ResponseEntity.ok(code);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error generating code: " + e.getMessage());
//        }
//    }
//}

@RestController
@RequestMapping("/api/code")
public class CodeGenerateController {

    private final GPTService gptService;
    private final CodeGenerationService codeGenerationService;

    @Autowired
    public CodeGenerateController(GPTService gptService, CodeGenerationService codeGenerationService) {
        this.gptService = gptService;
        this.codeGenerationService = codeGenerationService;
    }

    @PostMapping("/code")
    public Map<String, String> generateCode(@RequestBody GenerateRequest request) {
        return codeGenerationService.generateCode(request);
    }

    @PostMapping("/spring")
    public ResponseEntity<String> generateSpringCode(@RequestBody GenerateRequest request) {
        System.out.println("Received entityName: " + request.getEntityName());
        System.out.println("Received fields: " + request.getFields());

        try {
            String code = gptService.generateSpringComponents(request.getEntityName(), request.getFields());
            return ResponseEntity.ok(code);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating code: " + e.getMessage());
        }
    }
}

