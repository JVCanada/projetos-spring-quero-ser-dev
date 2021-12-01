package br.com.rd.MKCrudFilm.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LanguageDTO {
    private LanguageCompositeKeyDTO languageCompositeKey;
    private Date lastUpdate;
}
