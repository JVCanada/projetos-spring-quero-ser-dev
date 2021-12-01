package br.com.rd.JVProjetoCrudServico.model.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Vehicle {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
