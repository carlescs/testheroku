package cat.company.testheroku.testheroku.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HomeController {
    @GetMapping("/")
    public Person home() {
        return new Person("John", "Doe");
    }    
}
