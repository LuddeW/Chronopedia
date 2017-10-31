package Models;

/**
 * Created by Ludwig on 2017-10-24.
 */

public class Alarm {
    // Tid
    // Dag

    //Nått sätt att hantera för vilken dag som är vilken eventuellt?

    int id, day, hour, min;

    public void setId(int id){ this.id = id; }
    public int getId() { return this.id; }

//    public void setDay(int day){ this.day = day; }
    public int getDay() { return this.day; }

//    public void setHour(int hour){ this.hour = hour; }
    public int getHour() { return this.hour; }

//    public void setMin(int min){ this.min = min; }
    public int getMin() { return this.min; }

    public void setDate(int day, int hour, int min) { this.day = day; this.hour = hour; this.min = min;}
    public String getDate() { return String.valueOf(day)
            + " " + String.valueOf(hour)
            + ":" + String.valueOf(min); }

}
