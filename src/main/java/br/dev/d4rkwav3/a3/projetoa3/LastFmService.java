package br.dev.d4rkwav3.a3.projetoa3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestClient;

@Service
public class LastFmService {
    private boolean invalid = false;
    private FormData form;
    @Value("${lastfm.apikey}") private String apikey;
    @Value("${lastfm.apiurl}") private String baseUrl;
    //private RestClient httpClient;

    public LastFmService() {
        // this.httpClient = RestClient.builder()
        //     .baseUrl(baseUrl)
        //     .defaultHeader("user-agent", "ProjetoA3")
        //     .build();
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
