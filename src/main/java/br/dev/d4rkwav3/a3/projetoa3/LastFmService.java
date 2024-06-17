package br.dev.d4rkwav3.a3.projetoa3;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;

/**
 * Classe de serviço para realizar a comunição com a API
 * da Last.FM, utiliza a anotação @Service do Spring Boot.
 * <p>Utilizada pela Classe RankController</p>
 * 
 * @author Bruno Venâncio
 */
@Service
public class LastFmService {
    private boolean invalid = false;
    private FormData form;
    @Value("${lastfm.apikey}") private String apikey;
    @Value("${lastfm.apiurl}") private String baseUrl;
    // private RestClient httpClient;
    private Collection<Track> tracks;
    private User lastfmUser;

    /**
     * Getter de um usuário da Last.FM
     * @return retorna informações básicas de um usuário da Last.FM
     */
    public User getLastfmUser() {
        return lastfmUser;
    }

    /**
     * Método que retorna uma coleção de faixas
     * @return retorna uma coleção de faíxas
     */
    public Collection<Track> getTracks() {
        return tracks;
    }

    /**
     * Método setter para salvar uma coleção de faixas
     * @param tracks Receba uma coleção de faixas da API
     * da Last.FM e salva no atributo tracks
     */
    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }

    /**
     * Construtor da classe, injeta a dependência do
     * mecanismo de consulta da Last.FM
     */
    public LastFmService() {
        if (this.baseUrl == null) {
            Caller
                .getInstance()
                .setApiRootUrl("https://ws.audioscrobbler.com/2.0/");
        } else {
            Caller
            .getInstance()
            .setApiRootUrl(baseUrl);
        }
        
    }

    /**
     * Método que solicita as faixas mais recentes e 
     * salva no atributo tracks
     * @param form Usado para capturar a string de um
     * formulário em um record FormData
     */
    public void getWeeklyTracks(@ModelAttribute FormData form) {
        setTracks(User.getRecentTracks(form.userName(), 0, 200, apikey).getPageResults());
    }

    /**
     * Método para verificar se um usuário existe e salva
     * o resultado no atributo lastfmUser
     * @param form Capturar uma string de um formulário do
     * template em um record FormData
     */
    public void verifyUser(@ModelAttribute FormData form) {
        this.lastfmUser = User.getInfo(form.userName(), apikey);
    }

    /**
     * Getter para o atributo form
     * @return retorna um record FormData
     */
    public FormData getForm() {
        return form;
    }

    /**
     * Setter para o atributo form, usado para salvar
     * um nome de usuário temporariamente
     * @param form classe record para irá ser salva na
     * classe
     */
    public void setForm(FormData form) {
        this.form = form;
    }

    /**
     * Setter para o atributo invalid, usado para salvar
     * temporariamente se o nome de usuário pesquisado foi
     * válido ou não
     * @param invalid Valor booleano para definir o atributo
     */
    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    /**
     * Getter do atributo invalid, usado para verificar se
     * um nome de usuário digitado foi válido ou não, isto é,
     * se ele exista na Last.FM
     * @return Retorna true caso exista e false caso contrário.
     */
    public boolean isInvalid() {
        return invalid;
    }
}
