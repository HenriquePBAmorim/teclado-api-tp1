package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.AcessorioDTO;
import br.unitins.topicos1.teclado.dto.AcessorioDTOResponse;
import br.unitins.topicos1.teclado.exception.ValidationException;
import br.unitins.topicos1.teclado.model.Acessorio;
import br.unitins.topicos1.teclado.model.TipoAcessorio;
import br.unitins.topicos1.teclado.repository.AcessorioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AcessorioServiceImpl implements AcessorioService {

    @Inject
    AcessorioRepository repository;

    @Override
    public List<AcessorioDTOResponse> findAll() {
        return repository.listAll().stream().map(AcessorioDTOResponse::valueOf).toList();
    }

    @Override
    public AcessorioDTOResponse findById(Long id) {
        Acessorio a = repository.findById(id);
        if (a == null)
            throw new NotFoundException("Acessório não encontrado.");
        return AcessorioDTOResponse.valueOf(a);
    }

    @Override
    public List<AcessorioDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(AcessorioDTOResponse::valueOf).toList();
    }

    @Override
    @Transactional
    public AcessorioDTOResponse create(@Valid AcessorioDTO dto) {
        
        validarNomeAcessorio(dto.nome(), null);
        
        Acessorio a = new Acessorio();
        a.setNome(dto.nome());
        a.setPreco(dto.preco());
        a.setCompatibilidade(dto.compatibilidade());
        a.setTipo(TipoAcessorio.valueOf(dto.idTipoAcessorio()));
        a.setMaterial(dto.material());
        a.setCor(dto.cor());
        
        repository.persist(a);
        return AcessorioDTOResponse.valueOf(a);
    }

    private void validarNomeAcessorio(String nome, Long id) {
        Acessorio acessorioExistente = repository.findByNomeExatoExceptId(nome, id);
        
        if (acessorioExistente != null) {
            throw ValidationException.of("nome", "O nome '"+nome+"' já está em uso.");
        }
    }

    @Override
    @Transactional
    public void update(Long id, @Valid AcessorioDTO dto) {
        validarNomeAcessorio(dto.nome(), id);

        Acessorio a = repository.findById(id);
        if (a == null)
            throw new NotFoundException("Acessório não encontrado.");
            
        a.setNome(dto.nome());
        a.setPreco(dto.preco());
        a.setCompatibilidade(dto.compatibilidade());
        a.setTipo(TipoAcessorio.valueOf(dto.idTipoAcessorio()));
        a.setMaterial(dto.material());
        a.setCor(dto.cor());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Acessório não encontrado.");
    }
}