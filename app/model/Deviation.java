package model;

/**
 * Created by Gukov on 28.10.2016.
 */
public class Deviation {
    protected int id;
    protected String decription;

    protected String type;


    public Deviation(int id,String decription,String type ) {
        this.decription = decription;
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
