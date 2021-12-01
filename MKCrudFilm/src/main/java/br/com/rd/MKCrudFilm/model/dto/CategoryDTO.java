package br.com.rd.MKCrudFilm.model.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private Date lastUpdate;
}
