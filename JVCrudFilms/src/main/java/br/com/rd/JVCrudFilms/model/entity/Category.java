package br.com.rd.JVCrudFilms.model.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(nullable = false)
    private String name_category;

    @Column(nullable = false)
    private Date last_update;


}
