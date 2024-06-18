package br.dev.d4rkwav3.a3.projetoa3;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Classe responsável pela página de busca de faixas, utiliza a 
 * anotação @Controller do Spring Boot.
 * <p>Todas as requisições Rest do templeta search.html são feitas aqui</p>
 * 
 * @author Bruno Venâncio
 */
@Controller
public class SearchController {

    private DatabaseService db;

    public SearchController(DatabaseService database) {
        this.db = database;
    }

    @GetMapping("/search")
    public String getTracksByName(
        @RequestParam(required = false) String track,
        Model model) {

        if (track == null || track.equals("")) {
            model.addAttribute("track", "");
            return "search";

        } else {
            List<History> tabela = db.getAll();
            History[] tabelaOrdenada = tabela.toArray(History[]::new);
            History.ordenarPorTrack(tabelaOrdenada);
            List<History> resultado = History.buscaPorFaixa(Arrays.asList(tabelaOrdenada), track);

            model.addAttribute("track", track);
            model.addAttribute("result", resultado);

            return "search";
        }

    }
    
}
