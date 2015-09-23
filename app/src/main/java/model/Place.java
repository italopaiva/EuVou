package model;

import android.util.Log;

/**
 * Created by geovanni on 09/09/15.
 */
public class Place {
    private String name;
    private String comment;
    private Integer evaluate;
    private String longitude;
    private Integer Number;
    private Integer complement;
    private String type;
    private String patio; /* achei estranho, porém deixarei assim para averiguar com o grupo
                            posteriormente*/
    private String zipCode;


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        try
        {
            if(zipCode.length()>0)
                this.zipCode = zipCode;
        }catch(NullPointerException exception)
        {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try
        {
            if(name.length()>0)
                this.name = name;
        }catch(NullPointerException exception)
        {

        }

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        try
        {
            this.comment = comment;
        }catch(NullPointerException exception)
        {

        }

    }

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        try
        {
            if(evaluate < 0 || evaluate >5)
                System.out.println( "Opa! a avaliação deve ser de 0 a 5");
            else
                this.evaluate = evaluate;
        }catch(NumberFormatException exception)
        {

        }

    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        try
        {
            this.longitude = longitude;
        }catch(NullPointerException exception)
        {

        }

    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        try
        {
            Number = number;

        }catch(NullPointerException exception)
        {

        }catch(NumberFormatException except)
        {

        }


    }

    public Integer getComplement() {
        return complement;
    }

    public void setComplement(Integer complement) {
        try
        {
            this.complement = complement;

        }catch(NullPointerException exception)
        {

        }catch(NumberFormatException except)
        {

        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        try {
            this.type = type;

        }catch(NullPointerException exception)
        {

        }
    }

    public String getPatio() {
        return patio;
    }

    public void setPatio(String patio) {
       try {

           this.patio = patio;
       }catch(NullPointerException exception)
       {

       }
    }

}
