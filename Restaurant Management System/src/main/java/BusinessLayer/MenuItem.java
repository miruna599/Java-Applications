package BusinessLayer;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String numeProdus;
    private float pretProdus;

    public MenuItem (String numeProdus)
    {
        this.numeProdus = numeProdus;
    }

    public MenuItem()
    {

    }

    public void setPretProdus(float pretProdus) {this.pretProdus = pretProdus;}
    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }
    public String getNumeProdus() {
        return numeProdus;
    }

    public float getPretProdus() {
        return pretProdus;
    }


    public void computePrice(float pret)
    {
        this.pretProdus = pret;
    }

    public String toString()
    {
        return "Nume produs:"+this.getNumeProdus()+", pret produs:"+this.getPretProdus();
    }
}
