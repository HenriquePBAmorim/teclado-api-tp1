package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Keycap;
import br.unitins.topicos1.teclado.model.PerfilKeycap;

public record KeycapDTOResponse(
    Long id,
    String nome,
    String material,
    PerfilKeycap perfil,
    String cor
) {
    public static KeycapDTOResponse valueOf(Keycap k) {
        return new KeycapDTOResponse(
            k.getId(),
            k.getNome(),
            k.getMaterial(),
            k.getPerfil(),
            k.getCor()
        );
    }
}