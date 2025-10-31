package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Estado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estado> {

    public List<Estado> findByNome(String nome) {
        return find("SELECT e FROM Estado e WHERE e.nome LIKE ?1 ", "%"+nome+"%").list();
    }

    public Estado findBySigla(String sigla) {
        return find("sigla = ?1 ", sigla).firstResult();
    }
    
    public Estado findBySiglaExceptId(String sigla, Long id) {
        if (id == null)
            return findBySigla(sigla);
        return find("sigla = ?1 AND id != ?2 ", sigla, id).firstResult();
    }
}