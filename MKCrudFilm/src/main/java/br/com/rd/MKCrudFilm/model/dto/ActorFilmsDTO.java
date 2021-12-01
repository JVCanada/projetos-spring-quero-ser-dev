package br.com.rd.MKCrudFilm.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActorFilmsDTO {
    private Long actorId;
    private String fullName;
    private List<FilmShortDTO> films = new ArrayList<FilmShortDTO>();

}
