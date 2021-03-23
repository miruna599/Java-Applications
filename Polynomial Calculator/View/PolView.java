import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class PolView<contentAB> extends JFrame {
    //textul adaugat de utilizator
    private JTextField polinomA = new JTextField(20);
    private JTextField polinomB = new JTextField(20);
    //text rezultat
    private JTextField rez = new JTextField(20);
    private JTextField rezRest = new JTextField(20);

    //butoane
    private JButton adunare = new JButton("Adunare");
    private JButton scadere = new JButton("Scadere");
    private JButton inmultire = new JButton("Inmultire");
    private JButton derivare = new JButton("Derivare");
    private JButton integrare = new JButton("Integrare");
    private JButton impartire =new JButton("Impartire");

    //constructor
    PolView(){
        //panel cu cele 2 polinoame
        setBounds(800, 500,800, 500);
        JPanel contentAB = new JPanel();
        contentAB.setLayout(new FlowLayout());
        contentAB.add(new JLabel("Polinomul A"));
        contentAB.add(polinomA);
        contentAB.add(new JLabel("Polinomul B"));
        contentAB.add(polinomB);

        //panel butoane
        JPanel butoane = new JPanel();
        butoane.setLayout(new FlowLayout());
        butoane.add(adunare);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(scadere);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(inmultire);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(derivare);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(integrare);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(impartire);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(new JLabel("Rezultat:"));
        butoane.add(rez);
        butoane.add( Box.createRigidArea(new Dimension(0,15)) );
        butoane.add(new JLabel("Rest impartire:"));
        butoane.add(rezRest);
        butoane.setLayout(new BoxLayout(butoane, BoxLayout.Y_AXIS));


        JPanel content = new JPanel();
        setContentPane(content);
        content.add(contentAB);
        content.add(butoane);

        
        this.setTitle("Operatii polinoame");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    String getA() {
        return polinomA.getText();
    }

    String getB() {
        return polinomB.getText();
    }

    void setRez(String newRez)
    {
        rez.setText(newRez);
    }

    void setRezRest(String newRezRest)
    {
        rezRest.setText(newRezRest);
    }

    void addAdListener(ActionListener ev)
    {
        adunare.addActionListener(ev);
    }

    void addScListener(ActionListener ev)
    {
        scadere.addActionListener(ev);
    }

    void addInmListener(ActionListener ev)
    {
        inmultire.addActionListener(ev);
    }
    void addDerListener(ActionListener ev)
    {
        derivare.addActionListener(ev);
    }

    void addIntListener(ActionListener ev)
    {
        integrare.addActionListener(ev);
    }
    void addImpListener(ActionListener ev) { impartire.addActionListener(ev);}


}