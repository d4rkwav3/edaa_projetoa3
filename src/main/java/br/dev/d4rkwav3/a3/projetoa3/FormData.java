package br.dev.d4rkwav3.a3.projetoa3;

public record FormData(
    String userName, 
    String period) {

    public FormData(String userName, String period) {
        this.userName = userName.strip();
        this.period = period;
    }

    public String userName() {
        return userName;
    }

    public String period() {
        return period;
    }

}
