package com.upiiz.ExamenIII.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String mostrarPaginaOrden() {
        return "orden"; // Busca templates/orden.html
    }
}
