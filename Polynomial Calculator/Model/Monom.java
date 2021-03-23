import static java.lang.Math.abs;

import java.text.DecimalFormat;

public class Monom implements Comparable{

    private Double coef;
    private int exp;

    //private static DecimalFormat df = new DecimalFormat("####0.00");
    public Monom(Double coef, int exp)
    {
        this.coef=coef;
        this.exp=exp;
    }

    public double getCoef()
    {
        return this.coef;
    }

    public void setCoef(Double coef) { this.coef=coef;}

    public void setExp(int exp) { this.exp=exp;}

    public int getExp()
    {
        return exp;
    }


    public int compareTo ( Object unMonom)
    {
        if( this.getExp() == ( (Monom) unMonom).getExp() && this.getCoef() == ( (Monom) unMonom).getCoef())
            return 0;
        if( this.getExp() < ( (Monom) unMonom) .getExp())
            return 1;

        return -1;
    }

    public Monom adunaM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        if(this.exp==unMonom.exp) {
            aux.setCoef(this.getCoef() + unMonom.getCoef());
            aux.setExp(exp);
        }

        return aux;
    }

    public Monom scadeM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        if(this.exp==unMonom.exp) {
            aux.setCoef(this.getCoef() - unMonom.getCoef());
            aux.setExp(exp);
        }

        return aux;
    }

    public Monom inmultesteM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        aux.setCoef(this.getCoef()*unMonom.getCoef());
        aux.setExp(this.getExp()+unMonom.getExp());

        return aux;
    }
    public Monom imparteM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        aux.setCoef(this.getCoef()/unMonom.getCoef());
        aux.setExp(this.getExp()-unMonom.getExp());

        return aux;
    }

    public Monom deriveazaM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        if(unMonom.getExp()==0) {
            aux.setExp(0);
            aux.setCoef((double) 0);
        }
        else
             if(unMonom.getExp()==1)
            {
                aux.setExp(0);
                aux.setCoef(unMonom.getCoef());
            }
         else
             if(unMonom.getExp()>1)
             {
                 aux.setCoef(unMonom.getCoef()*unMonom.getExp());
                 aux.setExp(unMonom.getExp()-1);
             }

        return aux;
    }

    public Monom integreazaM ( Monom unMonom)
    {
        Monom aux = new Monom(0.0,0);
        if(unMonom.getExp()==0) {
            aux.setExp(1);
            aux.setCoef(unMonom.getCoef());
        }
        else
             if(unMonom.getExp()>=1)
             {
                 aux.setCoef(unMonom.getCoef()/(unMonom.getExp()+1));
                 aux.setExp(unMonom.getExp()+1);
             }

        return aux;


    }

    public String toString()
    {
        if ( this.getCoef()!=0 && this.getExp()==0 )
        {
            if(this.getCoef()>0)
                return "+"+Double.toString(this.getCoef());
                else
                return Double.toString(this.getCoef());
        }


        if ( this.getCoef()!=0 && this.getExp()>1 )
        {   if(this.getCoef()>0)
                return "+" + this.getCoef() + "X^" + exp;
            if(this.getCoef()<0)
                return this.getCoef()+"X^"+exp;
        }

        if( this.getCoef()!=0 && this.getExp()==1)
        {
            if( this.getCoef()==1 )
                return "+X";

            if( this.getCoef()==-1 )
                return "-X";

            if(this.getCoef()>0)
            return "+"+this.getCoef()+"X";

            if(this.getCoef()<0)
                return this.getCoef()+"X";
        }

        return "";

    }


}
