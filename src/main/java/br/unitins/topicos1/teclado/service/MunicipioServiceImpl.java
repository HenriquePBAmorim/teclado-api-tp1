// MunicipioServiceImpl.java (ATUALIZADO)
package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MunicipioDTO;
import br.unitins.topicos1.teclado.dto.MunicipioDTOResponse;
import br.unitins.topicos1.teclado.model.Municipio;
import br.unitins.topicos1.teclado.repository.EstadoRepository;
import br.unitins.topicos1.teclado.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MunicipioServiceImpl implements MunicipioService {

    @Inject
    MunicipioRepository repository;

    @Inject
    EstadoRepository estadoRepository;

    @Override
    public List<MunicipioDTOResponse> findAll() {
        return repository
                    .listAll()
                    .stream()
                    .map(m -> MunicipioDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<MunicipioDTOResponse> findByNome(String nome) {
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(m -> MunicipioDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public MunicipioDTOResponse findById(Long id) {
        return MunicipioDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public MunicipioDTOResponse create(MunicipioDTO dto) {
        Municipio municipio = new Municipio();
        municipio.setNome(dto.nome());
        municipio.setEstado(estadoRepository.findById(dto.idEstado()));

        repository.persist(municipio);

        return MunicipioDTOResponse.valueOf(municipio);
    }

    @Override
    @Transactional
    public void update(Long id, MunicipioDTO dto) {
        Municipio municipio = repository.findById(id);
        municipio.setNome(dto.nome());
        municipio.setEstado(estadoRepository.findById(dto.idEstado()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
       repository.deleteById(id);
    }
    
}