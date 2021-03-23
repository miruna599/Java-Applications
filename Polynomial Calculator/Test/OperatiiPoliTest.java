import org.junit.Test;

import static org.junit.Assert.*;

public class OperatiiPoliTest {


    @Test
    public void adunaP() {
        Polinom A = new Polinom ("X^3-5X^2-2");
        Polinom B = new Polinom ("-1X^3-1X^2+1");
        Polinom rezultat = new Polinom();
        rezultat=OperatiiPoli.adunaP(A,B);
        assertEquals("-6.0X^2-1.0",rezultat.toStringPol());
        System.out.println("Adunare corecta!");
    }

    @Test
    public void scadeP() {
        Polinom A = new Polinom ("X^3-5X^2-2");
        Polinom B = new Polinom ("-1X^4+X^2+1");
        Polinom rezultat = new Polinom();
        rezultat=OperatiiPoli.scadeP(A,B);
        assertEquals("+1.0X^4+1.0X^3-6.0X^2-3.0",rezultat.toStringPol());
        System.out.println("Scadere corecta!");

    }

    @Test
    public void inmultesteP() {
        Polinom A = new Polinom ("X^3+2X-5");
        Polinom B = new Polinom ("-3X^4-1X^3+1");
        Polinom rezultat = new Polinom();
        rezultat=OperatiiPoli.inmultesteP(A,B);
        assertEquals("-3.0X^7-1.0X^6-6.0X^5+13.0X^4+6.0X^3+2.0X-5.0",rezultat.toStringPol());
        System.out.println("Inmultire corecta!");
    }

    @Test
    public void deriveazaP() {
        Polinom A = new Polinom ("-1X^3+2X^2-1X-5");
        Polinom rezultat = new Polinom();
        rezultat=OperatiiPoli.deriveazaP(A);
        assertEquals("-3.0X^2+4.0X-1.0",rezultat.toStringPol());
        System.out.println("Derivare corecta!");
    }

    @Test
    public void integreazaP() {
        Polinom A = new Polinom ("-5X^3+X^4-5X+1");
        Polinom rezultat = new Polinom();
        rezultat=OperatiiPoli.integreazaP(A);
        assertEquals("+0.2X^5-1.25X^4-2.5X^2+X",rezultat.toStringPol());
        System.out.println("Integrare corecta!");
    }

    @Test
    public void imparteP() {
        Polinom A = new Polinom ("-15X^5+3X^3-2X+2");
        Polinom B = new Polinom ("-3X^3+1");
        Polinom cat = new Polinom();
        Polinom rest = new Polinom ();
        OperatiiPoli.imparteP(A,B,cat,rest);
        assertEquals("+5.0X^2-1.0",cat.toStringPol());
        assertEquals("-5.0X^2-2.0X+3.0",rest.toStringPol());
        System.out.println("Impartire corecta!");
    }



}