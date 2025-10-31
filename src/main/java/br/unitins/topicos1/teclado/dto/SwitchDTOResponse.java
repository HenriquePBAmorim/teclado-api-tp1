package br.unitins.topicos1.teclado.dto;

import br.unitins.topicos1.teclado.model.Switch;
import br.unitins.topicos1.teclado.model.TipoSwitch;

public record SwitchDTOResponse(
    Long id,
    String nome,
    String fabricante,
    TipoSwitch tipo,
    Double forcaAtuacao
) {
    public static SwitchDTOResponse valueOf(Switch s) {
        return new SwitchDTOResponse(
            s.getId(), 
            s.getNome(), 
            s.getFabricante(), 
            s.getTipo(), 
            s.getForcaAtuacao()
        );
    }
}