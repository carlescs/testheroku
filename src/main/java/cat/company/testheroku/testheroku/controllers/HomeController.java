package cat.company.testheroku.testheroku.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController("/")
public class HomeController {
    @GetMapping("/")
    public Person home(HttpServletResponse response) {
        response.addCookie(new Cookie("test", "test"));
        return new Person("John", "Doe");
    }    
}
