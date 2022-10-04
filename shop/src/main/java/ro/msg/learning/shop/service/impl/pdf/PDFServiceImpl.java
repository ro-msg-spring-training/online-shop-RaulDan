package ro.msg.learning.shop.service.impl.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.model.OrderDetail;
import ro.msg.learning.shop.service.PDFService;
import com.itextpdf.text.Document;

import javax.activation.DataSource;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class PDFServiceImpl implements PDFService {
    @Override
    public DataSource computePDF(List<OrderDetail> orderDetails) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfPTable pdfPTable = new PdfPTable(2);
            completeTable(pdfPTable,orderDetails);
            document.add(pdfPTable);
            document.close();
            byte[] bytes = outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            outputStream.close();
            return dataSource;
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void completeTable(PdfPTable pdfPTable,List<OrderDetail> orderDetails){
        PdfPCell cell = new PdfPCell(new Paragraph("Product Name"));
        pdfPTable.addCell(cell);
        cell = new PdfPCell(new Paragraph("Quantity"));
        pdfPTable.addCell(cell);
        for (OrderDetail orderDetail : orderDetails) {
            pdfPTable.addCell(new PdfPCell(new Phrase(orderDetail.getProduct().getName())));
            pdfPTable.addCell(new PdfPCell(new Phrase(orderDetail.getQuantity().toString())));
        }
    }
}
