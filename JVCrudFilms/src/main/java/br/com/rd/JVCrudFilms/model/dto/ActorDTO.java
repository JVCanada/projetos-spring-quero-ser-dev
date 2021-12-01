package br.com.rd.JVCrudFilms.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ActorDTO {

    private Long actor_id;
    private String first_name;
    private String last_name;
    private Date last_update;

}
