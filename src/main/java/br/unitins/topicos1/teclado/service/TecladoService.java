package br.unitins.topicos1.teclado.service;

import java.util.List;

import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.model.Teclado;

public interface TecladoService {
    List<Teclado> findAll();
    List<Teclado> findByNome(String nome);
    Teclado findById(Long id);
    Teclado create(TecladoDTO dto);
    void update(Long id, TecladoDTO dto);
    void delete(Long id);
}