package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.dto.TecladoDTOResponse;
import br.unitins.topicos1.teclado.model.Teclado;
import jakarta.validation.Valid;

public interface TecladoService {
    List<TecladoDTOResponse> findAll();
    List<TecladoDTOResponse> findByNome(String nome);
    Teclado findById(Long id); 
    TecladoDTOResponse create(@Valid TecladoDTO dto);
    void update(Long id, @Valid TecladoDTO dto);
    void delete(Long id);
}