package br.com.rd.RGATaskManager.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private Long id;
    private String description;
    private Date updateDate;
    private Boolean done;
    private Double percentage;
}
