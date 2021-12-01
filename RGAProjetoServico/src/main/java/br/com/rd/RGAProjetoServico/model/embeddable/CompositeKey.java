package br.com.rd.RGAProjetoServico.model.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
//composite key is required to be serializable. //https://blog.algaworks.com/serialversionuid/
//objeto é transformada em uma sequência de bytes e é útil quando precisamos enviar objetos pela rede, salvar no disco, ou comunicar de uma JVM com outra
public class CompositeKey implements Serializable { //Object serialization is the process of converting Java objects into byte streams. We can then transfer these byte streams over the wire or store them in persistent memory
    @Column
    private Long id;
    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
