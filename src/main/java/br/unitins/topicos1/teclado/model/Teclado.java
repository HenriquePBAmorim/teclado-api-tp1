package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Teclado extends DefaultEntity {
    
    private String nome;
    private String modelo;  
    private String tipo;
    private String idioma;   
    private Boolean comFio;
    private Boolean iluminacaoRgb;
    private Double preco;

    @ManyToOne
    private Marca marca;    

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getComFio() {
        return comFio;
    }
    public void setComFio(Boolean comFio) {
        this.comFio = comFio;
    }

    public Boolean getIluminacaoRgb() {
        return iluminacaoRgb;
    }
    public void setIluminacaoRgb(Boolean iluminacaoRgb) {
        this.iluminacaoRgb = iluminacaoRgb;
    }

    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}
