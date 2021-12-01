package br.com.rd.RGAProjetoServico.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ClassTable {
    @Id
    private String myId;
    @Column //Se eu coloco um @Id (para gerar uma chave composta) = Caused by: org.hibernate.MappingException: Composite-id class must implement Serializable: br.com.rd.RGAProjetoServico.model.entity.ClassTable
    private String name;

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
