package presentationLayer;

import businessLayer.ClientBLL;
import businessLayer.ComenziBLL;
import businessLayer.ProdusBLL;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Client;
import model.Comenzi;
import model.Produs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * "reportPDF" este clasa ce se ocupa cu generarea documentelor PDF in urma
 * detectarii, in fisierul Input.txt, a comenzii "Report".
 * La fiecare comanda "Report -nume tabel-" va fi scris, intr-un fisier PDF,
 * un tabel al tuturor datelor existente in tabelul respectiv, la momentul
 * cererii raportului (instructiunie echivalenta cu SELECT *). Cele trei
 * tabele vizare de aceasta comanda sunt: "Client", "Produs" si "Comenzi".
 */
public class reportPDF {
    /**
     *
     * @param pdfNume - numele fisierului in care va fi adaugat tabelul
     * @param nrColoane - numarul de coloane al tabelului
     * @param coloane - lista numelor fiecarei coloane din tabel
     * @param selectie - in functie de valoare, se face afisarea dintr-un anumit tabel
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public reportPDF(String pdfNume, int nrColoane, String[] coloane, int selectie) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfNume));

        document.open();
        PdfPTable table = new PdfPTable(nrColoane);
        addTableHeader(table,coloane);
        addRows(table,selectie);
        document.add(table);
        document.close();
    }

    /**
     * Formarea header-ului - contine numele fiecarei coloane
     *
     * @param table - tabelul format
     * @param coloane - lista numelor fiecarei coloane din tabel
     */
    private void addTableHeader(PdfPTable table, String[] coloane) {
        String[] col=coloane;
        Stream.of(col)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(3);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    /**
     * In functie de selectie, va fi creat un obiect "ArrayList" de "T" unde T
     * reprezinta unul dintre urmatoarele obiecte : Client, Produs sau Comenzi.
     * Obiectul "ArrayList" va avea valoarea returnata de functia de selectie
     * din fiecare tabel ( SELECT * ), apelata prin intermediul obiectului BLL asociat,
     * Valoarea este chiar lista de clienti/produse/comenzi, ce va fi parcursa.
     * Pentru fiecare element parcurs se va forma cate o celula cu valoarea fiecarei variabile instanta.
     *
     *
     * @param table - tabelul format
     * @param selectie - valoarea 1 - tabela "Client"
     *                 - valoarea 2 - tabela "Produs"
     *                 - valoarea 3 - tabela "Comenzi"
     */
    private void addRows(PdfPTable table,int selectie) {
        if(selectie==1)
        {
            ArrayList<Client> selectClienti = new ArrayList<Client>();
            ClientBLL tabelClienti = new ClientBLL();
            selectClienti = tabelClienti.selecteazaTotiClientii();
            for(Client unC: selectClienti)
            {
                table.addCell(String.valueOf(unC.getId()));
                table.addCell(unC.getNume());
                table.addCell(unC.getAdresa());
            }

        }
        if(selectie==2)
        {
            ArrayList<Produs> selectProduse = new ArrayList<Produs>();
            ProdusBLL tabelProdus = new ProdusBLL();
            selectProduse = tabelProdus.selecteazaToateProdusele();
            for(Produs unP: selectProduse)
            {
                table.addCell(unP.getNume());
                table.addCell(String.valueOf(unP.getPret()));
            }
        }
        if(selectie==3)
        {
            ArrayList<Comenzi> selectComenzi = new ArrayList<Comenzi>();
            ComenziBLL tabelComenzi = new ComenziBLL();
            selectComenzi = tabelComenzi.selecteazaToateComenzile();
            for(Comenzi oC: selectComenzi)
            {
                table.addCell(String.valueOf(oC.getId()));
                table.addCell(oC.getClient());
                table.addCell(oC.getProdus());
                table.addCell(String.valueOf(oC.getCantitate()));
            }
        }
    }

}
