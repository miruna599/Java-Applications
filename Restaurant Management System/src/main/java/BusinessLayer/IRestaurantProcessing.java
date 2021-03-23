package BusinessLayer;

public interface IRestaurantProcessing {

    /***
     *
     *
     *Adauga un produs in meniu.
     * @pre isOkNumberAndProd(String alegere, String nameProduct);
     * @post menu.size() == menu.size()@pre + 1
     */
    public void addNewMenuItemAD(String alegere, String nameProduct);


    /***
     *
     *Sterge un produs din meniu.
     * @pre isInMenu(String numeProd)
     * @post  menu.size() == menu.size()@pre - 1
     */
    public void deleteMenuItemAD(String numeProdCautat);


    /**
     *
     *Editeaza un produs din meniu.
     * @pre isInMenuAndMod(String numeProdMod)
     * @post isNotInMenu(String preNumeProd) || notThisPrice(String numeProd, float prePrice)
     */
    public void editMenuItemAD(String produsDeEditat);


    /**
     *Realizeaza o comanda.
     * @pre isOkOrder(String Order) &amp;&amp; isOkProducts(String Products) &amp;&amp; !menu.isEmpty()
     * @post ( (orders.size() == orders.size()@pre+1) &amp;&amp; ok==1 ) || ( (orders.size() == orders.size()@pre) &amp;&amp; ok==0 );
     */
    public void addNewOrderWA(String Order, String Products);

    /**
     * Realizeaza pretul unui order.
     * @pre  isOkProducts(String Products) &amp;&amp; !menu.isEmpty()
     * @post price bigger than 0 || price==0;
     */
    public float computePriceForOrderWA(String Products);

    /**
     *Realizeaza factura unui order.
     * @pre isOkProducts() &amp;&amp; !menu.isEmpty()
     * @post price == computePriceForOrderWA(String Products)
     */
    public void generateBillWA(String Products);





}
