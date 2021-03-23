import java.util.ArrayList;
import java.util.TreeSet;

public class OperatiiPoli {
    //adunare
    public static Polinom adunaP ( Polinom p1, Polinom p2)
    { Polinom adunati = new Polinom ();
        for ( Monom unM1 : p1.getPol())
       { for( Monom unM2 : p2.getPol())
           if(unM1.getExp()==unM2.getExp())
            adunati.adaugaMon(unM1.adunaM(unM2));
        }

        for ( Monom unM1 : p1.getPol())
        { int ok=0;
            for(Monom unM2 : adunati.getPol())
            { if(unM1.getExp()==unM2.getExp())
                { ok=1;
                  break;
                }
            }
            if( ok==0)
                adunati.adaugaMon(unM1);
        }

        for ( Monom unM1 : p2.getPol())
        { int ok=0;
            for(Monom unM2 : adunati.getPol())
            { if(unM1.getExp()==unM2.getExp())
                { ok=1;
                  break;
                }
            }
            if( ok==0)
                adunati.adaugaMon(unM1);

        }

        return adunati;
    }
    //scadere
    public static Polinom scadeP ( Polinom p1, Polinom p2)
    { Polinom scazuti = new Polinom ();
        for ( Monom unM1 : p1.getPol())
        { for( Monom unM2 : p2.getPol())
            if(unM1.getExp()==unM2.getExp())
            scazuti.adaugaMon(unM1.scadeM(unM2));
        }

        for ( Monom unM1 : p1.getPol())
        { int ok=0;
            for(Monom unM2 : scazuti.getPol())
            { if(unM1.getExp()==unM2.getExp())
            { ok=1;
                break;
            }
            }
            if( ok==0)
                scazuti.adaugaMon(unM1);
        }

        for ( Monom unM1 : p2.getPol())
        { int ok=0;
            for(Monom unM2 : scazuti.getPol())
            { if(unM1.getExp()==unM2.getExp())
            { ok=1;
                break;
            }
            }
            if( ok==0) {
                Monom copie = new Monom(0.0,0);
                copie.setCoef(-unM1.getCoef());
                copie.setExp(unM1.getExp());
                scazuti.adaugaMon(copie);
            }

        }
        Polinom scazutiNew = new Polinom ();
        for(Monom unM : scazuti.getPol())
        {
            if(unM.getCoef()!=0)
                scazutiNew.adaugaMon(unM);
        }

        return scazutiNew;
    }
    //inmultire
    public static Polinom inmultesteP (Polinom p1, Polinom p2)
    {
        Polinom inmultiti = new Polinom ();
        Polinom inmultitiVer = new Polinom ();
        for ( Monom unM1 : p1.getPol())
        {
            for ( Monom unM2 : p2.getPol())
            {
                inmultiti.adaugaMon(unM1.inmultesteM(unM2));

            }
        }

        for ( Monom unM1 : inmultiti.getPol())
        {   int seRepeta=0;
            double sumaCoef=0;
            for ( Monom unM2 : inmultiti.getPol())
            {
                if(unM1.getExp()==unM2.getExp())
                {
                    seRepeta++;
                    if(seRepeta==1)
                    sumaCoef+=unM2.getCoef();

                    if(seRepeta>1)
                        sumaCoef+=unM2.getCoef();
                }

            }
            if( seRepeta == 1)
                inmultitiVer.adaugaMon(unM1);
            if( seRepeta>1)
            {
                Monom frati = new Monom(0.0,0);
                frati.setCoef(sumaCoef);
                frati.setExp(unM1.getExp());
                inmultitiVer.adaugaMon(frati);
            }
        }

            return inmultitiVer;
    }
    //derivare
    public static Polinom deriveazaP (Polinom p1)
    {
        Polinom derivat = new Polinom ();
        for(Monom unM1 : p1.getPol())
        {
            derivat.adaugaMon(unM1.deriveazaM(unM1));

        }
        return derivat;
    }

    //integrare
    public static Polinom integreazaP (Polinom p1)
    {
        Polinom integrat = new Polinom ();
        for(Monom unM1 : p1.getPol())
        {
            integrat.adaugaMon(unM1.integreazaM(unM1));
        }

        return integrat;
    }

    //impartire
    public static void imparteP(Polinom p1, Polinom p2,Polinom cat,Polinom rest)
    {
        Polinom copie = new Polinom();
        Polinom help = new Polinom();
        for(Monom unM : p1.getPol())
        {
            copie.adaugaMon(unM);
        }


        while(copie.getAlfa().getExp() >= p2.getAlfa().getExp())
        {

            help.adaugaMon((copie.getAlfa()).imparteM(p2.getAlfa()));
            //System.out.println("1:"+help.toStringPol());
            cat.adaugaMon(help.getAlfa());
            //System.out.println("2:"+cat.toStringPol());
            copie=scadeP(copie, inmultesteP(help,p2));
            //System.out.println("3:"+copie.toStringPol());
            help.getPol().clear();

        }
          for(Monom unM : copie.getPol())
        {
            rest.adaugaMon(unM);
        }
    }

}
