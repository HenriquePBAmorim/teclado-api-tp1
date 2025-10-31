package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.KeycapDTO;
import br.unitins.topicos1.teclado.dto.KeycapDTOResponse;
import br.unitins.topicos1.teclado.model.Keycap;
import br.unitins.topicos1.teclado.model.PerfilKeycap;
import br.unitins.topicos1.teclado.repository.KeycapRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class KeycapServiceImpl implements KeycapService {

    @Inject
    KeycapRepository repository;

    @Override
    public List<KeycapDTOResponse> findAll() {
        return repository.listAll().stream().map(KeycapDTOResponse::valueOf).toList();
    }

    @Override
    public KeycapDTOResponse findById(Long id) {
        Keycap k = repository.findById(id);
        if (k == null)
            throw new NotFoundException("Keycap não encontrada.");
        return KeycapDTOResponse.valueOf(k);
    }

    @Override
    public List<KeycapDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(KeycapDTOResponse::valueOf).toList();
    }

    @Override
    @Transactional
    public KeycapDTOResponse create(@Valid KeycapDTO dto) {
        Keycap k = new Keycap();
        k.setNome(dto.nome());
        k.setMaterial(dto.material());
        k.setPerfil(PerfilKeycap.valueOf(dto.idPerfilKeycap()));
        k.setCor(dto.cor());
        
        repository.persist(k);
        return KeycapDTOResponse.valueOf(k);
    }

    @Override
    @Transactional
    public void update(Long id, @Valid KeycapDTO dto) {
        Keycap k = repository.findById(id);
        if (k == null)
            throw new NotFoundException("Keycap não encontrada.");
            
        k.setNome(dto.nome());
        k.setMaterial(dto.material());
        k.setPerfil(PerfilKeycap.valueOf(dto.idPerfilKeycap()));
        k.setCor(dto.cor());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Keycap não encontrada.");
    }
}