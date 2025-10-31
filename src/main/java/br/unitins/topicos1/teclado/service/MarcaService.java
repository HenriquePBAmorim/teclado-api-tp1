package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.dto.MarcaDTOResponse;
import br.unitins.topicos1.teclado.model.Marca;
import jakarta.validation.Valid;

public interface MarcaService {
    List<MarcaDTOResponse> findAll();
    List<MarcaDTOResponse> findByNome(String nome);
    Marca findById(Long id); 
    MarcaDTOResponse create(@Valid MarcaDTO dto);
    void update(Long id, @Valid MarcaDTO dto);
    void delete(Long id);
}