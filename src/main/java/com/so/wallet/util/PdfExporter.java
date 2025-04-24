package com.so.wallet.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PdfExporter {

    public static void exportRapports(HttpServletResponse response, List<String> lignes) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font titreFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titre = new Paragraph("Liste des Rapports", titreFont);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titre);

        document.add(new Paragraph(" ")); // espace

        for (String ligne : lignes) {
            document.add(new Paragraph(ligne));
        }

        document.close();
    }


    public static void exportTransactions(HttpServletResponse response, List<String> lignes) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font titreFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Paragraph titre = new Paragraph("Liste des Transactions", titreFont);
        titre.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(titre);

        document.add(new Paragraph(" ")); // espace

        for (String ligne : lignes) {
            document.add(new Paragraph(ligne));
        }

        document.close();
    }
}
