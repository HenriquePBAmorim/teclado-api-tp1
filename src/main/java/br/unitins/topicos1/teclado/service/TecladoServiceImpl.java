package br.unitins.topicos1.teclado.service;

import java.util.List;
import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.dto.TecladoDTOResponse;
import br.unitins.topicos1.teclado.model.Marca;
import br.unitins.topicos1.teclado.model.Teclado;
import br.unitins.topicos1.teclado.model.TipoTeclado;
import br.unitins.topicos1.teclado.repository.MarcaRepository;
import br.unitins.topicos1.teclado.repository.TecladoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TecladoServiceImpl implements TecladoService {

    @Inject
    TecladoRepository repository;
    @Inject
    MarcaRepository marcaRepository;

    @Override
    public List<TecladoDTOResponse> findAll() {
        return repository.listAll().stream().map(TecladoDTOResponse::valueOf).toList();
    }
    @Override
    public List<TecladoDTOResponse> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(TecladoDTOResponse::valueOf).toList();
    }
    @Override
    public Teclado findById(Long id) {
        return repository.findById(id);
    }
    @Override
    @Transactional
    public TecladoDTOResponse create(TecladoDTO dto) {
        Teclado teclado = new Teclado();
        teclado.setNome(dto.nome());
        teclado.setModelo(dto.modelo());
        teclado.setTipo(TipoTeclado.valueOf(dto.idTipo()));
        teclado.setIdioma(dto.idioma());
        teclado.setComFio(dto.comFio());
        teclado.setIluminacaoRgb(dto.iluminacaoRgb());
        teclado.setPreco(dto.preco());
        
        Marca marca = marcaRepository.findById(dto.idMarca());
        teclado.setMarca(marca);
        
        repository.persist(teclado);
        return TecladoDTOResponse.valueOf(teclado);
    }
    @Override
    @Transactional
    public void update(Long id, TecladoDTO dto) {
        Teclado existente = repository.findById(id);
        if (existente == null) throw new jakarta.ws.rs.NotFoundException("Teclado não encontrado.");
        
        existente.setNome(dto.nome());
        existente.setModelo(dto.modelo());
        existente.setTipo(TipoTeclado.valueOf(dto.idTipo()));
        existente.setIdioma(dto.idioma());
        existente.setComFio(dto.comFio());
        existente.setIluminacaoRgb(dto.iluminacaoRgb());
        existente.setPreco(dto.preco());
        
        Marca marca = marcaRepository.findById(dto.idMarca());
        existente.setMarca(marca);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new jakarta.ws.rs.NotFoundException("Teclado não encontrado.");
    }
}