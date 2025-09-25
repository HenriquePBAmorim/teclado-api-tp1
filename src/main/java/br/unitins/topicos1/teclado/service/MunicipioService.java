// MunicipioService.java
package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MunicipioDTO;
import br.unitins.topicos1.teclado.dto.MunicipioDTOResponse;

public interface MunicipioService {
    List<MunicipioDTOResponse> findAll();
    List<MunicipioDTOResponse> findByNome(String nome);
    MunicipioDTOResponse findById(Long id);
    MunicipioDTOResponse create(MunicipioDTO dto);
    void update(Long id, MunicipioDTO dto);
    void delete(Long id);
}