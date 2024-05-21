package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {
    private final ObjetiveService objservice;

    public HomeController(ObjetiveService objsvr) {
        this.objservice = objsvr;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("objetivos", objservice.getObjetives());

        return "index";
    }

    @PostMapping("/new-obj")
    public String novoObjetivo(@ModelAttribute Objetive obj) {
        objservice.create(obj);
        
        return "redirect:/";
    }
    
    
}
