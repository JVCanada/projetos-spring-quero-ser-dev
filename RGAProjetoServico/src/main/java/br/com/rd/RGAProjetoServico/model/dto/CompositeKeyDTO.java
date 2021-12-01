package br.com.rd.RGAProjetoServico.model.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CompositeKeyDTO {
    private Long id;
    private String name;
}
