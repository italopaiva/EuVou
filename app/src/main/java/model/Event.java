package model;

import java.text.DecimalFormat;
//confirmar restrições de tamanho
import exception.EventException;

public class Event {
    public static final String EVENT_NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos informar o nome do evento.";
    public static final String NAME_CANT_BE_GREATER_THAN_50 = "Hey, você ultrapassou o número de caracteres permitido para o nome do evento, tente novamente.";
    public static  final String DESCRIPTION_CANT_BE_EMPTY = "Hey, acho que você esqueu de informar a descrição do evento.";
    public static final String DESCRIPTION_CANT_BE_GREATER_THAN = "Hey, o máximo de caractéres para descrever um evento é de 500 caracteres";


    private int idEvent;
    private String nameEvent;
    private String description;
    private Float latitude;
    private Float longitude;

    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 500;
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

    public void setDescription(String description) throws EventException{
        if(!description.isEmpty() && description !=null)
        {
            if(description.length() < MAX_LENGTH_DESCRIPTION)
            {
                this.description = description;
            }else
            {
                throw new EventException(DESCRIPTION_CANT_BE_GREATER_THAN);
            }

        }else
        {
            throw new EventException(DESCRIPTION_CANT_BE_EMPTY);
        }

    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }
}
