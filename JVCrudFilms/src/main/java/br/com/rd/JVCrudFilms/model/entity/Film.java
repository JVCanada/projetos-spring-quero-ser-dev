package br.com.rd.JVCrudFilms.model.entity;
import br.com.rd.JVCrudFilms.model.embeddable.CompositeKeyLanguage;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long film_id;

    @Column(nullable = false)
    private String title_film;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private Integer release_year;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "country"), @JoinColumn(name = "name_language")
    })
    private Language language;

    @Column(nullable = false)
    private Double rental_rate;

    @Column(nullable = false)
    private Date last_update;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

}
