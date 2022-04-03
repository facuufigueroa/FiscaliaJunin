package Fiscalia.junin.ExportarPdf;


import Fiscalia.junin.Model.Causa;
import Fiscalia.junin.Model.Informacion2;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component("/causas")
public class ExportarPdf {

    List<Causa> listadoCausas;


    public ExportarPdf(List<Causa> causas) {
        this.listadoCausas = causas;

    }

    public void writeTableHeader(PdfPTable tablaCausas){
        PdfPCell cell = new PdfPCell();

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPhrase(new Phrase("Numero de expediente",font));
        tablaCausas.addCell(cell);

        cell.setPhrase(new Phrase("Victima",font));
        tablaCausas.addCell(cell);

        cell.setPhrase(new Phrase("Victimario",font));
        tablaCausas.addCell(cell);

        cell.setPhrase(new Phrase("Fecha", font));
        tablaCausas.addCell(cell);

        cell.setPhrase(new Phrase("Contexto", font));
        tablaCausas.addCell(cell);
    }
    public void writeTableData(PdfPTable tablaCausas){
        for(Causa causas : listadoCausas){

            tablaCausas.addCell(causas.getNumExpediente());
            tablaCausas.addCell(causas.getVictima());
            tablaCausas.addCell(causas.getVictimario());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String fechaComoCadena = sdf.format(causas.getFecha2());
            tablaCausas.addCell(fechaComoCadena);

            tablaCausas.addCell(causas.getContexto());
        }

    }


    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("LISTADO DE CAUSAS", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }


}
