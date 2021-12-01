package br.com.rd.RGAProjetoServico.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "cl_id_comp", referencedColumnName = "id"),
        @JoinColumn(name = "cl_name_comp", referencedColumnName = "name")
    })
    private CompositeKeyTable compositeKeyTable;


}
