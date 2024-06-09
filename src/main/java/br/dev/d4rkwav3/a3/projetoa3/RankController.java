package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RankController {
    private final LastFmService lastFmService;

    public RankController(LastFmService lastFmService) {
        this.lastFmService = lastFmService;
    }

    @GetMapping("/rank")
    public String createRank(Model model) {
        model.addAttribute("formInválido", lastFmService.isInvalid());
        return "create_rank";
    }
    
    @PostMapping("/rank/create")
    public String postMethodName(@ModelAttribute FormData form) {
        if (form.userName().length() < 3) {
            lastFmService.setInvalid(true);
            return "redirect:/rank";
        } else {
            lastFmService.setInvalid(false);
            return "redirect:/rank";
        }
    }
    
}
