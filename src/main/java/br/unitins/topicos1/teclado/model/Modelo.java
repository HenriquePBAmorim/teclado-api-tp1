package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Modelo extends DefaultEntity {

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}