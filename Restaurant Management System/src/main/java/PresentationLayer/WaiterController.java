package PresentationLayer;

import BusinessLayer.Restaurant;

import java.awt.event.*;
public class WaiterController {

    private WaiterView myView;
    private Restaurant restaurantul;


  public WaiterController (WaiterView view, Restaurant restaurantul) {
        this.restaurantul = restaurantul;
        this.myView = view;
        myView.addListenerAddOrder(new NewOrderListener());
        myView.addListenerPrice(new PriceListener());
        myView.addListenerBill(new BillListener());
        myView.addListenerList(new ListListener());
    }

    class NewOrderListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String newOrder = "";
            String newList = "";
            newOrder = myView.getNewOrder();
            newList = myView.getNewOrderProducts();
            restaurantul.addNewOrderWA(newOrder, newList);

        }
    }

    class PriceListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String newList="";
            newList = myView.getNewOrderProducts();
            newList = String.valueOf(restaurantul.computePriceForOrderWA(newList));
            myView.setNewOrderProductsPrice(newList);
        }
    }

    class BillListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String newList = "";
            newList = myView.getNewOrderProducts();
            restaurantul.generateBillWA(newList);

        }

    }

    class ListListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            TableWA newTable = new TableWA(restaurantul);

        }

    }


    }


