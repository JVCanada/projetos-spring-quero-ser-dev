package br.com.rd.RGAProjetoServico.model.dto;

public class VehicleDTO {
    private Long id;
    private String brand;
    private String model;
    private Integer year;

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getModel(){
        return this.model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public Integer getYear(){
        return this.year;
    }

    public void setYear(Integer year){
        this.year = year;
    }
}
