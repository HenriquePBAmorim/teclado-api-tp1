package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca>{
    
    public List<Marca> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Marca findByNomeExato(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public Marca findByNomeExatoExceptId(String nome, Long id) {
        if (id == null)
            return findByNomeExato(nome);
        return find("UPPER(nome) = ?1 AND id != ?2", nome.toUpperCase(), id).firstResult();
    }
}