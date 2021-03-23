package BusinessLayer;

import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private ArrayList<MenuItem> baseAndComposite ;

    public CompositeProduct(String numeProdus) {
        super(numeProdus);
        baseAndComposite = new ArrayList<MenuItem>();
    }
    public CompositeProduct()
    {

    }
    public ArrayList<MenuItem> getBaseAndComposite() {
        return baseAndComposite;
    }
    public void setBaseAndComposite(ArrayList<MenuItem> baseAndComposite){
        this.baseAndComposite = baseAndComposite;
    }
    @Override
    public void computePrice(float pretTotal)
    {
        float pretCalculat = 0;
        for(MenuItem unProdus : baseAndComposite)
        {
            pretCalculat = pretCalculat + unProdus.getPretProdus();
        }
        pretTotal = pretCalculat;
        super.computePrice(pretTotal);
    }

    public void adaugaProdus(MenuItem produs)
    {
        baseAndComposite.add(produs);
    }

    public void stergeProdus(MenuItem produs)
    {
        baseAndComposite.remove(produs);
    }

    @Override
    public String toString()
    {
        String mesaj;
        mesaj = "Nume produs: "+this.getNumeProdus()+", pret produs: "+this.getPretProdus()+", produs alcatuit din: ";
        for(MenuItem unProdus : baseAndComposite)
        {
                mesaj = mesaj + unProdus.getNumeProdus()+" ";

        }

        return mesaj;
    }
}
