package com.minegocio.alquimiasoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {

    private Long id;
    private String city;
    private String state;
    private boolean main;

}
