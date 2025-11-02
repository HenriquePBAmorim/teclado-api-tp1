package br.unitins.topicos1.teclado.service;

import br.unitins.topicos1.teclado.dto.EstoqueDTO;
import br.unitins.topicos1.teclado.dto.EstoqueDTOResponse;
import br.unitins.topicos1.teclado.model.Estoque;
import br.unitins.topicos1.teclado.repository.EstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDate;

@ApplicationScoped
public class EstoqueServiceImpl implements EstoqueService {

    @Inject
    EstoqueRepository repository;

    @Override
    public EstoqueDTOResponse findById(Long id) {
        Estoque e = repository.findById(id);
        if (e == null)
            return null; 
            
        return EstoqueDTOResponse.valueOf(e);
    }

    @Override
    @Transactional
    public EstoqueDTOResponse createForTeclado(@Valid EstoqueDTO dto) {
        Estoque e = new Estoque();
        e.setQuantidade(dto.quantidade());
        e.setDataAtualizacao(LocalDate.now());
        
        repository.persist(e);
        return EstoqueDTOResponse.valueOf(e);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid EstoqueDTO dto) {
        Estoque e = repository.findById(id);
        if (e == null)
            throw new NotFoundException("Estoque não encontrado.");
            
        e.setQuantidade(dto.quantidade());
        e.setDataAtualizacao(LocalDate.now());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}