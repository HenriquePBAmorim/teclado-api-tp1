
package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.ModeloDTO;
import br.unitins.topicos1.teclado.dto.ModeloDTOResponse;
import jakarta.validation.Valid;

public interface ModeloService {
    List<ModeloDTOResponse> findAll();
    ModeloDTOResponse findById(Long id);
    List<ModeloDTOResponse> findByNome(String nome);
    ModeloDTOResponse create(@Valid ModeloDTO dto);
    void update(Long id, @Valid ModeloDTO dto);
    void delete(Long id);
}