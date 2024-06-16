package br.dev.d4rkwav3.a3.projetoa3;

public record FormData(String userName) {

    public FormData(String userName) {
        this.userName = (userName != null) ? userName.strip() : "";
    }

    public String userName() {
        return userName;
    }
}
