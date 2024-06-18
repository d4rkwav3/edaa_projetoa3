package br.dev.d4rkwav3.a3.projetoa3;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Classe de serviço usuada para realizar as
 * transações com o banco de dados
 * 
 * @author Bruno Venâncio
 */
@Service
public class DatabaseService {

    private final HistoryRepository history;
    private History[] cache;
    private String userInCache;

    /**
     * Getter para saber qual o usuário no qual os
     * dados em cache pertencem
     * @return retorna o nome do usuário com dados em cache
     */
    public String getUserInCache() {
        return userInCache;
    }

    /**
     * Setter para guardar o nome do último usuário que fez
     * a requisição de busca da página history.html
     * @param userInCache
     */
    public void setUserInCache(String userInCache) {
        this.userInCache = userInCache;
    }

    /**
     * Método para retornar o ultimo resultado da memória ao invés]
     * do banco de dados.
     * @return 
     */
    public History[] getCache() {
        return cache;
    }

    /**
     * Salva a pesquisa mais recente em cache
     * @param cache recebe um array de classe History
     */
    public void setCache(History[] cache) {
        this.cache = cache;
    }

    /**
     * Construtor da classe
     * 
     * @param history Injeta um repostório de banco de dados
     */
    public DatabaseService(HistoryRepository history) {
        this.history = history;
    }

    /**
     * Método para pegar todos os dados do banco de dados
     * 
     * @return Retorna todos dados da tabela
     */
    public List<History> getAll() {
        return history.findAll();
    }
}
