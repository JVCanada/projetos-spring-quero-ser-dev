package br.com.rd.MKCrudFilm.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class LanguageCompositeKey implements Serializable {
    @Column
    private String country;

    @Column
    private String name;
}
