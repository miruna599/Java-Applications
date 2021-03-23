import java.time.LocalDateTime;
import java.util.Date;

public class MonitoredData {
    LocalDateTime start_time;
    LocalDateTime end_time;
    String activity_label;

    public MonitoredData(LocalDateTime start, LocalDateTime end, String activity)
    {
        this.start_time=start;
        this.end_time=end;
        this.activity_label=activity;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public void setActivity_label(String activity_label) {
        this.activity_label = activity_label;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public String getActivity_label() {
        return activity_label;
    }
    public int getDistinctDay(int month, int day)
    {
        return month*31+day;
    }

    public String toString()
    {
        return "Start time:"+this.getStart_time()+",   End time:"+this.getEnd_time()+"   "+"Activity: "+this.getActivity_label();
    }
}
