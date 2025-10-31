package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.CategoriaDTO;
import br.unitins.topicos1.teclado.dto.CategoriaDTOResponse;
import jakarta.validation.Valid;

public interface CategoriaService {
    List<CategoriaDTOResponse> findAll();
    CategoriaDTOResponse findById(Long id);
    List<CategoriaDTOResponse> findByNome(String nome);
    CategoriaDTOResponse create(@Valid CategoriaDTO dto);
    void update(Long id, @Valid CategoriaDTO dto);
    void delete(Long id);
}