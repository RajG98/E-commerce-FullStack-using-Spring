package com.project.ecom_proj.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date release_date;
    private boolean available;
    private int quantity;

    private String imageName;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageDate;
    private String imageType;

}
