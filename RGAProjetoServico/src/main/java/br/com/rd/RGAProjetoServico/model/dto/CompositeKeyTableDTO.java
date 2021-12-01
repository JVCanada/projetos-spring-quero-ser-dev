package br.com.rd.RGAProjetoServico.model.dto;

import lombok.Data;

@Data
public class CompositeKeyTableDTO {
    private CompositeKeyDTO compositeKey;
    private String anotherName;
}
