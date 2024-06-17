package br.dev.d4rkwav3.a3.projetoa3;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de modelo da tabela do banco de dados.
 * As anotações @NoArgsConstructor e @AllArgsConstructor geram
 * um construtores para nenhum e todos os argumentos.
 * A anotação @Data cria diversos métodos como toString e outros
 * automaticamente.
 * As demais anotações usam a classe de modelo para o banco de dados.
 * 
 * @author Bruno Venâncio
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "history")
public class History {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username")
    private String userName;
    private String artist;
    private String track;
    private String album;
    @Column(name = "dateTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    /**
     * Getter para o nome de usuário
     * @return retorna o nome do usuário
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Getter para o nome do artista
     * @return retorna o nome do artista
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Getter para o nome da faixa
     * @return retorna o nome da faixa/música
     */
    public String getTrack() {
        return track;
    }

    /**
     * Getter para o nome do álbum
     * @return retorna o nome do álbum 
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Getter para a data e hora de audição
     * @return retorna a data/hora que a faixa foi registrada
     */
    public LocalDateTime getDate() {
        return date;
    }
}
