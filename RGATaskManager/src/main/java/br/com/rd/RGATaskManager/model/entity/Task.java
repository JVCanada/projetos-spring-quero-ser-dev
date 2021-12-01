package br.com.rd.RGATaskManager.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tb_task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date updateDate;

    @Column(nullable = false)
    private Boolean done;

    @Column(nullable = false)
    private Double percentage;
}
