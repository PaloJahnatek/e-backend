package com.palo.backend.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Integer id;
    @NotNull
    private String image;
    @Size(min = 3)
    private String name;
    @Min(1)
    private BigDecimal price;

}
