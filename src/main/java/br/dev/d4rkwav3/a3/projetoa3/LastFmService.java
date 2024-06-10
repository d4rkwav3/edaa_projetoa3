package br.dev.d4rkwav3.a3.projetoa3;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;


@Service
public class LastFmService {
    private boolean invalid = false;
    private FormData form;
    @Value("${lastfm.apikey}") private String apikey;
    @Value("${lastfm.apiurl}") private String baseUrl;
    // private RestClient httpClient;
    private Collection<Track> tracks;


    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }

    public LastFmService() {
        // this.httpClient = RestClient.builder()
        //     .baseUrl(baseUrl)
        //     .defaultHeader("user-agent", "ProjetoA3")
        //     .build();
        Caller
            .getInstance()
            .setApiRootUrl("https://ws.audioscrobbler.com/2.0/");
    }

    public void getWeeklyTracks(@ModelAttribute FormData form) {
        setTracks(User.getRecentTracks(form.userName(), apikey).getPageResults());
    }

    public FormData getForm() {
        return form;
    }

    public void setForm(FormData form) {
        this.form = form;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public boolean isInvalid() {
        return invalid;
    }
}
