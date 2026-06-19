import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePackageReceipt {

    public static void generate(
            String username,
            String packageName,
            String persons,
            String id,
            String phone,
            String price,
            String status){

        try{

            String fileName =
                "PackageReceipt_" + username + ".pdf";

            Document document = new Document();

            PdfWriter.getInstance(
                document,
                new FileOutputStream(fileName)
            );

            document.open();

            document.add(new Paragraph(
                "TRAVEL MANAGEMENT SYSTEM"));
            document.add(new Paragraph(
                "----------------------------------------"));

            document.add(new Paragraph(""));

            document.add(new Paragraph(
                "Receipt Generated On : "
                + LocalDateTime.now().format(
                DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm:ss"))));

            document.add(new Paragraph(""));

            document.add(new Paragraph(
                "CUSTOMER DETAILS"));

            document.add(new Paragraph(
                "Username : " + username));

            document.add(new Paragraph(
                "Phone : " + phone));

            document.add(new Paragraph(""));

            document.add(new Paragraph(
                "PACKAGE DETAILS"));

            document.add(new Paragraph(
                "Package : " + packageName));

            document.add(new Paragraph(
                "Persons : " + persons));

            document.add(new Paragraph(
                "ID Number : " + id));

            document.add(new Paragraph(
                "Price : Rs. " + price));

            document.add(new Paragraph(
                "Booking Status : " + status));

            document.close();
            java.awt.Desktop.getDesktop().open(
             new java.io.File(fileName)
              );

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}