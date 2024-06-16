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
    private User lastfmUser;


    public User getLastfmUser() {
        return lastfmUser;
    }

    public Collection<Track> getTracks() {
        return tracks;
    }

    public void setTracks(Collection<Track> tracks) {
        this.tracks = tracks;
    }

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

    public void getWeeklyTracks(@ModelAttribute FormData form) {
        setTracks(User.getRecentTracks(form.userName(), 0, 200, apikey).getPageResults());
    }

    public void verifyUser(@ModelAttribute FormData form) {
        this.lastfmUser = User.getInfo(form.userName(), apikey);
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
