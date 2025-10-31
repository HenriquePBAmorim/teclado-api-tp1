package br.unitins.topicos1.teclado.repository;

import java.util.List;
import br.unitins.topicos1.teclado.model.Keycap;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KeycapRepository implements PanacheRepository<Keycap> {
    
    public List<Keycap> findByNome(String nome) {
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%").list();
    }
}