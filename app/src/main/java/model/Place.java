package model;

import java.text.ParseException;
import java.util.ArrayList;

import exception.PlaceException;
/**
 * Created by geovanni on 09/09/15.
 */
public class Place {

    private final String INVALID_NAME = "Hey, nome invalido";
    private final String INVALID_LATITUDE = "Hey, sem a latitude não é possível encontrar o lugar";
    private final String INVALID_LONGITUDE= "Hey, sem a longitude não é possível encontrar o lugar";
    private final String INVALID_COMMENT= "Hey, o comentario não pode ser vazio";

    private String name;
    private ArrayList<String> comment;
    private Float evaluate;
    private Double longitude;
    private Double latitude;
    private String operation;
    private String description;
    private String address;

    public Place(String name, String evaluate, String longitude, String latitude,
                 String operation, String description, String address) throws PlaceException, ParseException {
        setName(name);
        setEvaluate(evaluate);
        setLongitude(longitude);
        setLatitude(latitude);
        setOperation(operation);
        setDescription(description);
        getAddress(address);
        comment = new ArrayList<>();
    }

    private void getAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PlaceException {
        if(name.isEmpty()) throw new PlaceException(INVALID_NAME);
        this.name = name;
    }

    public ArrayList<String> getComment() {
        return comment;
    }

    public void addComment(String comment) throws PlaceException {
        if(comment == null)
            throw new PlaceException(INVALID_COMMENT);
        if(comment.isEmpty())
            throw new PlaceException(INVALID_COMMENT);
        this.comment.add(comment);
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setLatitude(String latitude) throws ParseException, PlaceException{
        if(latitude.isEmpty()) throw new PlaceException(INVALID_LATITUDE);
        this.latitude = Double.parseDouble(latitude);
    }

    public void setLongitude(String longitude) throws ParseException, PlaceException {
        if(longitude.isEmpty()) throw new PlaceException(INVALID_LONGITUDE);
        this.longitude = Double.parseDouble(longitude);
    }

    public void setEvaluate(String evaluate) throws ParseException{
        this.evaluate = (evaluate.equals("null")? 0 : Float.parseFloat(evaluate));
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
