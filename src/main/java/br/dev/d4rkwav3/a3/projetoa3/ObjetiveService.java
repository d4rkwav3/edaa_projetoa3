package br.dev.d4rkwav3.a3.projetoa3;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ObjetiveService {
    private List<Objetive> objs = List.of(
        new Objetive("Nesse projeto devemos trabalhar com a api da LastFM"),
        new Objetive("Buscar o histórico recente de usuários e salvar num banco de dados local"),
        new Objetive("buscar os scroobles e organar os dados pra gerar informações interessantes!")
    );

    public List<Objetive> getObjetives() {
        return objs;
    }
}
