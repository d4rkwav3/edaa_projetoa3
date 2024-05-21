package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final ObjetiveService objsrv;

    public HomeController(ObjetiveService objsvr) {
        this.objsrv = objsvr;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("objetivos", objsrv.getObjetives());

        return "index";
    }
    
}
