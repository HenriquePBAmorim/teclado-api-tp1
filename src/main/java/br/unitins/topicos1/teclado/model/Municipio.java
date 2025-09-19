package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Entity;

@Entity
public class Municipio extends DefaultEntity {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}