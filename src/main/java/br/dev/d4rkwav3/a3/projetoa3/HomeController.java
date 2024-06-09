package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Classe responsável pela página inicial do projeto
 * <p>Todas as requisições Rest da página inicial são feitas aqui</p>
 * @author Bruno Venâncio
 */
@Controller
public class HomeController {

    private String[] membros = {
        "Bruno Venâncio de Souza e Silva, RA: 821135934",
        "Henrick Melo Vital, RA: 821224905"
    };

    /**
     * @return Array de strings com os nomes e ra dos membros do grupo
     */
    public String[] getMembros() {
        return membros;
    }

    public HomeController() {}

    /**
     * Retorna a home page ao acessar o endereço raiz
     * 
     * @param Model adiciona um atributo chamado membros a partir 
     * do array de membros da classe através do método getMembros.
     * @return Retorna o template index.html
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("membros", getMembros());
        return "index";
    }   
    
}
