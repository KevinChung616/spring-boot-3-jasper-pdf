package com.jtsp.springboot3jasper.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product {
    private String productId;
    private String productUPC;
    private String productName;
    private Integer quantity;
    private String note;
}
