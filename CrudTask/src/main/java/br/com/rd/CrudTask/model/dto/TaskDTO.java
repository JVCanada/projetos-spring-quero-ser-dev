package br.com.rd.CrudTask.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TaskDTO {

    private Long id;
    private String descricao;
    private Boolean feito;
    private Date dataAtualizacao;
    private Integer porcentagem;
}
