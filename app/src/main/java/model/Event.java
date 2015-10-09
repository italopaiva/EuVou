package model;

import java.text.DecimalFormat;

public class Event {


    private int idEvent;
    private String nameEvent;
    private String description;
    private Float latitude;
    private Float longitude;

    public Event(String nameEvent,String description,Float latitude, Float longitude)
    {
        setNameEvent(nameEvent);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
