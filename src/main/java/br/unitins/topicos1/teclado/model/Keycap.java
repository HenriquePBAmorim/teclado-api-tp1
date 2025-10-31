package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Entity;

@Entity
public class Keycap extends DefaultEntity {

    private String nome;
    private String material;
    private PerfilKeycap perfil;
    private String cor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public PerfilKeycap getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilKeycap perfil) {
        this.perfil = perfil;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}