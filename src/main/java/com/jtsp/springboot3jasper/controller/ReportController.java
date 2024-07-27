package com.jtsp.springboot3jasper.controller;

import com.jtsp.springboot3jasper.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("report")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("inbound")
    public ResponseEntity<byte[]> generateReport() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "InboundReport.pdf");
            return ResponseEntity.ok().headers(headers).body(reportService.generateInboundReport());
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
