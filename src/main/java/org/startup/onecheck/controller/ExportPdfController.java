package org.startup.onecheck.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.startup.onecheck.service.PdfReportService;

import java.io.ByteArrayInputStream;

@RestController
@AllArgsConstructor
public class ExportPdfController {

    private PdfReportService pdfReportService;

    @RequestMapping(value = "/pdf/check/{checkId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> checkReport(@PathVariable Long checkId) {

        ByteArrayInputStream bis = pdfReportService.checkReport(checkId);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=receipt-"+checkId+"-report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @RequestMapping(value = "/pdf/basket", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> basketReport() {

        ByteArrayInputStream bis = pdfReportService.basketReport();

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=basketreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
