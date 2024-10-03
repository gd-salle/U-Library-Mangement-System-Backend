package com.university.librarymanagementsystem;

// import org.springframework.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    String test() {
        return "Test";
    }
}
