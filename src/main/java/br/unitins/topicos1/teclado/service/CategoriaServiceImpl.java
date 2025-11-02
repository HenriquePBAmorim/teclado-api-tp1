package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.CategoriaDTO;
import br.unitins.topicos1.teclado.dto.CategoriaDTOResponse;
import br.unitins.topicos1.teclado.model.Categoria;
import br.unitins.topicos1.teclado.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository repository;

    @Override
    public List<CategoriaDTOResponse> findAll() {
        return repository.listAll().stream().map(CategoriaDTOResponse::valueOf).toList();
    }

    @Override
    public CategoriaDTOResponse findById(Long id) {
        Categoria c = repository.findById(id);
        if (c == null)
            return null; 
            
        return CategoriaDTOResponse.valueOf(c);
    }

    @Override
    public List<CategoriaDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(CategoriaDTOResponse::valueOf).toList();
    }

    @Override
    @Transactional
    public CategoriaDTOResponse create(@Valid CategoriaDTO dto) {
        Categoria c = new Categoria();
        c.setNome(dto.nome());
        c.setDescricao(dto.descricao());
        
        repository.persist(c);
        return CategoriaDTOResponse.valueOf(c);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid CategoriaDTO dto) {
        Categoria c = repository.findById(id);
        if (c == null)
            throw new NotFoundException("Categoria não encontrada.");
            
        c.setNome(dto.nome());
        c.setDescricao(dto.descricao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}