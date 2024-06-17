package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Um simples repositório que serve para fazer a conexão com o
 * banco de dados
 * 
 * @author Bruno Venâncio
 */
public interface HistoryRepository extends JpaRepository<History, Integer> {

}
