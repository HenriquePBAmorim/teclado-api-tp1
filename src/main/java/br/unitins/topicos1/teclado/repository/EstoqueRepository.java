package br.unitins.topicos1.teclado.repository;

import br.unitins.topicos1.teclado.model.Estoque;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {
    // Pode ficar vazio por enquanto, pois é 1:1 e será acessado via Teclado
}