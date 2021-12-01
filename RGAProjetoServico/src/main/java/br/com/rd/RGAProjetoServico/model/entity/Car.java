package br.com.rd.RGAProjetoServico.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "tb_car")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "cl_brand")
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Boolean turbine;

    //@ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cl_media")
    private Media carMedia;

}
