package br.com.fabioperettig.domain;

import annotation.TipoChave;
import br.com.fabioperettig.dao.Persistente;

import java.math.BigDecimal;

public class Produto implements Persistente {

    @TipoChave("getCodigo")
    private String codigo;

    private String nome;

    private BigDecimal valor;

    public Produto(String codigo, String nome, BigDecimal valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
