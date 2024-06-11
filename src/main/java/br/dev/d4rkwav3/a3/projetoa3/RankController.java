package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RankController {
    private final LastFmService lastFmService;
    private boolean resultado = false;

    public boolean isResultado() {
        return resultado;
    }

    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    public RankController(LastFmService lastFmService) {
        this.lastFmService = lastFmService;
    }

    @GetMapping("/rank")
    public String createRank(Model model) {
        model.addAttribute("formInválido", lastFmService.isInvalid());

        // if(lastFmService.getTracks() != null && !lastFmService.getTracks().isEmpty()) {
        //     setResultado(true);
        //     model.addAttribute("resultado", isResultado());
        //     model.addAttribute("tracks", lastFmService.getTracks());
        // }

        // User usr = lastFmService.getLastfmUser();

        // if(usr != null) {
        //     setResultado(true);
        //     model.addAttribute("resultado", isResultado());
        //     model.addAttribute("user", usr);
        // } else if (usr == null) {
        //     usr.
        // }

        return "create_rank";
    }
    
    @PostMapping("/rank/create")
    public String postMethodName(@ModelAttribute FormData form) {
        if (form.userName().length() < 3) {
            lastFmService.setInvalid(true);
            return "redirect:/rank";
        } else {
            lastFmService.verifyUser(form);
            
            if(lastFmService.getLastfmUser() == null) {
                lastFmService.setInvalid(true);
                return "redirect:/rank";
            } else {
                return "redirect:/rank";
            }
    
        }
    }
    
}
