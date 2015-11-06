package model;

/**
 * Created by igor on 06/11/15.
 */
public class Evaluation {

    private float grave;
    private int idPlace;
    private int idUser;

    public Evaluation(int idPlace, int idUser, float grave){
        setIdPlace(idPlace);
        setIdUser(idUser);
        setGrave(grave);
    }

    public void setGrave(float grave) {
        this.grave = grave;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public float getGrave() {
        return grave;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public int getIdUser() {
        return idUser;
    }
}
