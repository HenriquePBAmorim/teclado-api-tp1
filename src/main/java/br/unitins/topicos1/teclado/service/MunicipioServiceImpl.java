package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MunicipioDTO;
import br.unitins.topicos1.teclado.dto.MunicipioDTOResponse;
import br.unitins.topicos1.teclado.model.Estado;
import br.unitins.topicos1.teclado.model.Municipio;
import br.unitins.topicos1.teclado.repository.EstadoRepository;
import br.unitins.topicos1.teclado.repository.MunicipioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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
        Municipio municipio = repository.findById(id);
        if (municipio == null)
            throw new NotFoundException("Município não encontrado.");
        return MunicipioDTOResponse.valueOf(municipio);
    }

    @Override
    @Transactional
    public MunicipioDTOResponse create(MunicipioDTO dto) {
        Municipio municipio = new Municipio();
        municipio.setNome(dto.nome());
        
        Estado estado = estadoRepository.findById(dto.idEstado());
        if (estado == null)
            throw new NotFoundException("Estado não encontrado.");
        municipio.setEstado(estado);

        repository.persist(municipio);

        return MunicipioDTOResponse.valueOf(municipio);
    }

    @Override
    @Transactional
    public void update(Long id, MunicipioDTO dto) {
        Municipio municipio = repository.findById(id);
        if (municipio == null)
            throw new NotFoundException("Município não encontrado.");

        municipio.setNome(dto.nome());
        
        Estado estado = estadoRepository.findById(dto.idEstado());
        if (estado == null)
            throw new NotFoundException("Estado não encontrado.");
        municipio.setEstado(estado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Município não encontrado.");
    }
}