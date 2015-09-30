package model;

/**
 * Created by geovanni on 09/09/15.
 */
public class Place {
    private String name;
    private String[] comment;
    private Integer evaluate;
    private Double longitude;
    private Double latitude;
    private String operation;
    private String description;
    private String address;

    public Place(String name, String[] comment, Integer evaluate, Double longitude, Double latitude,
                 String operation, String description, String address) {
        this.name = name;
        this.comment = comment;
        this.evaluate = evaluate;
        this.longitude = longitude;
        this.latitude = latitude;
        this.operation = operation;
        this.description = description;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getComment() {
        return comment;
    }

    public void setComment(String[] comment) {
        this.comment = comment;
    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
