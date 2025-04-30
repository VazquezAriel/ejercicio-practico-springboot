package com.minegocio.alquimiasoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequestDto {

    @NotNull(message = "El campo id no puede estar vacío")
    private Long id;
    
    @Size(max = 100, message = "El campo name no puede tener más de 100 caracteres")
    private String name;

    @Pattern(regexp = "CED|RUC|PAS", message = "El campo identificationType debe ser uno de los siguientes: CED, RUC o PAS")
    private String identificationType;

    @Size(max = 20, message = "El campo identificationNumber no puede tener más de 20 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "El campo identificationNumber solo puede contener letras, números o guiones")
    private String identificationNumber;

    @Size(max = 100, message = "El campo email no puede tener más de 100 caracteres")
    @Email(message = "El campo email debe tener un formato válido")
    private String email;

    @Pattern(regexp = "^[0-9]+$", message = "El número de teléfono solo puede contener números")
    @Size(max = 20, message = "El número de teléfono no puede tener más de 20 caracteres")
    private String phone;

}
