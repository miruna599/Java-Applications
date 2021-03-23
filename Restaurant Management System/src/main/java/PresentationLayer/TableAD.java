package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableAD extends JFrame {
    Restaurant unR;
    JTable  table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    String headers[] = { "Produs", "Pret" };
    String[] data;

    public TableAD(Restaurant restaurantul) {
        unR = restaurantul;
        String[] columns = new String[]{
                "Produs", "Pret"
        };
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        scroll = new JScrollPane(table);

        insert();

        add(scroll, BorderLayout.CENTER);
        setSize(300, 300);
        setVisible(true);

    }

    private void insert() {
        ArrayList<String> ar = new ArrayList<String>();
        ArrayList<String> prices = new ArrayList<String>();
       for(MenuItem product : unR.getMenu()){
            ar.add(product.getNumeProdus());
            prices.add(String.valueOf(product.getPretProdus()));
        }

        for (int i = 0; i < ar.size(); i++)
            model.addRow(new Object[] { String.valueOf(ar.get(i)), String.valueOf(prices.get(i))});
    }
}