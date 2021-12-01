package br.com.rd.CrudTask.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Boolean feito;
    @Column(nullable = false)
    private Date dataAtualizacao;
    @Column(nullable = false)
    private Integer porcentagem;

}
