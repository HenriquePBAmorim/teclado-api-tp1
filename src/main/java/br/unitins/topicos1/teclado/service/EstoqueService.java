package br.unitins.topicos1.teclado.service;

import br.unitins.topicos1.teclado.dto.EstoqueDTO;
import br.unitins.topicos1.teclado.dto.EstoqueDTOResponse;
import jakarta.validation.Valid;

public interface EstoqueService {
    // Serviços de estoque geralmente são atrelados ao produto
    EstoqueDTOResponse findById(Long id);
    EstoqueDTOResponse createForTeclado(@Valid EstoqueDTO dto);
    void update(Long id, @Valid EstoqueDTO dto);
    void delete(Long id);
}