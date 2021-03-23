
import BusinessLayer.*;
import BusinessLayer.MenuItem;
import DataLayer.RestaurantSerializator;
import PresentationLayer.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClass {

    public static void main(String[] args)  {

        Restaurant unR = new Restaurant();
        RestaurantSerializator.deSerialization(unR, args[0]);
        WaiterView viewWaiter = new WaiterView();
        viewWaiter.setVisible(true);

        AdministratorView viewAdministrator = new AdministratorView();
        viewAdministrator.setVisible(true);

        WaiterController waController = new WaiterController(viewWaiter, unR);
        AdministratorController adController = new AdministratorController(viewAdministrator, unR,args[0]);

        ChefView viewChef = new ChefView(unR);
        viewChef.setVisible(true);

    }
}
