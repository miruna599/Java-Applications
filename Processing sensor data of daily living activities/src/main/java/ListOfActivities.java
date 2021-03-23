import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class ListOfActivities {

    ArrayList<MonitoredData> act;

    public ListOfActivities()
    {
        act=new ArrayList<MonitoredData>();
    }

    public void addActivity(MonitoredData activity)
    {
        act.add(activity);
    }

    public ArrayList<MonitoredData> getActivities()
    {
        return act;
    }

    public int numberDistinctDays()
    {
        Set<Integer> list = new  TreeSet<Integer>();
        act.forEach(item ->{
            list.add(item.getDistinctDay(item.getStart_time().getMonthValue(), item.getStart_time().getDayOfMonth()));
        });
        return list.size();
    }

    public Map<String, Long> appearenceActivity()
    {
       Map<String, Long> eachActivity = new HashMap<String, Long>();
       eachActivity = act.stream().collect(Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting()));

       return eachActivity;
    }

    public Map<Integer,Map<String, Long>>  countForEachDay()
    {
       Map<Integer,Map<String, Long>> activityDay = new HashMap<Integer,Map<String, Long>>();
      activityDay = act.stream().collect(Collectors.groupingBy(item ->
              item.getStart_time().getDayOfMonth(),
            Collectors.groupingBy(MonitoredData::getActivity_label, Collectors.counting())));


       return activityDay;
    }

    public HashMap<String, Duration> totalTimeActivity()
    {
        HashMap<String, Duration> totalTime = new HashMap<String, Duration>();
        AtomicReference<ArrayList<String>> diffActivity = new AtomicReference<ArrayList<String>>();
        ArrayList<String> array = new ArrayList<String>();
        diffActivity.set(array);

        act.forEach(activ -> {
            if(!diffActivity.get().contains(activ.getActivity_label()))
            {
                array.add(activ.getActivity_label());
                diffActivity.set(array);
                Duration dur = Duration.between(activ.getStart_time(), activ.getEnd_time());
                String aAct = activ.getActivity_label().toString();
                totalTime.put(aAct,dur.minus(dur));
                AtomicReference<Duration> durFinal = new AtomicReference<Duration>();
                durFinal.getAndSet(dur.minus(dur));
                act.forEach(verify -> {
                    if(verify.getActivity_label().equals(activ.getActivity_label()))
                    {
                        Duration another = Duration.between(verify.getStart_time(), verify.getEnd_time());
                        durFinal.getAndSet(durFinal.get().plus(another));
                        totalTime.put(aAct, durFinal.get());
                    }
                });

            }
        });

        return totalTime;

    }

    public List<String> verify90 ()
    {
        List<String> listOfNames = new ArrayList<String>();

        Map<String, Long> occurence = this.appearenceActivity();
        Duration toComp = Duration.parse("PT0H5M0S");
        for(String key : occurence.keySet())
        {
            AtomicInteger counter = new AtomicInteger();

            act.forEach(activ->{
                if(activ.getActivity_label().equals(key))
                {
                    Duration dur = Duration.between(activ.getStart_time(), activ.getEnd_time());
                    if( dur.compareTo(toComp)<0)
                    {
                        counter.getAndIncrement();
                    }
                }
            });
            float percentage = (float)((counter.get()/occurence.get(key))*100);
            if( percentage>90)
                listOfNames.add(key);
        }
        return listOfNames;
    }

}
