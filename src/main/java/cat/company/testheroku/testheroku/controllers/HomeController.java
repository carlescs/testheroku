package cat.company.testheroku.testheroku.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController("/")
public class HomeController {
    @GetMapping("/")
    public Person home(HttpServletResponse response) {
        Cookie cookie = new Cookie("test", "carles");
        cookie.setDomain("company.cat");
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return new Person("John", "Doe");
    }    

    @PostMapping("/set-cookie")
    @CrossOrigin(origins = "https://testfront.company.cat")
    public Cookie setFromFrontend(@RequestBody NewCookie newCookie, HttpServletResponse response) {
        Cookie cookie = new Cookie(newCookie.getName(), newCookie.getValue());
        cookie.setDomain("company.cat");
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return cookie;
    }

    @GetMapping("/test")
    public Cookie[] test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        return cookies;
    }
}
