package br.unitins.topicos1.teclado.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors; 

import br.unitins.topicos1.teclado.model.Teclado;
import br.unitins.topicos1.teclado.model.TipoTeclado;

public record TecladoDTOResponse(
        Long id,
        String nome,
        Double preco,
        ModeloDTOResponse modelo,
        TipoTeclado tipo,
        String idioma,
        Boolean comFio,
        Boolean iluminacaoRgb,
        LocalDate dataCadastro,
        SwitchDTOResponse switchModelo,
        KeycapDTOResponse keycapModelo,
        EstoqueDTOResponse estoque,
        List<CategoriaDTOResponse> categorias,
        List<AcessorioDTOResponse> acessorios) {

    public static TecladoDTOResponse valueOf(Teclado teclado) {

        List<CategoriaDTOResponse> listaCategorias = teclado.getCategorias() == null ? null :
            teclado.getCategorias().stream()
                .map(CategoriaDTOResponse::valueOf).collect(Collectors.toList());
        
        List<AcessorioDTOResponse> listaAcessorios = teclado.getAcessorios() == null ? null :
            teclado.getAcessorios().stream()
                .map(AcessorioDTOResponse::valueOf).collect(Collectors.toList());

        return new TecladoDTOResponse(
                teclado.getId(),
                teclado.getNome(),
                teclado.getPreco(),
                teclado.getModelo() == null ? null : ModeloDTOResponse.valueOf(teclado.getModelo()),
                teclado.getTipo(),
                teclado.getIdioma(),
                teclado.getComFio(),
                teclado.getIluminacaoRgb(),
                teclado.getDataCadastro(),
                teclado.getSwitchModelo() == null ? null : SwitchDTOResponse.valueOf(teclado.getSwitchModelo()),
                teclado.getKeycapModelo() == null ? null : KeycapDTOResponse.valueOf(teclado.getKeycapModelo()),
                teclado.getEstoque() == null ? null : EstoqueDTOResponse.valueOf(teclado.getEstoque()),
                listaCategorias,
                listaAcessorios
        );
    }
}