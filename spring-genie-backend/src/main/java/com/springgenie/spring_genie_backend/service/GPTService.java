package com.springgenie.spring_genie_backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class GPTService {

    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash-001:generateContent";

    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    @Value("${gpt.api.key}") // ✅ Fetch API key from application.properties
    private String apiKey;

    public GPTService() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public String generateCode(String prompt) throws IOException {
        String json = String.format("""
            {
              "contents": [
                {
                  "parts": [
                    {
                      "text": "%s"
                    }
                  ]
                }
              ]
            }
            """, prompt);

        Request request = new Request.Builder()
                .url(GEMINI_API_URL + "?key=" + apiKey)
                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Gemini API error: " + response.code() + " - " + response.body().string());
            }

            String responseBody = response.body().string();
            JsonNode root = objectMapper.readTree(responseBody);
            return root.path("candidates").get(0)
                    .path("content")
                    .path("parts").get(0)
                    .path("text").asText();
        }
    }

    public String generateSpringComponents(String entityName, Map<String, String> fields) throws IOException {
        StringBuilder fieldBuilder = new StringBuilder();
        fields.forEach((name, type) ->
                fieldBuilder.append("- `").append(name).append("`: `").append(type).append("`\n"));

        String prompt = String.format("""
            Generate the following Java files for a Spring Boot project:
            1. A JPA Entity class named `%s`
            2. A Repository interface extending `JpaRepository`
            3. A Service class with CRUD methods using the Repository
            
            Fields for the entity:
            %s
            
            Use appropriate annotations like @Entity, @Id, @GeneratedValue, and mark the service with @Service.
            Return only clean code blocks for each class, separated clearly with headers like `// Entity`, `// Repository`, and `// Service`.
            """, entityName, fieldBuilder);

        return generateCode(prompt);
    }
}
