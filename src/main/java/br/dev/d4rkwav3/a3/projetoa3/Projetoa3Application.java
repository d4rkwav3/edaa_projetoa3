package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe de execução do SpringBoot
 * Execute essa classe para dar inicio a aplicação
 * @author Bruno Venâncio
 */
@SpringBootApplication
public class Projetoa3Application {

	/**
	 * Metódo de execução da aplicação
	 * @param args Argumentos extras para a aplicação, desnecessário pois nenhum
	 * argumento é processado.
	 */
	public static void main(String[] args) {
		SpringApplication.run(Projetoa3Application.class, args);
	}

}
