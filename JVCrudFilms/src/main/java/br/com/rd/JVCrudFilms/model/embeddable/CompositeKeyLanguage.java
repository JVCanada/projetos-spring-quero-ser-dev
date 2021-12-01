package br.com.rd.JVCrudFilms.model.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class CompositeKeyLanguage implements Serializable {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String name_language;





}
