package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Switch;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SwitchRepository implements PanacheRepository<Switch> {
    
    public List<Switch> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }

    public Switch findByNomeExato(String nome) {
        return find("UPPER(nome) = ?1", nome.toUpperCase()).firstResult();
    }

    // NOVO MÉTODO ADICIONADO (Como o do professor)
    public Switch findByNomeExatoExceptId(String nome, Long id) {
        if (id == null)
            return findByNomeExato(nome);
        return find("UPPER(nome) = ?1 AND id != ?2", nome.toUpperCase(), id).firstResult();
    }
}