package model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//confirmar restrições de tamanho
import exception.EventException;

public class Event {

    public static final String EVENT_NAME_CANT_BE_EMPTY_NAME = "Hey, acho que você está esquecendo de nos informar o nome do evento.";
    public static final String NAME_CANT_BE_GREATER_THAN_50 = "Hey, você ultrapassou o número de caracteres permitido para o nome do evento, tente novamente.";
    public static final String DESCRIPTION_CANT_BE_EMPTY = "Hey, acho que você esqueu de informar a descrição do evento.";
    public static final String DESCRIPTION_CANT_BE_GREATER_THAN = "Hey, o máximo de caractéres para descrever um evento é de 500 caracteres";
    public static final String LATITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a latitude deve ser maior que -90 e menor que 90!";
    public static final String LONGITUDE_IS_INVALID = "Hey, você inseriu um número inválido, a longitude deve ser maior que -180 e menor que 180";
    public static final String LONGITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";
    public static final String LANTITUDE_IS_EMPTY = "Hey, você deixou a longitude em branco... preenche ela aí vai!";
    public static final String INVALID_EVALUATION = "Hey, você deve avaliar um evento com notas de 1 a 5!";
    public static final String ADDRESS_IS_EMPTY = "Hey, você esqueceu de nos informar o endereço do evento!";
    public static final String INVALID_EVENT_DATE = "Hey, você informou uma data errada, pay attention guy!";
    public static final String EVENT_DATE_IS_EMPTY = "Hey, você esqueceu de informar a data do evento, cuidado!";


    private int idEvent;
    private String nameEvent;
    private String dateTimeEvent;
    private String description;
    private Double latitude;
    private Double longitude;
    private String adress;
    private Integer evaluation;


    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESCRIPTION = 500;




    public Event(int idEvent,String nameEvent,String dateTimeEvent,String adress,String description,Double latitude, Double longitude) throws EventException, ParseException {
        setIdEvent(idEvent);
        setNameEvent(nameEvent);
        setDateTimeEvent(dateTimeEvent);
        setAdress(adress);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Event(String nameEvent,String dateTimeEvent,String adress,String description,Double latitude, Double longitude) throws EventException, ParseException {
        setNameEvent(nameEvent);
        setDateTimeEvent(dateTimeEvent);
        setAdress(adress);
        setDescription(description);
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public String getDateTimeEvent() {
        return dateTimeEvent;
    }

    public void setDateTimeEvent(String dateTimeEvent) throws ParseException, EventException {
        if(!dateTimeEvent.isEmpty() && dateTimeEvent !=null)
        {
            try{
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                Date eventDate = format.parse(dateTimeEvent);

                if(eventDate.after(new Date()))
                {
                    this.dateTimeEvent = dateTimeEvent;
                }else
                {
                    throw new EventException(INVALID_EVENT_DATE);
                }
            }catch(ParseException exception)
            {
                throw new EventException(INVALID_EVENT_DATE);
            }


        }else
        {
            throw  new EventException(EVENT_DATE_IS_EMPTY);
        }

    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) throws  EventException{
        if(evaluation >1 && evaluation<5)
        {
            this.evaluation = evaluation;
        }
        else
        {
            throw new EventException(INVALID_EVALUATION);
        }

    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) throws EventException{
        if(!(adress.isEmpty()) && adress!=null)
        {
            this.adress = adress;
        }else{
            throw new EventException(ADDRESS_IS_EMPTY);
        }

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

            if(nameEvent.length() <= MAX_LENGTH_NAME)
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
        if(!(latitude.toString().isEmpty()))
        {
            if(latitude >= -90 && latitude <= 90)
            {
                this.latitude = latitude;
            }else
            {
                throw  new EventException(LATITUDE_IS_INVALID);
            }
        }else
        {
            throw  new EventException(LANTITUDE_IS_EMPTY);
        }
    }
}
