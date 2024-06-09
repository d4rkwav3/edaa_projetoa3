package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    public HomeController() {
        
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }   
    
}
