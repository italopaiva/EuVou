package model;

import java.text.DecimalFormat;
//confirmar restrições de tamanho
import exception.EventException;

public class Event {
    public static final String EVENT_NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos informar o nome do evento.";
    public static final String NAME_CANT_BE_GREATER_THAN_50 = "Hey, você ultrapassou o número de caracteres permitido para o nome do evento, tente novamente.";
    public static  final String DESCRIPTION_CANT_BE_EMPTY = "Hey, acho que você esqueu de informar a descrição do evento.";
    public static final String DESCRIPTION_CANT_BE_GREATER_THAN = "Hey, o máximo de caractéres para descrever um evento é de 500 caracteres";
    public static final String LATITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a latitude deve ser maior que -90 e menor que 90!";
    public static final String LONGITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a longitude deve ser maior que -180 e menor que 180";
    public static final String LONGITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";
    public static final String LANTITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";

    private int idEvent;
    private String nameEvent;
    private String description;
    private Double latitude;
    private Double longitude;

    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 500;

    public Event(int idEvent,String nameEvent,String description,Double latitude, Double longitude) throws EventException {
        setIdEvent(idEvent);
        setNameEvent(nameEvent);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Event(String nameEvent,String description,Double latitude, Double longitude) throws EventException {
        setNameEvent(nameEvent);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }


    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) throws EventException{
        if(!(longitude.toString().isEmpty()))
        {
            if(longitude > -180 && longitude < 180) {
                this.longitude = longitude;

            }else
            {
                throw  new EventException(LONGITUDE_IS_INVALID);
            }
        }else
        {
            throw new EventException(LONGITUDE_IS_EMPTY);
        }

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) throws EventException{

        if(latitude >= -90 && latitude <= 90)
        {
            this.latitude = latitude;
        }else
        {
            throw  new EventException(LATITUDE_IS_INVALID);
        }
    }
}
