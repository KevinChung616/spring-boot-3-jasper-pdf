package com.jtsp.springboot3jasper.service;

import com.jtsp.springboot3jasper.model.InboundTransaction;
import com.jtsp.springboot3jasper.model.Product;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    public byte[] generateInboundReport() throws JRException {
        InboundTransaction inboundTransaction = InboundTransaction.builder()
                .inboundType("purchase")
                .date(LocalDate.now())
                .driverName("Zoey Xu")
                .id("1")
                .supplier("The Coca-Cola Company")
                .logistic("USPS")
                .receiverName("Kevin Ma")
                .warehouse("Amazon Super Warehouse")
                .productList(Arrays.asList(
                        Product.builder().productId("12310").productName("Coca-Cola Classic 12 oz (Pack of 12)").productUPC("049000050103").note("good").quantity(10).build(),
                        Product.builder().productId("12314").productName("Diet Coke 12 oz (Pack of 12)").productUPC("038000015102").note("good").quantity(20).build(),
                        Product.builder().productId("12315").productName("Sprite 12 oz (Pack of 12)").productUPC("035000768192").note("good").quantity(30).build()
                ))
                .build();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("companyName", "Amazon Same Day Delivery");
        parameters.put("warehouse", inboundTransaction.getWarehouse());
        parameters.put("date", inboundTransaction.getDate());
        parameters.put("logistic", inboundTransaction.getLogistic());
        parameters.put("supplier", inboundTransaction.getSupplier());
        parameters.put("driverName", inboundTransaction.getDriverName());
        parameters.put("receiverName", inboundTransaction.getReceiverName());
        parameters.put("inboundType", inboundTransaction.getInboundType());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(inboundTransaction.getProductList());
        InputStream reportInputStream = getClass().getResourceAsStream("/transaction.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportInputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);

    }
}
