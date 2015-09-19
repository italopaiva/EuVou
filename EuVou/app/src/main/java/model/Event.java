package model;

/**
 * Created by geovanni on 09/09/15.
 */
public class Event {

    private Integer idEvent;
    private String description;
    private String date;
    private String hour;
    private Integer minimumAge;
    private Integer evaluation;

    public Event(Integer idEvent, String description, String date,String hour,Integer minimumAge, Integer evaluation)
    {
        setIdEvent(idEvent);
        setDescription(description);
        setDate(date);
        setHour(hour);
        setMinimumAge(minimumAge);
        setEvaluation(evaluation);

    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {

        try
        {
            this.idEvent = idEvent;

        }catch(NullPointerException exception)
        {

        }
        catch(NumberFormatException exceptionNumber)
        {

        }

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        try{
            if(description.length() > 0)
                this.description = description;
        }catch(NullPointerException exception)
        {

        }

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        try {
            if (date.length() > 0)
                this.date = date;

        } catch(NullPointerException exception)
        {

        }
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        try
        {
            if(hour.length()>0)
                this.hour = hour;
        }catch(NullPointerException exception)
        {

        }

    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        try{
            this.minimumAge = minimumAge;

        }catch(NumberFormatException exception)
        {

        }

    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        try
        {
            this.evaluation = evaluation;

        }catch(NumberFormatException exception)
        {

        }

    }
}
