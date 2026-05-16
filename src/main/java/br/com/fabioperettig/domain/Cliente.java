package br.com.fabioperettig.domain;

import annotation.TipoChave;
import br.com.fabioperettig.dao.Persistente;

public class Cliente implements Persistente {

    private String nome;

    @TipoChave("getCpf")
    private Long cpf;

    private String email;

    public Cliente(String nome, Long cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email+"@mail";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
