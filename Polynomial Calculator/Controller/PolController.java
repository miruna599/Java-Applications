import javax.swing.*;
import java.awt.event.*;
public class PolController {
    private PolView myView;

    PolController(PolView view) {
        myView = view;
        myView.addAdListener(new AdListener());
        myView.addScListener(new ScListener());
        myView.addInmListener(new InmListener());
        myView.addDerListener(new DerListener());
        myView.addIntListener(new IntListener());
        myView.addImpListener(new ImpListener());
    }

    class AdListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            String inputB = "";
            inputA = myView.getA();
            inputB = myView.getB();
            Polinom rezAdunare = new Polinom();
            rezAdunare = OperatiiPoli.adunaP(new Polinom(inputA), new Polinom(inputB));
            String s = rezAdunare.toStringPol();
            myView.setRez(s);

        }
    }

    class ScListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            String inputB = "";
            inputA = myView.getA();
            inputB = myView.getB();
            Polinom rezAdunare = new Polinom();
            rezAdunare = OperatiiPoli.scadeP(new Polinom(inputA), new Polinom(inputB));
            String s = rezAdunare.toStringPol();
            myView.setRez(s);

        }
    }

    class InmListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            String inputB = "";
            inputA = myView.getA();
            inputB = myView.getB();
            Polinom rezAdunare = new Polinom();
            rezAdunare = OperatiiPoli.inmultesteP(new Polinom(inputA), new Polinom(inputB));
            String s = rezAdunare.toStringPol();
            myView.setRez(s);

        }

    }

    class DerListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            inputA = myView.getA();
            Polinom rezAdunare = new Polinom();
            rezAdunare = OperatiiPoli.deriveazaP(new Polinom(inputA));
            String s = rezAdunare.toStringPol();
            myView.setRez(s);

        }
    }

    class IntListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            inputA = myView.getA();
            Polinom rezAdunare = new Polinom();
            rezAdunare = OperatiiPoli.integreazaP(new Polinom(inputA));
            String s = rezAdunare.toStringPol();
            myView.setRez(s);

        }
    }

    class ImpListener implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            String inputA = "";
            String inputB = "";
            inputA = myView.getA();
            inputB = myView.getB();
            Polinom rezImpCat = new Polinom();
            Polinom rezImpRest = new Polinom();
            OperatiiPoli.imparteP(new Polinom(inputA), new Polinom(inputB), rezImpCat,rezImpRest);
            String s1 = rezImpCat.toStringPol();
            String s2 = rezImpRest.toStringPol();
            myView.setRez(s1);
            myView.setRezRest(s2);

        }
    }

}
