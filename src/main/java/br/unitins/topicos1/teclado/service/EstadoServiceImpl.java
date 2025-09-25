package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.EstadoDTO;
import br.unitins.topicos1.teclado.dto.EstadoDTOResponse;
import br.unitins.topicos1.teclado.model.Estado;
import br.unitins.topicos1.teclado.model.Regiao;
import br.unitins.topicos1.teclado.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository repository;

    @Override
    public List<EstadoDTOResponse> findAll() {
        return repository
                    .listAll()
                    .stream()
                    .map(m -> EstadoDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public List<EstadoDTOResponse> findByNome(String nome) {
        return repository
                    .findByNome(nome)
                    .stream()
                    .map(m -> EstadoDTOResponse.valueOf(m))
                    .toList();
    }

    @Override
    public EstadoDTOResponse findById(Long id) {
        return EstadoDTOResponse.valueOf(repository.findById(id));
    }

    @Override
    @Transactional
    public EstadoDTOResponse create(EstadoDTO dto) {
        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estado.setRegiao(Regiao.valueOf(dto.idRegiao()));

        repository.persist(estado);

        return EstadoDTOResponse.valueOf(estado);
    }

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {
        Estado estado = repository.findById(id);
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
