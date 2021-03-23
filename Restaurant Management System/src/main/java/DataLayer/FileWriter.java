package DataLayer;

import BusinessLayer.MenuItem;
import BusinessLayer.Restaurant;

import java.io.*;
import java.util.ArrayList;

public class FileWriter {

    public static void serialization(Restaurant unR, String file)
    {

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(unR.getMenu());
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in restaurant.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
