package com.springgenie.spring_genie_backend.service;

import com.springgenie.spring_genie_backend.dto.GenerateRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

//public class CodeGenerationService {
//}
@Service
public class CodeGenerationService {

    public Map<String, String> generateCode(GenerateRequest request) {
        String entityCode = generateEntity(request.getEntityName(), request.getFields());
        String repositoryCode = generateRepository(request.getEntityName());
        String serviceCode = generateService(request.getEntityName());

        Map<String, String> codeMap = new LinkedHashMap<>();
        codeMap.put(request.getEntityName() + ".java", entityCode);
        codeMap.put(request.getEntityName() + "Repository.java", repositoryCode);
        codeMap.put(request.getEntityName() + "Service.java", serviceCode);

        return codeMap;
    }

    private String generateEntity(String entityName, Map<String, String> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("import javax.persistence.*;\n\n");
        sb.append("@Entity\npublic class ").append(entityName).append(" {\n\n");

        sb.append("    @Id\n    @GeneratedValue(strategy = GenerationType.IDENTITY)\n    private Long id;\n\n");

        fields.forEach((name, type) -> {
            sb.append("    private ").append(type).append(" ").append(name).append(";\n\n");
        });

        sb.append("    // Getters, setters, constructors...\n");
        sb.append("}");

        return sb.toString();
    }

    private String generateRepository(String entityName) {
        return "import org.springframework.data.jpa.repository.JpaRepository;\n\n" +
                "public interface " + entityName + "Repository extends JpaRepository<" + entityName + ", Long> {\n}";
    }

    private String generateService(String entityName) {
        String lcEntity = entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
        return "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import java.util.*;\n\n" +
                "@Service\n" +
                "public class " + entityName + "Service {\n\n" +
                "    private final " + entityName + "Repository " + lcEntity + "Repository;\n\n" +
                "    @Autowired\n" +
                "    public " + entityName + "Service(" + entityName + "Repository " + lcEntity + "Repository) {\n" +
                "        this." + lcEntity + "Repository = " + lcEntity + "Repository;\n" +
                "    }\n\n" +
                "    // CRUD methods...\n" +
                "}";
    }
}
