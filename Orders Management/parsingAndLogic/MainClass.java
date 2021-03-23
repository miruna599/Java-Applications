package parsingAndLogic;
import com.itextpdf.text.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Scanner;

import com.itextpdf.text.pdf.PdfWriter;
import model.Client;
import businessLayer.ClientBLL;
import businessLayer.ComenziBLL;
import businessLayer.ProdusBLL;
import businessLayer.StocBLL;
import model.Comenzi;
import model.Produs;
import model.Stoc;
import presentationLayer.reportPDF;

/**
 * "MainClass" este clasa in care se realizeaza citirea din fisierul text.
 * Fisierul text va contine cuvinte cheie precum "insert", "delete", "order"
 * sau "report". In functie de cuvantul cheie, se vor realiza operatiile necesare
 * ce duc la realizarea comenzii, tratand si exceptiile ce pot aparea.
 * De exemplu, pentru a putea sterge un client, trebuie sterse mai intai comenzile lui
 * din tabela "Comenzi", deoarece coloana "Client" in tabela "Comenzi" referentiaza
 * coloana "Nume" din tabela "Client". Toate cazurile ce pot aparea sunt explicate
 * in detaliu in documentatie.
 * @author Stefanovici Miruna
 *
 */

public class MainClass {

    public static void main(String[] args) throws SQLException, FileNotFoundException, DocumentException {
       // File file = new File("D:\\UTCN\\An II\\Sem. II\\TP\\Laborator\\Tema3\\Input.txt");
        File file = new File(args[0]);
        Scanner scan = new Scanner(file);

        ClientBLL clientBLL = new ClientBLL();
        ProdusBLL produsBLL = new ProdusBLL();
        ComenziBLL comenziBLL = new ComenziBLL();
        StocBLL stocBLL = new StocBLL();
        int idClient=0;
        int idComanda=0;
        int numarReport=0;
        int numarRaportClienti=0;
        int numarRaportProduse=0;
        int numarRaportComenzi=0;

        while(scan.hasNextLine())
        {
            String read;


            String command;
            String fields;

            String[] commandSplit;
            read = scan.nextLine();

            if(read.contains("Report"))
            {
                commandSplit = read.split(" ");
                command = commandSplit[0];
            }
            else {
                commandSplit = read.split(":");
                command = commandSplit[0];
            }

            if( command.contentEquals("Insert client"))
            {   idClient++;
                fields = commandSplit[1];
                String[] fieldsSplit = fields.split(",");
                String numeClient = fieldsSplit[0];
                String adresaClient = fieldsSplit[1];
                Client unNouClient = new Client(idClient, numeClient, adresaClient);
                clientBLL.adaugaClient(unNouClient);

            }

            if( command.contentEquals("Delete client") )
            {
                    //Comanda va fi "Delete client: -nume-"
                String numeClient = commandSplit[1];
                if(comenziBLL.cautaComandaClient(numeClient)==1)
                {
                    comenziBLL.stergeComandaAUnuiClient(numeClient);
                    clientBLL.stergeClient(numeClient);

                }
                else
                {
                    clientBLL.stergeClient(numeClient);
                }
            }

            if( command.contentEquals("Insert product") )
            {
                fields = commandSplit[1];
                String[] fieldsSplit = fields.split(",");
                String numeProdus = fieldsSplit[0];
                int cantitateProdus = Integer.parseInt(fieldsSplit[1]);
                float pretProdus = Float.parseFloat(fieldsSplit[2]);
                if(produsBLL.cautaProdus(numeProdus)==0)
                {
                    Produs unNouProdus = new Produs(numeProdus, pretProdus);
                    Stoc stoculProdusul = new Stoc(numeProdus, cantitateProdus);
                    produsBLL.adaugaProdus(unNouProdus);
                    stocBLL.adaugaStoc(stoculProdusul);
                }
                else
                {
                    stocBLL.actualizeazaCantitateAdaugare(cantitateProdus, numeProdus);
                }
            }

            if( command.contentEquals("Delete product") )
            {
                String numeProdus = commandSplit[1];
                if(comenziBLL.cautaComandaProdus(numeProdus)==0)
                {
                    stocBLL.stergeStoc(numeProdus);
                    produsBLL.stergeProdus(numeProdus);
                }
                else
                {
                    stocBLL.stergeStoc(numeProdus);
                    produsBLL.stergeProdus(numeProdus);
                    comenziBLL.stergeComandaCuProdus(numeProdus);


                }
            }

            if( command.contentEquals("Order") )
            {   idComanda++;
                fields = commandSplit[1];
                String[] fieldsSplit = fields.split(",");
                String numeClient = fieldsSplit[0];
                String numeProdus = fieldsSplit[1];
                int cantitateProdus = Integer.parseInt(fieldsSplit[2]);
                Comenzi oNouaComanda = new Comenzi(idComanda, numeClient, numeProdus, cantitateProdus);
                if(produsBLL.cautaProdus(numeProdus)==1)
                {
                    if(stocBLL.verificaStoc(numeProdus, cantitateProdus)==1) {
                        comenziBLL.adaugaComanda(oNouaComanda);
                        stocBLL.actualizeazaCantitateScadere(cantitateProdus, numeProdus);
                        numarReport++;

                        String stringNumarReport=String.valueOf(numarReport);
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream("ReportBill"+stringNumarReport+".pdf"));
                        float price = produsBLL.cautaPret(numeProdus);
                        document.open();
                        Font font = FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK);
                        float total = price * cantitateProdus;
                        Chunk chunk = new Chunk("FACTURA:"+"client-"+numeClient+","+"produs-"+numeProdus+","+"total pret-"+total, font);

                        document.add(chunk);
                        document.close();
                    }
                    else {
                        numarReport++;

                        String stringNumarReport=String.valueOf(numarReport);
                        Document document2 = new Document();
                        PdfWriter.getInstance(document2, new FileOutputStream("ReportBill"+stringNumarReport+".pdf"));
                        document2.open();
                        Font font = FontFactory.getFont(FontFactory.COURIER, 14, BaseColor.BLUE);
                        Chunk chunk2 = new Chunk("Under-stock!", font);

                        document2.add(chunk2);
                        document2.close();

                        }
                }

            }

            if( command.contentEquals("Report" ) )
            {
                String alegereTabel = commandSplit[1];
                if(alegereTabel.contains("client"))
                {
                    String[] tabelClienti = { "Id", "Nume", "Adresa"};
                    numarRaportClienti++;
                    String numeC=String.valueOf(numarRaportClienti);
                    String numePDFC="RaportClient"+numeC+".pdf";
                    reportPDF raportNouClient = new reportPDF(numePDFC, 3, tabelClienti, 1);
                }
                if(alegereTabel.contains("product"))
                {
                    String[] tabelProduse = { "Nume", "Pret"};
                    numarRaportProduse++;
                    String numeP=String.valueOf(numarRaportProduse);
                    String numePDFP="RaportProdus"+numeP+".pdf";
                    reportPDF raportNouProdus = new reportPDF(numePDFP, 2, tabelProduse, 2);
                }
                if(alegereTabel.contains("order"))
                {
                    String[] tabelComenzi = { "Id", "Client", "Produs", "Cantitate"};
                    numarRaportComenzi++;
                    String numeCo=String.valueOf(numarRaportComenzi);
                    String numePDFCo="RaportComanda"+numeCo+".pdf";
                    reportPDF raportNouComenzi = new reportPDF(numePDFCo, 4, tabelComenzi, 3);

                }


            }

        }


    }


}
