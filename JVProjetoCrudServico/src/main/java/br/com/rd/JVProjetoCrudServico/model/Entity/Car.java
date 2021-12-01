package br.com.rd.JVProjetoCrudServico.model.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @NotNull(message = "Brand field is empty")
    @Column(nullable = false)
    private String brand;
    // @NotNull(message = "Model field is empty")
    @Column(nullable = false)
    private String model;
    // @NotNull(message = "Year field is empty")
    @Column(nullable = false)
    private Integer year;
    // @NotNull(message = "Color field is empty")
    @Column(nullable = false)
    private String color;
    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "cl_media")
    private Media carMedia;
//    @ManyToOne(fetch = FetchType.EAGER)


}