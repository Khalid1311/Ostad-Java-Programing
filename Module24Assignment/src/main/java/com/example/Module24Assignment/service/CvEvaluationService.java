package com.example.Module24Assignment.service;

import com.example.Module24Assignment.dto.EvaluationResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

@Service
public class CvEvaluationService {

    @Value("${groq.api.key}")
    private String apiKey;
    private String prompt = """
            You are an expert CV evaluator and recruitment consultant.
            
            Your task is to analyze a CV provided as an image and evaluate its quality based on professional hiring standards.
            
            Evaluate the CV across the following dimensions:
            
            1. Formatting & Structure (0-10)
               - Clear sections (Education, Experience, Skills, etc.)
               - Readability and layout
               - Proper alignment and spacing
            
            2. Content Quality (0-10)
               - Clarity of descriptions
               - Use of action verbs
               - Relevance of information
            
            3. Skills & Technical Strength (0-10)
               - Presence of relevant skills
               - Depth of expertise
               - Alignment with industry expectations
            
            4. Experience & Impact (0-10)
               - Quantifiable achievements
               - Real-world impact
               - Internship/project relevance
            
            5. Overall Professionalism (0-10)
               - Grammar and spelling
               - Tone and presentation
               - Completeness
            
            After evaluating all categories:
            - Calculate TOTAL SCORE out of 50
            - Convert it to a percentage (0-100)
            
            IMPORTANT:
            - Be strict and realistic (do not give overly generous scores)
            - Do not assume missing information
            - Base evaluation only on visible content in the CV image
            
            Return your response ONLY in the following JSON format:
            
            {
              "formatting_score": number,
              "content_score": number,
              "skills_score": number,
              "experience_score": number,
              "professionalism_score": number,
              "total_score": number,
              "percentage": number,
              "strengths": ["point1", "point2", "point3"],
              "weaknesses": ["point1", "point2", "point3"],
              "suggestions": ["improvement1", "improvement2", "improvement3"]
            }
            
            Do NOT include any explanation outside JSON.
            Ensure all fields are present.
            Ensure numbers are integers.
            """;

    public EvaluationResponse evaluate(MultipartFile file) throws Exception {
        byte[] imageBytes = file.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        String mimeType = file.getContentType() != null ? file.getContentType() : "image/jpeg";

        String responseBody = callGroq(prompt, base64Image, mimeType);
        System.out.println("Respondbody" + responseBody);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);

        String raw = root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        raw = raw.replace("```json", "").replace("```", "").trim();

        // Validate it's actually JSON before returning
        JsonNode jsonData = mapper.readTree(raw);

        return new EvaluationResponse(jsonData);
    }

    private String callGroq(String prompt, String base64Image, String mimeType) throws Exception {
        String url = "https://api.groq.com/openai/v1/chat/completions";

        ObjectMapper mapper = new ObjectMapper();

        // Build the request body properly using ObjectMapper
        String body = mapper.writeValueAsString(mapper.createObjectNode()
                .put("model", "meta-llama/llama-4-scout-17b-16e-instruct")
                .set("messages", mapper.createArrayNode()
                        .add(mapper.createObjectNode()
                                .put("role", "user")
                                .set("content", mapper.createArrayNode()
                                        .add(mapper.createObjectNode()
                                                .put("type", "text")
                                                .put("text", prompt))
                                        .add(mapper.createObjectNode()
                                                .put("type", "image_url")
                                                .set("image_url", mapper.createObjectNode()
                                                        .put("url", "data:" + mimeType + ";base64," + base64Image)))
                                )
                        )
                )
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}