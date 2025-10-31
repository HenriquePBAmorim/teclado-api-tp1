package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.AcessorioDTO;
import br.unitins.topicos1.teclado.dto.AcessorioDTOResponse;
import jakarta.validation.Valid;

public interface AcessorioService {
    List<AcessorioDTOResponse> findAll();
    AcessorioDTOResponse findById(Long id);
    List<AcessorioDTOResponse> findByNome(String nome);
    AcessorioDTOResponse create(@Valid AcessorioDTO dto);
    void update(Long id, @Valid AcessorioDTO dto);
    void delete(Long id);
}