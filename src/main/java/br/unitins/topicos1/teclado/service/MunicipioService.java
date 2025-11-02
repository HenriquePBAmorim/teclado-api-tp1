package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MunicipioDTO;
import br.unitins.topicos1.teclado.dto.MunicipioDTOResponse;
import jakarta.validation.Valid;

public interface MunicipioService {
    List<MunicipioDTOResponse> findAll();
    List<MunicipioDTOResponse> findByNome(String nome);
    MunicipioDTOResponse findById(Long id); 
    MunicipioDTOResponse create(@Valid MunicipioDTO dto);
    void update(Long id, @Valid MunicipioDTO dto);
    void delete(Long id);
}