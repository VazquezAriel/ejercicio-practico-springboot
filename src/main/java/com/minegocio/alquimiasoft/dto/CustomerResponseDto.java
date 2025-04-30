package com.minegocio.alquimiasoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String identificationType;
    private String identificationNumber;
    private String email;
    private String phone;
    private AddressResponseDto mainAddress;
}
