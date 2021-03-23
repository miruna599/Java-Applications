import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class MainClass {

    public static void main(String[] args) {


        ListOfActivities activities=new ListOfActivities();

        Path filePath = Paths.get("D:\\UTCN\\An II\\Sem. II\\TP\\Laborator\\Tema5\\Activities.txt");
        //TASK 1
        PrintWriter fW1 = null;
        try {
            fW1 = new PrintWriter("Task_1.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (Stream<String> stream = Files.lines( filePath ))
        {
            stream.forEach(item->{

                LocalDateTime start_time=null, end_time=null;

                String[] vector=item.split("\t");

                DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                start_time=LocalDateTime.parse(vector[0], formatter);

                end_time=LocalDateTime.parse(vector[2], formatter);

                MonitoredData monD = new MonitoredData(start_time, end_time, vector[4]);

                activities.addActivity(monD);
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for(MonitoredData act: activities.getActivities())
        {
            fW1.println(act.toString());
        }
        fW1.close();

        //TASK 2
        int numberDD = activities.numberDistinctDays();
        PrintWriter fW2 = null;
        try {
            fW2 = new PrintWriter("Task_2.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fW2.println("Number of distinct days is: "+numberDD);
        fW2.close();

        //TASK 3
        Map<String, Long> map = activities.appearenceActivity();
        PrintWriter fW3 = null;
        try {
            fW3 = new PrintWriter("Task_3.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fW3.println(map.toString());
        fW3.close();

        //TASK 4
        Map<Integer,Map<String, Long>> mapi = activities.countForEachDay();
        PrintWriter fW4 = null;
        try {
            fW4= new PrintWriter("Task_4.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(Integer key : mapi.keySet())
        {
          String message =key.toString()+":"+mapi.get(key).toString();
          fW4.println(message);
        }
        fW4.close();

        //TASK 5
        HashMap<String, Duration> mapAct = activities.totalTimeActivity();
        PrintWriter fW5 = null;
        try {
            fW5= new PrintWriter("Task_5.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String key : mapAct.keySet())
        {
            String message ="Total duration for "+key+":"+mapAct.get(key).toString();
            fW5.println(message);
        }
        fW5.close();

        //TASK 6
        List<String> list = activities.verify90();
        PrintWriter fW6 = null;
        try {
            fW6= new PrintWriter("Task_6.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fW6.println("Activities that have more than 90% of the monitoring records with duration less than 5 minutes:");
        for(String name : list)
            fW6.println(name.toString());

        fW6.close();

    }


}
