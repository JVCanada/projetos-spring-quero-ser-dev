package br.com.rd.JVCrudFilms.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LanguageDTO {

    private CompositeKeyLanguageDTO compositeKeyLanguageDTO;
    private Date last_update;
}
