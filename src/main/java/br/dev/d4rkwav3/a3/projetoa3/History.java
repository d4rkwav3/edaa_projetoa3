package br.dev.d4rkwav3.a3.projetoa3;

import java.time.LocalDateTime;
import java.util.Arrays;

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
     * Método de ordenação usando MergeSort para o atributo Track
     * Divide o array em dois se ele tiver mais de um elemento e 
     * chama recursivamente o método auxiliar merge para cada parte
     * @param histories Um array do tipo History (essa própria classe)
     */
    public static void ordenarPorTrack(History[] histories) {
        if (histories.length > 1) {
            int divisao = histories.length / 2;

            History[] esquerda = Arrays.copyOfRange(histories, 0, divisao);
            History[] direita = Arrays.copyOfRange(histories, divisao, histories.length);

            ordenarPorTrack(esquerda);
            ordenarPorTrack(direita);

            mesclar(histories, esquerda, direita);
        }
    }

    /**
     * Método auxiliar para ordenar por track, mescla os dois array
     * comparando a track da esquerda com a direita, e realiza a troca
     * se o noma da faixa for menor em ordem alfabetica.
     * @param histories Um array do tipo History (essa própria classe)
     * @param esquerda Um array do tipo History (dividido anteriormente)
     * @param direita Um array do tipo History (dividido anteriormente)
     */
    private static void mesclar(History[] histories, History[] esquerda, History[] direita) {
        int i = 0, j = 0, k = 0;

        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i].getTrack().compareTo(direita[j].getTrack()) <= 0) {
                histories[k++] = esquerda[i++];
            } else {
                histories[k++] = direita[j++];
            }
        }

        while (i < esquerda.length) {
            histories[k++] = esquerda[i++];
        }

        while (j < direita.length) {
            histories[k++] = direita[j++];
        }
    }

    /**
     * Método principal para a ordenação de um array de History
     * a partir da data (mais antiga para mais atual)
     * @param histories Um array do tipo History (essa própria classe)
     */
    public static void ordenarPorData(History[] histories) {
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
