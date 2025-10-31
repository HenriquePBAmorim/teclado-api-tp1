package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.SwitchDTO;
import br.unitins.topicos1.teclado.dto.SwitchDTOResponse;
import br.unitins.topicos1.teclado.exception.ValidationException;
import br.unitins.topicos1.teclado.model.Switch;
import br.unitins.topicos1.teclado.model.TipoSwitch;
import br.unitins.topicos1.teclado.repository.SwitchRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SwitchServiceImpl implements SwitchService {

    @Inject
    SwitchRepository repository;

    @Override
    public List<SwitchDTOResponse> findAll() {
        return repository.listAll().stream().map(SwitchDTOResponse::valueOf).toList();
    }

    @Override
    public SwitchDTOResponse findById(Long id) {
        Switch s = repository.findById(id);
        if (s == null)
            throw new NotFoundException("Switch não encontrado.");
        return SwitchDTOResponse.valueOf(s);
    }

    @Override
    public List<SwitchDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(SwitchDTOResponse::valueOf).toList();
    }

    @Override
    @Transactional
    public SwitchDTOResponse create(@Valid SwitchDTO dto) {
        // ATUALIZADO: Passa null para o ID
        validarNomeSwitch(dto.nome(), null);
        
        Switch s = new Switch();
        s.setNome(dto.nome());
        s.setFabricante(dto.fabricante());
        s.setTipo(TipoSwitch.valueOf(dto.idTipoSwitch()));
        s.setForcaAtuacao(dto.forcaAtuacao());
        
        repository.persist(s);
        return SwitchDTOResponse.valueOf(s);
    }

    // MÉTODO ATUALIZADO para aceitar o ID
    private void validarNomeSwitch(String nome, Long id) {
        // ATUALIZADO: Usa o novo método do repositório
        Switch switchExistente = repository.findByNomeExatoExceptId(nome, id);

        if (switchExistente != null) {
            // CORREÇÃO APLICADA AQUI:
            throw ValidationException.of("nome", "O nome '"+nome+"' já existe.");
        }
    }

    @Override
    @Transactional
    public void update(Long id, @Valid SwitchDTO dto) {
        // ATUALIZADO: Validação adicionada ao método de update
        validarNomeSwitch(dto.nome(), id);

        Switch s = repository.findById(id);
        if (s == null)
            throw new NotFoundException("Switch não encontrado.");
            
        s.setNome(dto.nome());
        s.setFabricante(dto.fabricante());
        s.setTipo(TipoSwitch.valueOf(dto.idTipoSwitch()));
        s.setForcaAtuacao(dto.forcaAtuacao());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Switch não encontrado.");
    }
}