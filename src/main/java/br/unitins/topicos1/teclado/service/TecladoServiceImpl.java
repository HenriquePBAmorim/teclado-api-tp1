package br.unitins.topicos1.teclado.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.dto.TecladoDTOResponse;
import br.unitins.topicos1.teclado.model.Acessorio;
import br.unitins.topicos1.teclado.model.Categoria;
import br.unitins.topicos1.teclado.model.Estoque;
import br.unitins.topicos1.teclado.model.Keycap;
import br.unitins.topicos1.teclado.model.Modelo;
import br.unitins.topicos1.teclado.model.Switch;
import br.unitins.topicos1.teclado.model.Teclado;
import br.unitins.topicos1.teclado.model.TipoTeclado;
import br.unitins.topicos1.teclado.repository.AcessorioRepository;
import br.unitins.topicos1.teclado.repository.CategoriaRepository;
import br.unitins.topicos1.teclado.repository.EstoqueRepository;
import br.unitins.topicos1.teclado.repository.KeycapRepository;
import br.unitins.topicos1.teclado.repository.ModeloRepository;
import br.unitins.topicos1.teclado.repository.SwitchRepository;
import br.unitins.topicos1.teclado.repository.TecladoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TecladoServiceImpl implements TecladoService {

    // Repositório principal
    @Inject
    TecladoRepository repository;

    // Repositórios das associações
    @Inject
    ModeloRepository modeloRepository;
    @Inject
    SwitchRepository switchRepository;
    @Inject
    KeycapRepository keycapRepository;
    @Inject
    EstoqueRepository estoqueRepository;
    @Inject
    CategoriaRepository categoriaRepository;
    @Inject
    AcessorioRepository acessorioRepository;

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
        Teclado teclado = repository.findById(id);
        if (teclado == null)
            throw new NotFoundException("Teclado não encontrado.");
        return teclado;
    }
    
    @Override
    @Transactional
    public TecladoDTOResponse create(TecladoDTO dto) {
        Teclado teclado = new Teclado();

        // Dados do Produto
        teclado.setNome(dto.nome());
        teclado.setPreco(dto.preco());
        
        // Dados do Teclado
        teclado.setIdioma(dto.idioma());
        teclado.setComFio(dto.comFio());
        teclado.setIluminacaoRgb(dto.iluminacaoRgb());
        teclado.setDataCadastro(LocalDate.now());
        teclado.setTipo(TipoTeclado.valueOf(dto.idTipo()));

        // Associações
        vincularAssociacoes(teclado, dto);
        
        repository.persist(teclado);
        return TecladoDTOResponse.valueOf(teclado);
    }

    @Override
    @Transactional
    public void update(Long id, TecladoDTO dto) {
        Teclado existente = repository.findById(id);
        if (existente == null) 
            throw new NotFoundException("Teclado não encontrado.");
        
        // Dados do Produto
        existente.setNome(dto.nome());
        existente.setPreco(dto.preco());
        
        // Dados do Teclado
        existente.setIdioma(dto.idioma());
        existente.setComFio(dto.comFio());
        existente.setIluminacaoRgb(dto.iluminacaoRgb());
        existente.setTipo(TipoTeclado.valueOf(dto.idTipo()));
        
        // Associações
        vincularAssociacoes(existente, dto);
    }

    private void vincularAssociacoes(Teclado teclado, TecladoDTO dto) {
        // Modelo (N:1)
        Modelo modelo = modeloRepository.findById(dto.idModelo());
        if (modelo == null) throw new NotFoundException("Modelo não encontrado.");
        teclado.setModelo(modelo);

        // Switch (N:1)
        Switch s = switchRepository.findById(dto.idSwitch());
        if (s == null) throw new NotFoundException("Switch não encontrado.");
        teclado.setSwitchModelo(s);

        // Keycap (N:1)
        Keycap k = keycapRepository.findById(dto.idKeycap());
        if (k == null) throw new NotFoundException("Keycap não encontrado.");
        teclado.setKeycapModelo(k);

        // Estoque (1:1)
        Estoque e = estoqueRepository.findById(dto.idEstoque());
        if (e == null) throw new NotFoundException("Estoque não encontrado.");
        teclado.setEstoque(e);

        // Categorias (N:M)
        if (teclado.getCategorias() == null) {
            teclado.setCategorias(new HashSet<>());
        }
        teclado.getCategorias().clear(); // Limpa as existentes para atualizar
        if (dto.idCategorias() != null) {
            for (Long idCat : dto.idCategorias()) {
                Categoria cat = categoriaRepository.findById(idCat);
                if (cat == null) throw new NotFoundException("Categoria ID " + idCat + " não encontrada.");
                teclado.getCategorias().add(cat);
            }
        }

        // Acessorios (N:M)
        if (teclado.getAcessorios() == null) {
            teclado.setAcessorios(new HashSet<>());
        }
        teclado.getAcessorios().clear();
        if (dto.idAcessorios() != null) {
            for (Long idAces : dto.idAcessorios()) {
                Acessorio aces = acessorioRepository.findById(idAces);
                if (aces == null) throw new NotFoundException("Acessório ID " + idAces + " não encontrado.");
                teclado.getAcessorios().add(aces);
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id))
            throw new NotFoundException("Teclado não encontrado.");
    }
}