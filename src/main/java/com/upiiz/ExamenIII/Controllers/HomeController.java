package com.upiiz.ExamenIII.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "¡Bienvenido! La API está en funcionamiento.";
    }
}
