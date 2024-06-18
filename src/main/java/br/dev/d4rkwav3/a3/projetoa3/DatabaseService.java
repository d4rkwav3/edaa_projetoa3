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

    public String getUserInCache() {
        return userInCache;
    }

    public void setUserInCache(String userInCache) {
        this.userInCache = userInCache;
    }

    public History[] getCache() {
        return cache;
    }

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
