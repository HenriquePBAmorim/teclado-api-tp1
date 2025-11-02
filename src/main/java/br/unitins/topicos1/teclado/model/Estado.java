package br.unitins.topicos1.teclado.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import br.unitins.topicos1.teclado.model.jpa.RegiaoConverter;

@Entity
public class Estado extends DefaultEntity {

    private String nome;
    private String sigla;

    @Convert(converter = RegiaoConverter.class)
    private Regiao regiao;

    public String getNome() { 
        return nome; 
    }

    public void setNome(String nome) { 
        this.nome = nome; 
    }

    public String getSigla() { 
        return sigla; 
    }

    public void setSigla(String sigla) { 
        this.sigla = sigla; 
    }

    public Regiao getRegiao() { 
        return regiao; 
    }

    public void setRegiao(Regiao regiao) { 
        this.regiao = regiao; 
    }
}
