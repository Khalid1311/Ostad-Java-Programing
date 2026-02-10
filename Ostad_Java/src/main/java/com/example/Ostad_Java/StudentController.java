package com.example.Ostad_Java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/info")
    public String Info(){
        return "Name : Khalid bin Jamil\n"
                + "Course : Spring Boot Development\n"
                + "Date : Nov 29, 2010\n"
                + "Hello, fellow developers!";
    }

    @GetMapping("/goal")
    public String Goal(){
        return "My goal is to learn how to build production-ready APIs.";
    }

    @GetMapping("/learning-summary")
    public String learningSummary() {
        return "In Module 1, I learned how to set up the development environment.\n"
                + "Create a Spring Boot project, understand project structure.\n"
                + "Run a basic Spring Boot application.";
    }

    @GetMapping("/review")
    public String review() {
        return "Projects created so far: 2\n"
                + "The last project class was very helpful and beginner-friendly.\n"
                + "Suggestion: More hands-on examples would make learning even better.";
    }
}
