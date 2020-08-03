package com.benwillcabinets.benwillestimator.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin(origins="http://localhost:3000")
@RestController
public class BasicAuthenticationController {

    @GetMapping(path = "/basicauth")
    public String helloWorldBean() {
        return "You are authenticated";
    }
}
