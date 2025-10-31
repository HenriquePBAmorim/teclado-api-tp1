package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Entity;

@Entity
public class Acessorio extends Produto {

    private String compatibilidade;
    private TipoAcessorio tipo;
    private String material;
    private String cor;

    public String getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(String compatibilidade) {
        this.compatibilidade = compatibilidade;
    }

    public TipoAcessorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoAcessorio tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}