package br.unitins.topicos1.teclado.repository;

import java.util.List;

import br.unitins.topicos1.teclado.model.Teclado;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class TecladoRepository implements PanacheRepository{

    public List<Teclado> findBynNome(String nome) {
        return find("nome LIKE ?1" +nome+ "%").list();
    }

}
