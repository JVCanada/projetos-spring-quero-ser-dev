package br.com.rd.RGAProjetoServico.model.entity;

import br.com.rd.RGAProjetoServico.model.embeddable.CompositeKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class CompositeKeyTable {
    @EmbeddedId
    private CompositeKey compositeKey;
    private String anotherName;

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }
}
