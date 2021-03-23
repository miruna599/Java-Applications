package PresentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorView extends JFrame {

    private JTextField alegere  = new JTextField(5);
    private JTextField numeProdus  = new JTextField(25);
    private JButton addProd= new JButton("Adauga noul produs");

    private JTextField produsSchimbari  = new JTextField(30);
    private JButton executeSchimbari = new JButton("Realizeaza schimbarile");

    private JTextField prodToDelete  = new JTextField(30);
    private JButton deleteProd = new JButton("Elimina produsul din lista");

    private JButton seeMnuItems = new JButton("Vizualizeaza meniul");

    public AdministratorView(){

        setBounds(800, 500,800, 600);
        JPanel contentWA1 = new JPanel();
        contentWA1.setLayout(new FlowLayout());
        contentWA1.add(new JLabel("Adauga:"));
        contentWA1.add(new JLabel("1 -> pentru adaugarea unui produs de baza"));
        contentWA1.add(new JLabel("2 ->  pentru adaugarea unui produs compus"));
        contentWA1.add(alegere);

        JPanel contentWA2 = new JPanel();
        contentWA2.setLayout(new FlowLayout());
        contentWA2.add(new JLabel("Adauga numele noului produs:"));
        contentWA2.add(numeProdus);

        JPanel contentWA3 = new JPanel();
        contentWA3.setLayout(new FlowLayout());
        contentWA3.add(new JLabel("Adauga numele produsului ce trebuie editat, urmat de:"));

        JPanel contentWA5 = new JPanel();
        contentWA5.add(new JLabel("- noul nume sau 'X' si noul pret sau '0'"));
        contentWA5.add(produsSchimbari);

        JPanel contentWA4 = new JPanel();
        contentWA4.setLayout(new FlowLayout());
        contentWA4.add(new JLabel("Adauga numele produsul ce trebuie sters"));
        contentWA4.add(prodToDelete);


        JPanel butoane = new JPanel();
        butoane.setLayout(new FlowLayout());
        butoane.add(addProd);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );

        butoane.add(executeSchimbari);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );

        butoane.add(deleteProd);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(seeMnuItems);
        butoane.setLayout(new BoxLayout(butoane, BoxLayout.Y_AXIS));

        JPanel content = new JPanel();
        setContentPane(content);
        content.add(contentWA1);
        content.add(contentWA2);
        content.add(contentWA3);
        content.add(contentWA5);
        content.add(contentWA4);

        content.add(butoane);

        this.setTitle("AdministratorGUI");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String getAlegere() {
        return alegere.getText();
    }

    public String getNumeProdus() {
        return numeProdus.getText();
    }

    public String getProdusSchimbari() {
        return produsSchimbari.getText();
    }

    public String getProdToDelete() {
        return prodToDelete.getText();
    }

    public void setAlegere(String alegere) {
        this.alegere.setText(alegere);
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus.setText(numeProdus); ;
    }

    public void setProdusSchimbari(String produsSchimbari) {
        this.produsSchimbari.setText(produsSchimbari);
    }

    public void setProdToDelete(String prodToDelete) {
        this.prodToDelete.setText(prodToDelete);
    }

    void addListenerAddProd(ActionListener ev)
    {
        addProd.addActionListener(ev);
    }

    void addListenerExSchimb(ActionListener ev)
    {
        executeSchimbari.addActionListener(ev);
    }

    void addListenerDelete(ActionListener ev)
    {
        deleteProd.addActionListener(ev);
    }

    void addListenerSEE(ActionListener ev)
    {
        seeMnuItems.addActionListener(ev);
    }

}