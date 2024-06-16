package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HistoryController {
    public LastFmService lfms;

    public HistoryController(LastFmService lastFmService) {
        this.lfms = lastFmService;
    }

    @GetMapping("/history")
    public String getHistory(@RequestParam(required = false) String user, Model model, @ModelAttribute FormData input) {

        if (user == null) {
            model.addAttribute("invalid", true);
            model.addAttribute("user", user);
            return "history";
        } else {
            model.addAttribute("invalid", false);
            model.addAttribute("user", user);
            return "history";
        }

    }
    
}
