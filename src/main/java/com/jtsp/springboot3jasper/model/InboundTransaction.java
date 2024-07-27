package com.jtsp.springboot3jasper.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InboundTransaction {

    private String id;
    private String logistic;
    private String receiverName;
    private String driverName;
    private String supplier;
    private LocalDate date;
    private String warehouse;
    private String inboundType;
    private List<Product> productList;
}
