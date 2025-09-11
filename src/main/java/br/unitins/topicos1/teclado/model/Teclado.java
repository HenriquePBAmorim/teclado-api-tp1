package br.unitins.topicos1.teclado.model;

public class Teclado extends DefaultEntity{
    
 private String nome;
 private String marca;
 private String modelo;
 private String tipo;
 private String idoma;
 private Boolean comFio;
 private Boolean iluminacaoRgb;
 private Double preco;
 public String getNome() {
    return nome;
 }
 public void setNome(String nome) {
    this.nome = nome;
 }
 public String getMarca() {
    return marca;
 }
 public void setMarca(String marca) {
    this.marca = marca;
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
 public String getIdoma() {
    return idoma;
 }
 public void setIdoma(String idoma) {
    this.idoma = idoma;
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

 
    
}
