package br.dev.d4rkwav3.a3.projetoa3;

/**
 * Um java record (objeto imutável) para guardar um nome 
 * de usuário para o formulário do template create_rank
 * 
 * @author Bruno Venâncio
*/
public record FormData(String userName) {

    /**
     * Construtor do record
     * @param userName Se o nome de usuário for nula, retorna uma string vázio, do contrário
     * retorna a string removendo espaços em branco no início e final e em caixa baixa.
     */
    public FormData(String userName) {
        this.userName = (userName != null) ? userName.strip().toLowerCase() : "";
    }

    /**
     * Getter para acessar o único atributo do record
     * @return retorna o nome de usuário
     */
    public String userName() {
        return userName;
    }
}
