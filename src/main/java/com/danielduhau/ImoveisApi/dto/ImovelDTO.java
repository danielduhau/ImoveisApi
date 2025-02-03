package com.danielduhau.ImoveisApi.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ImovelDTO {

    @NotBlank(message = "Name is required")
    private String matricula;

    private String nomeProprietario;

    private String endereco;
}
