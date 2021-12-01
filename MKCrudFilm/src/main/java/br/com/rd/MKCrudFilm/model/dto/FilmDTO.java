package br.com.rd.MKCrudFilm.model.dto;

import br.com.rd.MKCrudFilm.model.entity.Actor;
import br.com.rd.MKCrudFilm.model.entity.Category;
import br.com.rd.MKCrudFilm.model.entity.Language;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class FilmDTO {
    private Long id;
    private String title;
    private String description;
    private Integer releaseYear;
    private LanguageDTO language;
    private Double rentalRate;
    private Date lastUpdate;
    private CategoryDTO category;
    private List<ActorDTO> actors;
}
