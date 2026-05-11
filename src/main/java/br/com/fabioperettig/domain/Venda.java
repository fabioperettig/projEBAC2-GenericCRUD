package br.com.fabioperettig.domain;

import annotation.TipoChave;
import br.com.fabioperettig.dao.Persistente;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Venda implements Persistente {

    public enum Status {
        INICIADA, CONLCUIDA, CANCELADA;
    }

    @TipoChave("getCodigo")
    private String codigo;

    private Cliente cliente;
    private Set<ProdutoQtd> produtos;
    private BigDecimal valorTotal;
    private Instant dataVenda;
    private Status status;

    public Venda() {
        produtos =  new HashSet<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoQtd> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto, Integer qtd){
        validarStatus();
        Optional<ProdutoQtd> op = produtos.stream()
                .filter(f -> f.getProduto().getCodigo().equals(produto.getCodigo()))
                .findAny();

        if (op.isPresent()){
            ProdutoQtd produtoQtd = op.get();
            produtoQtd.adicionar(qtd);
        } else {
            ProdutoQtd produtoQtd = new ProdutoQtd();
            produtoQtd.setProduto(produto);
            produtoQtd.adicionar(qtd);
        }
        recalcularValorTotalVenda();
    }

    private void validarStatus(){
        if (this.status == Status.CONLCUIDA){
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    public void removerProduto(Produto produto, Integer qtd){
        validarStatus();
        Optional<ProdutoQtd> op = produtos.stream()
                .filter(f -> f.getProduto().getCodigo().equals(produto.getCodigo()))
                .findAny();

        if (op.isPresent()){
            ProdutoQtd produtoQtd = op.get();
            if (produtoQtd.getQuantidade() > qtd){
                produtoQtd.remover(qtd);
                recalcularValorTotalVenda();
            } else {
                produtos.remove(op.get());
                recalcularValorTotalVenda();
            }
        }
    }

    public void zerarProdutos(){
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public Integer getQtdTotal(){
        return produtos.stream()
                .reduce(0,(partialCountResult, prod)
                        -> partialCountResult + prod.getQuantidade(), Integer::sum);
    }

    private void recalcularValorTotalVenda(){
        validarStatus();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ProdutoQtd pQtd : this.produtos){
            valorTotal = valorTotal.add(pQtd.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
