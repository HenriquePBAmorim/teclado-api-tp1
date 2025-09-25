package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.dto.MarcaDTOResponse;
import br.unitins.topicos1.teclado.model.Marca;
import br.unitins.topicos1.teclado.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject
    MarcaRepository repository;

    @Override
    public List<MarcaDTOResponse> findAll() {
        return repository.listAll().stream().map(MarcaDTOResponse::valueOf).toList();
    }
    @Override
    public List<MarcaDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(MarcaDTOResponse::valueOf).toList();
    }
    @Override
    public Marca findById(Long id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    public MarcaDTOResponse create(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
        repository.persist(marca);
        return MarcaDTOResponse.valueOf(marca);
    }
    @Override
    @Transactional
    public void update(Long id, MarcaDTO dto) {
        Marca marca = repository.findById(id);
        if (marca == null) throw new jakarta.ws.rs.NotFoundException("Marca não encontrada.");
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
    }
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new jakarta.ws.rs.NotFoundException("Marca não encontrada.");
    }
}