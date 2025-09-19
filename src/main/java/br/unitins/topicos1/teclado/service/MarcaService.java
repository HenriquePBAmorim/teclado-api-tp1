package br.unitins.topicos1.teclado.service;

import java.util.List;

import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.model.Marca;

public interface MarcaService {
    List<Marca> findAll();
    List<Marca> findByNome(String nome);
    Marca findById(Long id);
    Marca create(MarcaDTO dto);
    void update(Long id, MarcaDTO dto);
    void delete(Long id);
}