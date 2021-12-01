package br.com.rd.MKCrudFilm.model.dto;

import br.com.rd.MKCrudFilm.model.entity.Film;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ActorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Date lastUpdate;

}
