package br.com.rd.JVCrudFilms.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {

    private Long category_id;
    private String name_category;
    private Date last_update;

}
