package br.dev.d4rkwav3.a3.projetoa3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HistoryController {
    //private LastFmService lfms;
    private DatabaseService db;

    public HistoryController(
        // LastFmService lastFmService,
        DatabaseService database) {
        // this.lfms = lastFmService;
        this.db = database;
    }

    @GetMapping("/history")
    public String getHistory(
        @RequestParam(required = false) String user, 
        Model model, 
        @ModelAttribute FormData input) {

        if (user == null) {
            model.addAttribute("invalid", true);
            model.addAttribute("user", user);
            return "history";
        } else {
            List<History> historico = db.getAll();
            List<History> userHistory = new ArrayList<History>();

            for (History track : historico) {
                if (track.getUserName().equals(user)) userHistory.add(track);
            }

            model.addAttribute("invalid", false);
            model.addAttribute("user", user);
            // model.addAttribute("hist", historico);
            model.addAttribute("usr", userHistory);
            // History track = historico.get(0);
            // track.getT
            return "history";
        }

    }
    
}
