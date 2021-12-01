package br.com.rd.JVProjetoCrudServico.model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Boolean touch;
}
