package br.com.rd.JVCrudFilms.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FilmDTO {

    private Long film_id;
    private String title_film;
    private String description;
    private Integer release_year;
    private LanguageDTO languageDTO;
    private Double rental_rate;
    private Date last_update;
    private CategoryDTO categoryDTO;

}
