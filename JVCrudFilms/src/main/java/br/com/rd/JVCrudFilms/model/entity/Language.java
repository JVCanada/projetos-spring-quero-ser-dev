package br.com.rd.JVCrudFilms.model.entity;

import br.com.rd.JVCrudFilms.model.embeddable.CompositeKeyLanguage;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

@Data
@Entity
public class Language {

    @EmbeddedId
    private CompositeKeyLanguage compositeKeyLanguage;

    @Column(nullable = false)
    private Date last_update;

}
