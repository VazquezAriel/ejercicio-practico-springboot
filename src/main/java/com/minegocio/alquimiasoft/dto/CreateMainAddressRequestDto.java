package com.minegocio.alquimiasoft.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CreateMainAddressRequestDto {

    @NotBlank(message = "El campo description no puede estar vacío")
    @Size(max = 200, message = "El campo description no puede tener más de 200 caracteres")
    private String description;

    @NotBlank(message = "El campo city no puede estar vacío")
    @Size(max = 50, message = "El campo city no puede tener más de 50 caracteres")
    private String city;

    @NotBlank(message = "El campo description no puede estar vacío")
    @Size(max = 50, message = "El campo description no puede tener más de 50 caracteres")
    private String state;

}
