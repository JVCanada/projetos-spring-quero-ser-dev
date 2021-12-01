package br.com.rd.MKCrudFilm.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
