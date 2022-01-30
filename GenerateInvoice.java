package com.hammad.jfx1;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.IOException;

public class GenerateInvoice {


    String invoiceFullname;
    String invoiceLicense ;
    String invoicePlateNum ;

    public GenerateInvoice(String invoiceFullname, String invoiceLicense, String invoicePlateNum, String invoiceCarMake, String invoiceCarModel,String invoiceRentedDays, String invoiceTotalCost) {
        this.invoiceFullname = invoiceFullname;
        this.invoiceLicense = invoiceLicense;
        this.invoicePlateNum = invoicePlateNum;
        this.invoiceCarMake = invoiceCarMake;
        this.invoiceRentedDays = invoiceRentedDays;
        this.invoiceTotalCost = invoiceTotalCost;
        this.invoiceCarModel = invoiceCarModel;
    }

    String invoiceCarMake ;
    String invoiceRentedDays;
    String invoiceTotalCost;
    String invoiceCarModel;





    public void generateBill() throws IOException {

        int fontSize8 = 8;
        int xCoordinate = 200;

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A6);
        document.addPage( page );

        PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Users\\Hammad\\Desktop\\JAVAFX\\jfx-1\\src\\main\\resources\\com\\hammad\\jfx1\\car next-logos_black.png", document);

// Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.COURIER;
        PDFont font1 = PDType1Font.COURIER_BOLD;

// Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);


// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"

	contentStream.drawImage(pdImage, 100, 330, 100,100);

        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.moveTextPositionByAmount( 125, 330 );
        contentStream.drawString( "Invoice" );
        contentStream.endText();



        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,280);
        contentStream.drawString( "Full Name:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,280);
        contentStream.drawString( invoiceFullname );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,260);
        contentStream.drawString( "License Number:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,260);
        contentStream.drawString( invoiceLicense);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,240);
        contentStream.drawString( "Car Make:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,240);
        contentStream.drawString( invoiceCarMake);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,220);
        contentStream.drawString( "Car Model:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,220);
        contentStream.drawString( invoiceCarModel);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,200);
        contentStream.drawString( "Car Plate Number:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,200);
        contentStream.drawString( invoicePlateNum);
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,180);
        contentStream.drawString( "Days Rented:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,180);
        contentStream.drawString( invoiceRentedDays);
        contentStream.endText();



        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,160);
        contentStream.drawString( "-------------------------------------------------" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,140);
        contentStream.drawString( "Total Cost:" );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font1,fontSize8);
        contentStream.moveTextPositionByAmount(xCoordinate,140);
        contentStream.drawString( "$ "+invoiceTotalCost );
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font,fontSize8);
        contentStream.moveTextPositionByAmount(30,120);
        contentStream.drawString( "-------------------------------------------------" );
        contentStream.endText();





// Make sure that the content stream is closed:
        contentStream.close();
        


// Save the results and ensure that the document is properly closed:
        document.save("C:\\Users\\Hammad\\Desktop\\CAT201 PROJECT\\invoice.pdf");
        document.close();



    }
}

