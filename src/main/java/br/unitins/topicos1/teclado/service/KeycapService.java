package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.KeycapDTO;
import br.unitins.topicos1.teclado.dto.KeycapDTOResponse;
import jakarta.validation.Valid;

public interface KeycapService {
    List<KeycapDTOResponse> findAll();
    KeycapDTOResponse findById(Long id);
    List<KeycapDTOResponse> findByNome(String nome);
    KeycapDTOResponse create(@Valid KeycapDTO dto);
    void update(Long id, @Valid KeycapDTO dto);
    void delete(Long id);
}