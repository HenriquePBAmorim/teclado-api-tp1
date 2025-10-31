package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Categoria;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoriaRepository implements PanacheRepository<Categoria> {
    
    public List<Categoria> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}