package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.SwitchDTO;
import br.unitins.topicos1.teclado.dto.SwitchDTOResponse;
import jakarta.validation.Valid;

public interface SwitchService {
    List<SwitchDTOResponse> findAll();
    List<SwitchDTOResponse> findByNome(String nome);
    SwitchDTOResponse findById(Long id); 
    SwitchDTOResponse create(@Valid SwitchDTO dto);
    void update(Long id, @Valid SwitchDTO dto);
    void delete(Long id);
}