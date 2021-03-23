package DataLayer;
import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;
import PresentationLayer.*;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.ArrayList;

public class RestaurantSerializator{

        public static void deSerialization(Restaurant unRDeserializat, String file) {
            try {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                unRDeserializat.setMenu((ArrayList<MenuItem>) in.readObject());
                in.close();
                fileIn.close();
            } catch (IOException i) {
                i.printStackTrace();
                return;
            } catch (ClassNotFoundException c) {
                System.out.println("Restaurant class not found");
                c.printStackTrace();
                return;
            }
        }



}