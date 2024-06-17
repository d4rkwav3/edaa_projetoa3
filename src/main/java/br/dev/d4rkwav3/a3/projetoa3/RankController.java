package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import de.umass.lastfm.User;

/**
 * Classe que controla as ações do template create_rank.html
 * Usa a anotação @Controller do Spring Boot.
 * <p>Todas as requisições REST da página create_rank são feitas
 * aqui.</p>
 * 
 * @author Bruno Venâncio
 */
@Controller
public class RankController {
    private final LastFmService lastFmService;
    private boolean resultado = false;

    /**
     * Getter do atributo resultado, usado para
     * mostrar ou esconder algumas tags do template
     * @return retorna true ou false
     */
    public boolean isResultado() {
        return resultado;
    }

    /**
     * Setter do atributo resultado, usado para
     * mostrar ou esconder algumas tags do template
     */
    public void setResultado(boolean resultado) {
        this.resultado = resultado;
    }

    /**
     * Costrutor da classe, injeta a dependência LastFmService
     * @param lastFmService Dependência que faz a comunicação com
     * a API da Last.FM
     */
    public RankController(LastFmService lastFmService) {
        this.lastFmService = lastFmService;
    }

    /**
     * Retorna a página para se criar um rank semanal
     * 
     * @param model Classe usada para inserir atributos no template
     * @return Retorna o template create_rank.html
     */
    @GetMapping("/rank")
    public String createRank(Model model) {
        model.addAttribute("formInválido", lastFmService.isInvalid());

        // if(lastFmService.getTracks() != null && !lastFmService.getTracks().isEmpty()) {
        //     setResultado(true);
        //     model.addAttribute("resultado", isResultado());
        //     model.addAttribute("tracks", lastFmService.getTracks());
        // }

        User usr = lastFmService.getLastfmUser();

        if(usr != null) {
            setResultado(true);
            model.addAttribute("resultado", isResultado());
            model.addAttribute("user", usr);
        } else if (usr == null) {
            lastFmService.setInvalid(true);
        }

        return "create_rank";
    }
    
    /**
     * Método usado para pegas as faixas mais recentes do usuário
     * e salvar no banco de dados, porém ainda não foi completamente
     * implementada, portanto apenas redireciona para o paǵina de 
     * histórico do usuário se o mesmo já existir no banco de dados.
     * @param form Classe FormData usada para capturar a string do 
     * formulário do template
     * @return Se o nome de usuário for inválido retorna o template 
     * create_rank com um texto de error, do contrário redireciona
     * para a página de histórico do usuário
     */
    @PostMapping("/rank/create")
    public String postMethodName(@ModelAttribute FormData form) {
        lastFmService.verifyUser(form);
        
        if(lastFmService.getLastfmUser() == null) {
            lastFmService.setInvalid(true);
            return "redirect:/rank";
        } else {
            return "redirect:/history?user=" + form.userName();
        }
    }
    
}
