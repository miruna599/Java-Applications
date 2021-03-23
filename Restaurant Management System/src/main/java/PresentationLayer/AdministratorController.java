package PresentationLayer;
import BusinessLayer.Restaurant;
import DataLayer.FileWriter;

import java.awt.event.*;

public class AdministratorController {

    private AdministratorView myView;
    private Restaurant restaurantul;
    private String file;

    public AdministratorController (AdministratorView view, Restaurant restaurantul, String file) {
        this.restaurantul = restaurantul;
        this.myView = view;
        this.file = file;
        myView.addListenerAddProd(new NewProductListener());
        myView.addListenerExSchimb(new ExChangesListener());
        myView.addListenerDelete(new DeleteListener());
        myView.addListenerSEE(new SeeListener());
    }

    class NewProductListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String alegere = "";
            String nume = "";
            alegere = myView.getAlegere();
            nume = myView.getNumeProdus();
            restaurantul.addNewMenuItemAD(alegere, nume);
            FileWriter.serialization(restaurantul, file);

        }
    }

    class ExChangesListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String prodSchimbari="";
            prodSchimbari = myView.getProdusSchimbari();
            restaurantul.editMenuItemAD(prodSchimbari);
            FileWriter.serialization(restaurantul, file);
        }
    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String prodToDelete = "";
            prodToDelete = myView.getProdToDelete();
            restaurantul.deleteMenuItemAD(prodToDelete);
            FileWriter.serialization(restaurantul, file);

        }

    }

    class SeeListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            TableAD newTable = new TableAD(restaurantul);

        }

    }

}


