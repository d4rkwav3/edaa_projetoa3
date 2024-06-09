package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Classe responsável pela página inicial do projeto
 * <p>Todas as requisições Rest da página inicial são feitas aqui</p>
 * @author Bruno Venâncio
 */
@Controller
public class HomeController {
    
    public HomeController() {
        
    }

    /**
     * Retorna a home page ao acessar o endereço raiz
     * 
     * 
     * @return Retorna o template index.mustache
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }   
    
}
