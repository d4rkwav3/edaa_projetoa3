package br.dev.d4rkwav3.a3.projetoa3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final HistoryRepository history;

    public DatabaseService(HistoryRepository history) {
        this.history = history;
    }

    public List<History> getAll() {
        return history.findAll();
    }
}
