package cat.company.testheroku.testheroku.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController("/")
public class HomeController {
    @GetMapping("/")
    public Person home(HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "carles");
        cookie.setDomain("azurewebsites.net");
        response.addCookie(cookie);
        return new Person("John", "Doe");
    }    
}
