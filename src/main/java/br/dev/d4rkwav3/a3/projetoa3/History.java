package br.dev.d4rkwav3.a3.projetoa3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
 * Classe de modelo da tabela do banco de dados e onde os métodos
 * de busca e ordenação estão disponíveis estaticamente.
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
public class History implements Comparable<History> {
    
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

    @Override
    public int compareTo(History faixa) {
        return this.track.compareTo(faixa.track);
    }

    /**
     * Método de ordenação por album usando o método Selection Sort
     * @param histories Um array do tipo History (essa própria classe)
     */
    public static void ordenarPorAlbum(History[] histories) {
        int tamanho = histories.length;
        for (int i = 0; i < tamanho - 1; i++) {
            // Encontra o menor elemento no subarray não ordenado
            int minIndex = i;
            for (int j = i + 1; j < tamanho; j++) {
                if (histories[j].getAlbum().compareTo(histories[minIndex].getAlbum()) < 0) {
                    minIndex = j;
                }
            }
            // Troca o menor elemento encontrado com o primeiro elemento do subarray não ordenado
            History temp = histories[minIndex];
            histories[minIndex] = histories[i];
            histories[i] = temp;
        }
    }

    /**
     * Método de ordenação por artista usando o método Insertion Sort
     * @param histories Um array do tipo History (essa própria classe)
     */
    public static void ordenarPorArtista(History[] histories) {
        for (int i = 1; i < histories.length; i++) {
            History ref = histories[i];
            int j = i - 1;

            // Move os elementos do array que são maiores que a ref (referência) para uma posição à frente da sua posição atual
            while (j >= 0 && histories[j].getArtist().compareTo(ref.getArtist()) > 0) {
                histories[j + 1] = histories[j];
                j = j - 1;
            }
            histories[j + 1] = ref;
        }
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
     * @param histories O array que contém os elementos
     * @param i A posição que irá "pra frente" no array
     * @param j A posição que irá "pra trás" no array
     */
    private static void inverter(History[] histories, int i, int j) {
        History temp = histories[i];
        histories[i] = histories[j];
        histories[j] = temp;
    }

    /**
     * Método que busca faixas em uma lista usando o método de busca
     * binária
     * @param history Uma lista com objetos dessa classe
     * @param track Uma string com o nome exato da faixa
     * @return Retorna uma lista com todos os objetos do tipo History
     * que contém um atributo track de nome identido ao parâmetro track
     */
    public static List<History> buscaPorFaixa(List<History> history, String track) {
        List<History> resultaddo = new ArrayList<>();
        int esquerda = 0;
        int direita = history.size() - 1;
        int centro = -1;

        // Busca binária para encontrar uma ocorrência
        while (esquerda <= direita) {
            centro = esquerda + (direita - esquerda) / 2;
            History meio = history.get(centro);
            int comparacao = (meio.getTrack() == null) ? -1 : meio.getTrack().compareTo(track);
            if (comparacao == 0) {
                break;
            } else if (comparacao < 0) {
                esquerda = centro + 1;
            } else {
                direita = centro - 1;
            }
        }

        if (esquerda > direita) {
            return resultaddo; // Nenhum resultado encontrado
        }

        // Encontrar todas as ocorrências à esquerda
        int temp = centro;
        while (temp >= 0 && track.equals(history.get(temp).getTrack())) {
            resultaddo.add(history.get(temp));
            temp--;
        }

        // Encontrar todas as ocorrências à direita
        temp = centro + 1;
        while (temp < history.size() && track.equals(history.get(temp).getTrack())) {
            resultaddo.add(history.get(temp));
            temp++;
        }

        return resultaddo;
    }
}
