package PresentationLayer;
import BusinessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ChefView extends JFrame implements PropertyChangeListener {

    private JTextField newOrderComp  = new JTextField(50);
    private int numberOrders = 0;
    private Restaurant resta;

    public ChefView(Restaurant restaurantul){

        resta = restaurantul;

        setBounds(800, 500,800, 500);
        JPanel contentChef = new JPanel();
        contentChef.add(newOrderComp);

        JPanel content = new JPanel();
        setContentPane(content);
        content.add(contentChef);


        this.setTitle("ChefView");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        resta.addPropertyChangeListener(this);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.numberOrders++;
        newOrderComp.setText("Am primit un nou order ce contine un produs compus!"+String.valueOf(numberOrders));

    }



}