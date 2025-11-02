package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Acessorio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AcessorioRepository implements PanacheRepository<Acessorio> {
    
    public List<Acessorio> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Acessorio findByNomeExato(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    public Acessorio findByNomeExatoExceptId(String nome, Long id) {
        if (id == null)
            return findByNomeExato(nome);
        return find("UPPER(nome) = ?1 AND id != ?2", nome.toUpperCase(), id).firstResult();
    }
}