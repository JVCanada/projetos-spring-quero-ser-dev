package br.com.rd.CrudCarrinho.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;




}
