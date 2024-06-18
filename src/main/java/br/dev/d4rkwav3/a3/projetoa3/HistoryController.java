package br.dev.d4rkwav3.a3.projetoa3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Classe que controla as ações do template history.html
 * Usa a anotação @Controller do Spring Boot.
 * <p>Todas as requisições REST da página history são feitas
 * aqui.</p>
 * 
 * @author Bruno Venâncio
 */
@Controller
public class HistoryController {
    //private LastFmService lfms;
    private DatabaseService db;

    /**
     * Construtor do controller, injeta a dependência do
     * repositório do banco de dados
     * @param database O repositório do banco de dados é
     * injetado como dependência da classe
     */
    public HistoryController(
        // LastFmService lastFmService,
        DatabaseService database) {
        // this.lfms = lastFmService;
        this.db = database;
    }

    /**
     * Método que mapeia as requisições GET da URL /history
     * Caso nenhum usuário seja passado como parâmetro, retorna
     * o template vázio para pesquisar por usuário, do contrário
     * retorna o template com os dados do usuário
     * 
     * @param user Parâmetro opcional, caso esteja presente
     * busca do banco de dados o usuário do parâmetro.
     * @param model Classe usava para inserir atributos no
     * template history.html
     * @param input Classe usava para vincular o formulário do 
     * template, caso a caixa de pesquisa seja usava, a string é
     * capturada por essa classe
     * @return Retorna o template history.html com alguns atributos
     * adicionados a depender de se um nome de usuário foi recebido ou não
     */
    @GetMapping("/history")
    public String getHistory(
        @RequestParam(required = false) String user, 
        @RequestParam(required = false) String orderby,
        Model model) {

        if (user == null) {
            model.addAttribute("invalid", true);
            model.addAttribute("user", user);
            return "history";
        } else if (user.equals("")) {
            model.addAttribute("invalid", true);
            model.addAttribute("user", null);
            return "history";
        } else {
            List<History> userHistory = new ArrayList<History>();
            History[] tabelaArray;

            if (db.getCache() == null) {
                List<History> historico = db.getAll();
    
                for (History track : historico) {
                    if (track.getUserName().equals(user)) userHistory.add(track);
                }
                
                // Coverte a lista em um array para realizar a ordenação
                tabelaArray = userHistory.toArray(History[]::new);
                db.setCache(tabelaArray);
            } else {
                tabelaArray = db.getCache();
            }       

            if (orderby == null) {
                orderby = "none";
            }

            switch (orderby) {
                case "album":
                    History.ordenarPorAlbum(tabelaArray);
                    break;
                case "artist":
                    History.ordenarPorArtista(tabelaArray);;
                    break;
                case "date":
                    History.ordenarPorData(tabelaArray);
                    break;
                case "music":
                    History.ordenarPorTrack(tabelaArray);
                    break;
                case "none":
                    break;
            }

            model.addAttribute("invalid", false);
            model.addAttribute("user", user);
            model.addAttribute("usr", tabelaArray);

            return "history";
        }

    }
    
}
