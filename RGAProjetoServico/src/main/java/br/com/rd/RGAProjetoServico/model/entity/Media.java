package br.com.rd.RGAProjetoServico.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull(message = "Description field is empty")
    @Column(nullable = false) //It's used mainly in the DDL schema metadata generation. This means that if we let Hibernate generate the database schema automatically, it applies the not null constraint to the particular database column
    private String description;
    //@NotNull(message = "Touch field is empty")
    @Column(nullable = false) //It's used mainly in the DDL schema metadata generation. This means that if we let Hibernate generate the database schema automatically, it applies the not null constraint to the particular database column
    private Boolean touch;
}
