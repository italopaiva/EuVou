package model;

import java.text.DecimalFormat;

import exception.EventException;

public class Event {
    public static final String EVENT_NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos informar o nome do evento.";
    public static final String NAME_CANT_BE_GREATER_THAN_50 = "Hey, acho que você ultrapassou o número de caracteres permitido para o nome do evento, tente novamente.";

    private int idEvent;
    private String nameEvent;
    private String description;
    private Float latitude;
    private Float longitude;

    private static final int MAX_LENGTH_NAME = 50;

    public Event(String nameEvent,String description,Float latitude, Float longitude) throws EventException {
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

    public String getNameEvent(){
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) throws EventException {
        if(!nameEvent.isEmpty() && nameEvent!= null) {

            if(nameEvent.length() < MAX_LENGTH_NAME)
            {
                this.nameEvent = nameEvent;
            }else
            {
                throw new EventException(NAME_CANT_BE_GREATER_THAN_50);
            }
        }else{
            throw new EventException(EVENT_NAME_CANT_BE_EMPTY_NAME);
        }
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
