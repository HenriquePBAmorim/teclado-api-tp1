package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.dto.MarcaDTOResponse;
import br.unitins.topicos1.teclado.exception.ValidationException;
import br.unitins.topicos1.teclado.model.Marca;
import br.unitins.topicos1.teclado.repository.MarcaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

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
    public MarcaDTOResponse findById(Long id) {
        Marca marca = repository.findById(id);
        if (marca == null)
            return null; 
        
        return MarcaDTOResponse.valueOf(marca);
    }
    
    @Override
    @Transactional
    public MarcaDTOResponse create(MarcaDTO dto) {
        validarNomeMarca(dto.nome(), null);

        Marca marca = new Marca();
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
        repository.persist(marca);
        
        return MarcaDTOResponse.valueOf(marca);
    }

    @Override
    @Transactional
    public void update(Long id, MarcaDTO dto) {
        validarNomeMarca(dto.nome(), id);
        
        Marca marca = repository.findById(id);
        if (marca == null) 
            throw new NotFoundException("Marca não encontrada.");
        
        marca.setNome(dto.nome());
        marca.setDescricao(dto.descricao());
    }

    private void validarNomeMarca(String nome, Long id) {
        Marca marca = repository.findByNomeExatoExceptId(nome, id);
        if (marca != null) {
            throw ValidationException.of("nome", "Este nome de marca já está em uso.");
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}