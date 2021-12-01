package br.com.rd.MKCrudFilm.model.dto;

import lombok.Data;

@Data
public class FilmShortDTO {
    private Long filmId;
    private String title;
    private Integer releaseYear;
    private String description;
}
