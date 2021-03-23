package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaiterView extends JFrame {

    private JTextField newOrder  = new JTextField(20);
    private JTextField newOrderProducts  = new JTextField(40);
    private JButton executeNewOrder = new JButton("Plaseaza comanda");

    private JTextField newOrderProductsPrice= new JTextField(20);
    private JButton executeNewOrderPrice = new JButton("Calculeaza pretul comenzii");
    private JButton generateBill = new JButton("Scrie nota de plata");

    private JButton listaOrders = new JButton("Vizualizeaza lista de comenzi");



    public WaiterView(){

        setBounds(800, 500,800, 500);
        JPanel contentWA1 = new JPanel();
        contentWA1.setLayout(new FlowLayout());
        contentWA1.add(new JLabel("Adauga datele unui order"));
        contentWA1.add(newOrder);

        JPanel contentWA2 = new JPanel();
        contentWA2.setLayout(new FlowLayout());
        contentWA2.add(new JLabel("Adauga produsele asociate acestui order"));
        contentWA2.add(newOrderProducts);

        JPanel contentWA3 = new JPanel();
        contentWA3.setLayout(new FlowLayout());
        contentWA3.add(new JLabel("Pretul comenzii"));
        contentWA3.add(newOrderProductsPrice);

        //panel butoane
        JPanel butoane = new JPanel();
        butoane.setLayout(new FlowLayout());
        butoane.add(executeNewOrder);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );

        butoane.add(executeNewOrderPrice);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );

        butoane.add(generateBill);

        butoane.add( Box.createRigidArea(new Dimension(0,15)) );

        butoane.add(listaOrders);
        butoane.setLayout(new BoxLayout(butoane, BoxLayout.Y_AXIS));

        JPanel content = new JPanel();
        setContentPane(content);
        content.add(contentWA1);
        content.add(contentWA2);
        content.add(contentWA3);

        content.add(butoane);

        this.setTitle("WaiterGUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String getNewOrder() {
        return newOrder.getText();
    }

    public String getNewOrderProducts() {
        return newOrderProducts.getText();
    }

    public String  getNewOrderProductsPrice() {
        return newOrderProductsPrice.getText();
    }

    public void setNewOrder(String order)
    {
        newOrder.setText(order);
    }

    public void setNewOrderProducts(String listProducts)
    {
        newOrderProducts.setText(listProducts);
    }

    public void setNewOrderProductsPrice(String productsPrice)
    {
        newOrderProductsPrice.setText(productsPrice);
    }

    public void addListenerAddOrder(ActionListener ev)
    {
        executeNewOrder.addActionListener(ev);
    }

    public void addListenerPrice(ActionListener ev)
    {
        executeNewOrderPrice.addActionListener(ev);
    }

    public void addListenerBill(ActionListener ev)
    {
        generateBill.addActionListener(ev);
    }

    public void addListenerList(ActionListener ev)
    {
        listaOrders.addActionListener(ev);
    }

}