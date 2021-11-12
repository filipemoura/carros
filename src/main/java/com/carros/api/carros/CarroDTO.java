package com.carros.api.carros;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class CarroDTO {
    private Long id;
    private String nome;
//    @JsonIgnore
    private String tipo;

    public static CarroDTO create(Carro carro) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(carro, CarroDTO.class);
    }
}