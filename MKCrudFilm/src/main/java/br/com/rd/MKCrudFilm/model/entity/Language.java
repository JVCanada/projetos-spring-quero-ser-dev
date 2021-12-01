package br.com.rd.MKCrudFilm.model.entity;

import br.com.rd.MKCrudFilm.model.embeddable.LanguageCompositeKey;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "tb_language")
public class Language {
    @EmbeddedId
    private LanguageCompositeKey languageCompositeKey;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
}
