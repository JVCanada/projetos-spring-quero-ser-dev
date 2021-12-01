package br.com.rd.RGAProjetoServico.model.dto;

import lombok.Data;

@Data
public class CarDTO {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private Boolean turbine;
    private MediaDTO media;
}
