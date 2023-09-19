package cat.company.testheroku.testheroku.controllers;

import java.io.InputStream;
import java.util.*;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @CrossOrigin(origins = {"https://testfront.company.cat","http://localhost:64860"}, allowCredentials = "true")
    public Cookie setFromFrontend(@RequestBody NewCookie newCookie, HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(newCookie.getName(), newCookie.getValue());
        cookie.setDomain("company.cat");
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        if(!request.getRemoteHost().endsWith("company.cat"))
            cookie.setAttribute("SameSite", "None");
        response.addCookie(cookie);
        return cookie;
    }

    @GetMapping("/test")
    public Cookie[] test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        return cookies;
    }

    @GetMapping("/file")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(HttpServletRequest request){
        if(!Arrays.stream(request.getCookies()).anyMatch(cookie->cookie.getName().equals("request")&&cookie.getValue().equals("secure")))
            throw new ForbiddenException();
        MediaType type=MediaType.IMAGE_PNG;
        InputStream in=getClass().getResourceAsStream("/images/test.png");
        return ResponseEntity.ok()
            .contentType(type)
            .body(new InputStreamResource(in));
    }
}
