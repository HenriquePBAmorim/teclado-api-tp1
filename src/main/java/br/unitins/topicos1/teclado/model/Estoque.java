package br.unitins.topicos1.teclado.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;

@Entity
public class Estoque extends DefaultEntity {

    private Integer quantidade;
    private LocalDate dataAtualizacao;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}