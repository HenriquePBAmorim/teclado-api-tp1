package br.unitins.topicos1.teclado.service;

import java.util.List;

import br.unitins.topicos1.teclado.dto.MarcaDTO;
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
    public List<Marca> findAll() {
        return repository.listAll();
    }

    @Override
    public List<Marca> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Marca findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Marca create(MarcaDTO dto) {
        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
        repository.persist(marca);
        return marca;
    }

    @Override
    @Transactional
    public void update(Long id, MarcaDTO dto) {
        Marca marca = repository.findById(id);
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}