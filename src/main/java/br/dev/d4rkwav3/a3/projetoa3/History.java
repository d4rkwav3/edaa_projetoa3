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
    @Column(name = "datetime", columnDefinition = "TIMESTAMP")
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

    /**
     * Método principal para a ordenação de um array de History
     * a partir da data (mais antiga para mais atual)
     * @param histories Um array do tipo History (essa própria classe)
     */
    public static void sortByDate(History[] histories) {
        quickSort(histories, 0, histories.length - 1);
    }

    /**
     * Implementação do quicksort por data, enquanto o início for menor 
     * que o fim, particiona o array em duas partes e as ordena recursivamente
     * @param histories Um array do tipo History (essa própria classe)
     * @param inicio o primeiro elemento do array
     * @param fim o ultimo elemento do array
     */
    private static void quickSort(History[] histories, int inicio, int fim) {
        if (inicio < fim) {
            int pivot = particionar(histories, inicio, fim);
            quickSort(histories, inicio, pivot - 1);
            quickSort(histories, pivot + 1, fim);
        }
    }

    /**
     * Método para dividir um array de History em dois, seleciona o último
     * elemento como pivot, itera pelo array e troca a posição dos elementos
     * caso o elemento seguinte é de uma data anterior a data do elemento atual
     * de modo que os elementos da data mais antiga ficam a esquerda
     * @param histories O array que será particionado
     * @param inicio O primeiro elemento do array
     * @param fim O último elemento do array
     * @return
     */
    private static int particionar(History[] histories, int inicio, int fim) {
        LocalDateTime pivot = histories[fim].getDate();
        int i = (inicio - 1);
        for (int j = inicio; j < fim; j++) {
            if (histories[j].getDate().isBefore(pivot)) {
                i++;
                inverter(histories, i, j);
            }
        }
        inverter(histories, i + 1, fim);
        return i + 1;
    }

    /**
     * Método auxiliar para a função de ordenação quicksort
     * Troca as posições de dois objetos de um array de History
     * @param histories 
     * @param i A posição que irá "pra frente" no array
     * @param j A posição que irá "pra trás" no array
     */
    private static void inverter(History[] histories, int i, int j) {
        History temp = histories[i];
        histories[i] = histories[j];
        histories[j] = temp;
    }
}
