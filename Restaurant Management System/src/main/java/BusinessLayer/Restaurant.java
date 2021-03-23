package BusinessLayer;
import DataLayer.FileWriter;
import DataLayer.RestaurantSerializator;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;


import java.util.*;

public class Restaurant implements IRestaurantProcessing, Serializable{

    private  PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private ArrayList<MenuItem> menu = new ArrayList<MenuItem>() ;
    private  HashMap<Order,ArrayList<MenuItem>> orders;
    private  int pathFile = 1;
    private  String notificare ="0";

    public Restaurant()
    {
        menu = new ArrayList<MenuItem>();
        orders = new  HashMap<Order,ArrayList<MenuItem>>();

    }
    protected boolean isWellFormed()
    {
        for(Map.Entry<Order, ArrayList<MenuItem>> entry : this.getMap().entrySet())
        {
            if(entry.getValue() == null)
                return false;
        }

        if( menu == null )
            return false;
        return true;
    }

    public void setMenu(ArrayList<MenuItem> meniu)
    {
        this.menu = meniu;
    }

   //adauga un nou produs, 1-Base, 2-Composite

    public boolean isOkNumberAndProd(String alegere, String nameProd)
    {
        if( alegere.contentEquals("1") )
        {
            float pret = 0;
            String numeProdus;
            int ok = 0;
            String[] prodAndPrice = nameProd.split(" ");

            if(!prodAndPrice[0].matches("[a-zA-Z]+"))
                return false;

            for( int i=0; i<prodAndPrice[1].length();i++)
            {
                char c = prodAndPrice[1].charAt(i);
                if( (c >= '0' && c<='9') || (c=='.' && i!=0) )
                {
                    ok=1;
                }
                else
                {
                    ok=0;
                    break;
                }
            }
            if( ok==0 )
                return false;
            else
                return true;
        }
        else if ( alegere.contentEquals("2"))
        {
            String[] prodAndPriceC = nameProd.split(" ");
            String numeProdusC;
            numeProdusC = prodAndPriceC[0];

            if(!prodAndPriceC[0].matches("[a-zA-Z]+"))
                return false;

            for(int i=1; i<prodAndPriceC.length; i++)
            {
                if(!prodAndPriceC[i].matches("[a-zA-Z]+"))
                    return false;
            }
            return true;
        }

        return false;
    }


    public void addNewMenuItemAD(String alegere, String nameProduct)
    {
        assert isOkNumberAndProd(alegere, nameProduct);
        assert isWellFormed();
        int sizePre = menu.size();
        int al = Integer.parseInt(alegere);
        if( al==1 )
        {
            float pret = 0;
            String numeProdus;
            String[] prodAndPrice = nameProduct.split(" ");
            numeProdus = prodAndPrice[0];
            pret = Float.parseFloat(prodAndPrice[1]);
            MenuItem noulProdus = new BaseProduct(numeProdus);
            noulProdus.computePrice(pret);
            menu.add(noulProdus);
        }

        else if ( al==2 )
        {
            String[] prodAndPriceC = nameProduct.split(" ");
            String numeProdusC;
            numeProdusC = prodAndPriceC[0];
            MenuItem noulProdusC = new CompositeProduct(numeProdusC);
            int ok=0;
            int exista;
            ArrayList<MenuItem> listaComp = new ArrayList<MenuItem>();
            for(int i=1; i<prodAndPriceC.length; i++)
            {
                exista = 0;
                for(MenuItem product: menu)
                {
                    if(prodAndPriceC[i].equals(product.getNumeProdus()))
                    {
                        exista = 1;
                        listaComp.add(product);
                    }
                }
                if(exista==0)
                {
                    ok=1;
                    break;
                }
            }
            if(ok!=1)
            {
                ((CompositeProduct) noulProdusC).setBaseAndComposite(listaComp);
                float pretTotal = 0;
                noulProdusC.computePrice(pretTotal);
                menu.add(noulProdusC);
            }
        }
        int sizePost = menu.size();
        assert sizePost == sizePre + 1;
        assert isWellFormed();
    }

    public boolean isInMenuAndMod(String numeProdMod)
    {
        String[] cond = numeProdMod.split(" ");
        int verificari = 0;
        for(MenuItem product: menu) {
            if (product.getNumeProdus().contentEquals(cond[0]))
                verificari++;
        }
        if( ( cond[1].contentEquals("X") == false && Float.valueOf(cond[2])==0 ) ||
            ( cond[1].contentEquals("X") == true && Float.valueOf(cond[2])!=0 ) ||
            (cond[1].contentEquals("X") == false && Float.valueOf(cond[2])!=0 )  )
            verificari++;

        if ( verificari == 2 )
            return true;
        else
            return false;

    }
    public boolean isNotInMenu(String preNumeProd)
    {
        for(MenuItem prod : menu)
            if(prod.getNumeProdus().equals(preNumeProd))
            {
                return false;
            }
       return true;
    }

    public boolean notThisPrice(String numeProd, float prePrice)
    {
        for(MenuItem prod : menu)
            if(prod.getNumeProdus().equals(numeProd)) {
                if (prod.getPretProdus() == prePrice) {

                    return false;
                }
            }

         return true;
    }
    public void editMenuItemAD(String produsSchimbari)
    {
        assert isInMenuAndMod(produsSchimbari);
        assert isWellFormed();

        String[] cond = produsSchimbari.split(" ");
        String numeProdus;
        String noulNume;
        float pret = 0;
        numeProdus = cond[0];
        String preNume = cond[0];
        noulNume = cond[1];
        pret = Float.parseFloat(cond[2]);
        float prePrice = 0;
        int alegere = 0;
        if(noulNume.contentEquals("X") == false && pret==0)
            alegere = 1;
        if(noulNume.contentEquals("X") == true && pret!=0)
            alegere = 2;
        if(noulNume.contentEquals("X") == false && pret!=0)
            alegere = 3;

        for(MenuItem product: menu)
        {
            if(product.getNumeProdus().contentEquals(numeProdus))
            {
                prePrice = product.getPretProdus();
                if (alegere == 1 ) product.setNumeProdus(noulNume);


                if( alegere == 2 )
                {

                    product.computePrice(pret);
                }

                if( alegere == 3 )
                {

                    product.setNumeProdus(noulNume);
                    product.computePrice(pret);
                }
                break;
            }

        }

        for(MenuItem product: menu)
        {
            if(product instanceof CompositeProduct)
            {
                float pretTotal = 0;
                product.computePrice(pretTotal);
            }
        }

        assert  isNotInMenu(preNume) || notThisPrice(preNume, prePrice);
        assert isWellFormed();

    }

    public boolean isInMenu(String numeProd)
    {
        for(MenuItem prod : menu)
            if(prod.getNumeProdus().equals(numeProd))
                return true;

        return false;
    }
    public void deleteMenuItemAD(String numeProdCautat)
    {
        assert isInMenu(numeProdCautat);
        assert isWellFormed();
        int sizePre = menu.size();
        MenuItem unProd = findMenuItem(numeProdCautat);
        Iterator<MenuItem> cauta = menu.iterator();
        ArrayList<String> deSters = new ArrayList<String>();
        while(cauta.hasNext())
        {
            MenuItem produsDinMeniu = cauta.next();
            if( produsDinMeniu instanceof CompositeProduct)
            {
                CompositeProduct isComposite = (CompositeProduct) produsDinMeniu;
                if(isComposite.getBaseAndComposite().contains(unProd))
                    deSters.add(isComposite.getNumeProdus());
            }
        }
        for(String prodi : deSters)
            deleteMenuItemAD(prodi);

        menu.remove(unProd);
        int sizePost = menu.size();
        assert sizePost < sizePre;
        assert isWellFormed();
    }
    public MenuItem findMenuItem(String numeProdCautat)
    {
       MenuItem unBase = new BaseProduct();
       MenuItem unComp = new CompositeProduct();
       int alegere = 0;
        for(MenuItem product: menu)
        {
            if(product.getNumeProdus().contentEquals(numeProdCautat))
            {
                if( product instanceof BaseProduct)
                {
                    unBase = product;
                    alegere = 1;
                }

                if( product instanceof CompositeProduct)
                {
                    unComp = product;
                    alegere = 2;
                }

            }
        }

        if(alegere == 1)
            return unBase;
       else
            return unComp;

    }



    public boolean isOkOrder(String Order)
    {
        int orderId;
        String date;
        int table;
        String[] orderValues = Order.split(" ");

        if(!orderValues[0].matches("[0-9]+"))
            return false;

        if(!orderValues[2].matches("[0-9]+"))
            return false;

        return true;

    }

    public boolean isOkProducts(String Products)
    {
        String[] prodcs = Products.split(" ");
        String numeProdusC;
        numeProdusC = prodcs[0];

        if(!prodcs[0].matches("[a-zA-Z]+"))
            return false;

        for(int i=1; i<prodcs.length; i++)
        {
            if(!prodcs[i].matches("[a-zA-Z]+"))
                return false;
        }
        return true;
    }
    //adauga un nou order in casuta de text
    public void addNewOrderWA(String Order, String Products)
    {

        assert isOkOrder(Order) && isOkProducts(Products) && !menu.isEmpty();
        assert isWellFormed();
        int sizePre = orders.size();
        int orderId;
        String date;
        int table;
        int ok = 0;
        String[] orderValues = Order.split(" ");
        orderId = Integer.parseInt(orderValues[0]);
        date = orderValues[1];
        table = Integer.parseInt(orderValues[2]);
        Order order = new Order(orderId, date, table);

        ArrayList<MenuItem> orderedProducts = new ArrayList<MenuItem>();
        String[] productsValues = Products.split(" ");
        for(int i=0; i<productsValues.length; i++)
        {
            for(MenuItem product: menu)
            {
                if(productsValues[i].equals(product.getNumeProdus()))
                {
                    ok = 1;
                    if(product instanceof CompositeProduct)
                    {
                        notificare ="1";
                        //changes.firePropertyChange("update", notificare,"1");
                        changes.firePropertyChange(notificare, "0","1");
                        notificare ="0";
                    }
                    orderedProducts.add(product);
                }
            }
        }
        if( ok == 1)
        orders.put(order, orderedProducts);

        int sizePost = orders.size();

        assert ( (sizePost == sizePre+1) && ok==1 ) || ( (sizePost == sizePre) && ok==0 );
        assert isWellFormed();

    }

    public void addPropertyChangeListener(PropertyChangeListener l)
    {

        changes.addPropertyChangeListener(l);
    }

    //calculeaza pret pentru order-ul adaugat in casuta de text
    public float computePriceForOrderWA(String Products)
    {
        assert isOkProducts(Products) && !menu.isEmpty();
        assert isWellFormed();
        ArrayList<MenuItem> orderedProductsPrice = new ArrayList<MenuItem>();
        String[] productsValues = Products.split(" ");
        for(int i=0; i<productsValues.length; i++)
        {
            for(MenuItem product: menu)
            {
                if(productsValues[i].equals(product.getNumeProdus()))
                    orderedProductsPrice.add(product);

            }
        }
        float price = 0;
        for(MenuItem orderedItem : orderedProductsPrice)
                price+=orderedItem.getPretProdus();


        assert price>0 || price==0;
        assert isWellFormed();

        return price;
    }
    public void generateBillWA(String Products)  {
        assert isOkProducts(Products) && !menu.isEmpty();
        assert isWellFormed();
        ArrayList<MenuItem> orderedProducts = new ArrayList<MenuItem>();
        String[] productsValues = Products.split(" ");
        for(int i=0; i<productsValues.length; i++)
        {
            for(MenuItem product: menu)
            {
                if(productsValues[i].equals(product.getNumeProdus()))
                {
                    orderedProducts.add(product);
                }
            }
        }
        float price = 0;
        for(MenuItem orderedItem : orderedProducts)
        {
            price+=orderedItem.getPretProdus();
        }
        String path;
        String numberBill = String.valueOf(this.pathFile);
        File file;
        if(price!=0) {
            this.pathFile++;
             file = new File("Bill" + numberBill + ".txt");
            PrintWriter toWrite = null;
            try {
                toWrite = new PrintWriter(file, "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            toWrite.print("Pretul comenzii: " + price);
            toWrite.close();
        }
        assert price == computePriceForOrderWA(Products);
        assert isWellFormed();

    }

    public ArrayList<MenuItem> getMenu()
    {
        return menu;
    }
    public HashMap<Order,ArrayList<MenuItem>> getMap(){ return orders;}

}
