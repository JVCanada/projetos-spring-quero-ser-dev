package br.com.rd.JVProjetoCrudServico.model.dto;

import lombok.Data;

@Data
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private String color;
    private MediaDTO media;


}
