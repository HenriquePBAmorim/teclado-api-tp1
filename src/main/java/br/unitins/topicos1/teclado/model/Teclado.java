package br.unitins.topicos1.teclado.model;

import java.time.LocalDate;
import java.util.Set; 
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Teclado extends Produto { 
    
    private String idioma;    
    private Boolean comFio;
    private Boolean iluminacaoRgb;
    private LocalDate dataCadastro; 
    private TipoTeclado tipo;

    @ManyToOne
    @JoinColumn(name = "id_modelo")
    private Modelo modelo; 
    
    @ManyToOne
    @JoinColumn(name = "id_switch")
    private Switch switchModelo; 
    
    @ManyToOne
    @JoinColumn(name = "id_keycap")
    private Keycap keycapModelo; 

    @OneToOne
    @JoinColumn(name = "id_estoque")
    private Estoque estoque;

    @ManyToMany
    @JoinTable(name = "teclado_categoria",
               joinColumns = @JoinColumn(name = "id_teclado"),
               inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private Set<Categoria> categorias;

    @ManyToMany
    @JoinTable(name = "teclado_acessorio",
               joinColumns = @JoinColumn(name = "id_teclado"),
               inverseJoinColumns = @JoinColumn(name = "id_acessorio"))
    private Set<Acessorio> acessorios;

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

    public LocalDate getDataCadastro() { 
        return dataCadastro; 
    }
    public void setDataCadastro(LocalDate dataCadastro) { 
        this.dataCadastro = dataCadastro; 
    }

    public TipoTeclado getTipo() { 
        return tipo; 
    }
    public void setTipo(TipoTeclado tipo) { 
        this.tipo = tipo; 
    }

    public Modelo getModelo() { 
        return modelo; 
    }
    public void setModelo(Modelo modelo) { 
        this.modelo = modelo; 
    }

    public Switch getSwitchModelo() { 
        return switchModelo; 
    }
    public void setSwitchModelo(Switch switchModelo) { 
        this.switchModelo = switchModelo; 
    }

    public Keycap getKeycapModelo() { 
        return keycapModelo; 
    }
    public void setKeycapModelo(Keycap keycapModelo) { 
        this.keycapModelo = keycapModelo; 
    }

    public Estoque getEstoque() { 
        return estoque; 
    }
    public void setEstoque(Estoque estoque) { 
        this.estoque = estoque; 
    }

    public Set<Categoria> getCategorias() { 
        return categorias; 
    }
    public void setCategorias(Set<Categoria> categorias) { 
        this.categorias = categorias; 
    }

    public Set<Acessorio> getAcessorios() { 
        return acessorios; 
    }
    public void setAcessorios(Set<Acessorio> acessorios) { 
        this.acessorios = acessorios; 
    }
}