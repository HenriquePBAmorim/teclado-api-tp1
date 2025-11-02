package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.ModeloDTO;
import br.unitins.topicos1.teclado.dto.ModeloDTOResponse;
import br.unitins.topicos1.teclado.model.Marca;
import br.unitins.topicos1.teclado.model.Modelo;
import br.unitins.topicos1.teclado.repository.MarcaRepository;
import br.unitins.topicos1.teclado.repository.ModeloRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ModeloServiceImpl implements ModeloService {

    @Inject
    ModeloRepository repository;
    @Inject
    MarcaRepository marcaRepository;

    @Override
    public List<ModeloDTOResponse> findAll() {
        return repository.listAll().stream().map(ModeloDTOResponse::valueOf).toList();
    }

    @Override
    public ModeloDTOResponse findById(Long id) {
        // CORREÇÃO: Implementando o padrão do professor
        Modelo m = repository.findById(id);
        if (m == null)
            return null; 
        
        return ModeloDTOResponse.valueOf(m);
    }

    @Override
    public List<ModeloDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(ModeloDTOResponse::valueOf).toList();
    }

    @Override
    @Transactional
    public ModeloDTOResponse create(@Valid ModeloDTO dto) {
        Modelo m = new Modelo();
        m.setNome(dto.nome());
        
        Marca marca = marcaRepository.findById(dto.idMarca());
        if(marca == null)
            throw new NotFoundException("Marca não encontrada.");
        m.setMarca(marca);
        
        repository.persist(m);
        return ModeloDTOResponse.valueOf(m);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid ModeloDTO dto) {
        Modelo m = repository.findById(id);
        if (m == null)
            throw new NotFoundException("Modelo não encontrado.");
            
        m.setNome(dto.nome());
        
        Marca marca = marcaRepository.findById(dto.idMarca());
        if(marca == null)
            throw new NotFoundException("Marca não encontrada.");
        m.setMarca(marca);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}