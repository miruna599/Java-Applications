package PresentationLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class TableWA extends JFrame {
    Restaurant unR;
    JTable  table = new JTable();
    DefaultTableModel model = new DefaultTableModel();
    JScrollPane scroll;
    String headers[] = { "Lista comenzi" };
    String[] data;

    public TableWA(Restaurant restaurantul) {
        unR = restaurantul;
        String[] columns = new String[]{
                "Lista comenzi"
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

        for(Map.Entry<Order, ArrayList<MenuItem>> entry : unR.getMap().entrySet())
        {
            ArrayList<MenuItem> orderFromAClient;
            orderFromAClient = entry.getValue();
            String mes="";
            for(MenuItem anItem : orderFromAClient )
                {
                    mes+=anItem.getNumeProdus()+" ";
                 }
            ar.add(mes);
        }

        for (int i = 0; i < ar.size(); i++)
            model.addRow(new Object[] { String.valueOf(ar.get(i))});
    }
}