package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.EstadoDTO;
import br.unitins.topicos1.teclado.dto.EstadoDTOResponse;
import br.unitins.topicos1.teclado.exception.ValidationException;
import br.unitins.topicos1.teclado.model.Estado;
import br.unitins.topicos1.teclado.model.Regiao;
import br.unitins.topicos1.teclado.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Override
    public List<EstadoDTOResponse> findAll() {
        return repository
                .listAll()
                .stream()
                .map(e -> EstadoDTOResponse.valueOf(e))
                .toList();
    }

    @Override
    public List<EstadoDTOResponse> findByNome(String nome) {
        return repository
                .findByNome(nome)
                .stream()
                .map(e -> EstadoDTOResponse.valueOf(e))
                .toList();
    }

    @Override
    public EstadoDTOResponse findById(Long id) {
        Estado estado = repository.findById(id);
        if (estado == null)
            return null;
            
        return EstadoDTOResponse.valueOf(estado);
    }

    @Override
    @Transactional
    public EstadoDTOResponse create(EstadoDTO dto) {
        validarSigla(dto, null);

        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estado.setRegiao(Regiao.valueOf(dto.idRegiao()));

        repository.persist(estado);

        return EstadoDTOResponse.valueOf(estado);
    }

    private void validarSigla(EstadoDTO dto, Long id) {
        Estado e = repository.findBySiglaExceptId(dto.sigla(), id);
        if (e != null)
            throw ValidationException.of("sigla", "A sigla informada já foi utilizada.");
    }

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {
        validarSigla(dto, id);

        Estado estado = repository.findById(id);
         if (estado == null)
            throw new NotFoundException("Estado não encontrado.");
         
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estado.setRegiao(Regiao.valueOf(dto.idRegiao()));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}