package org.startup.onecheck.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.startup.onecheck.model.dto.CheckDto;
import org.startup.onecheck.model.dto.CheckProductDto;
import org.startup.onecheck.model.dto.ProductDto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
@Log
public class PdfReportService {

    private CheckService checkService;

    private BasketService basketService;

    public ByteArrayInputStream basketReport() throws IOException {
        List<CheckDto> currentChecks = basketService.findCurrentChecks();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, out);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.open();
        for (CheckDto checkDto : currentChecks) {
            Long checkId = checkDto.getId();


            CheckDto check = checkService.findById(checkId);
            List<ProductDto> products = check.getProducts();
            List<CheckProductDto> checkProductDtos = basketService.map(products);

            try {

                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(80);
                table.setWidths(new int[]{3, 1, 1});

                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

                PdfPCell cells;
                cells = new PdfPCell(new Phrase("Product", headFont));
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cells);

                cells = new PdfPCell(new Phrase("Count", headFont));
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cells);

                cells = new PdfPCell(new Phrase("TotalPrice", headFont));
                cells.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cells);

                double total = 0d;
                for (CheckProductDto checkProductDto : checkProductDtos) {

                    PdfPCell cell;

                    ProductDto product = checkProductDto.getProduct();
                    cell = new PdfPCell(new Phrase(product.getProductName()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    long count = checkProductDto.getCount();
                    cell = new PdfPCell(new Phrase(String.valueOf(count)));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setPaddingRight(5);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    double productTotal = product.getPrice() * count;
                    total += productTotal;
                    cell = new PdfPCell(new Phrase(productTotal + " $"));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }

                String receiptTitle = checkId == 1L ? "My"
                        : check.getUser().getFirstName();
                receiptTitle += " purchase receipt" + "\n";
                Paragraph userNameReceipt = new Paragraph();
                userNameReceipt.add(receiptTitle);
                userNameReceipt.setAlignment(Element.ALIGN_CENTER);


                Paragraph totalCoast = new Paragraph();
                totalCoast.add(String.format(Locale.ENGLISH, "Total: %(.2f $", total));
                totalCoast.setAlignment(Element.ALIGN_RIGHT);


                document.add(userNameReceipt);
                document.add(Chunk.NEWLINE);
                document.add(table);
                document.add(totalCoast);
                document.add(Chunk.NEWLINE);


            } catch (DocumentException ex) {

                log.info("Error occurred: " + ex);
            }


        }
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream checkReport(Long checkId) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CheckDto check = checkService.findById(checkId);
        List<ProductDto> products = check.getProducts();
        List<CheckProductDto> checkProductDtos = basketService.map(products);

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80);
            table.setWidths(new int[]{3, 1, 1});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell cells;
            cells = new PdfPCell(new Phrase("Product", headFont));
            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cells);

            cells = new PdfPCell(new Phrase("Count", headFont));
            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cells);

            cells = new PdfPCell(new Phrase("TotalPrice", headFont));
            cells.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cells);

            double total = 0d;
            for (CheckProductDto checkProductDto : checkProductDtos) {

                PdfPCell cell;

                ProductDto product = checkProductDto.getProduct();
                cell = new PdfPCell(new Phrase(product.getProductName()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                long count = checkProductDto.getCount();
                cell = new PdfPCell(new Phrase(String.valueOf(count)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setPaddingRight(5);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                double productTotal = product.getPrice() * count;
                total += productTotal;
                cell = new PdfPCell(new Phrase(productTotal + " $"));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            String receiptTitle = checkId == 1L ? "My"
                    : check.getUser().getFirstName();
            receiptTitle += " purchase receipt" + "\n";
            Paragraph userNameReceipt = new Paragraph();
            userNameReceipt.add(receiptTitle);
            userNameReceipt.setAlignment(Element.ALIGN_CENTER);


            Paragraph totalCoast = new Paragraph();
            totalCoast.add(String.format(Locale.ENGLISH, "Total: %(.2f $", total));
            totalCoast.setAlignment(Element.ALIGN_RIGHT);

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(userNameReceipt);
            document.add(Chunk.NEWLINE);
            document.add(table);
            document.add(totalCoast);
            document.add(Chunk.NEWLINE);

            document.close();

        } catch (DocumentException ex) {

            log.info("Error occurred: " + ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
