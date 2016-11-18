package model;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.View;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Gukov on 28.10.2016.
 */


@Entity
@View(name = "GTK_ALL_CAR")
public class DriverTransportCompany extends Model {

    public static final Finder<String,DriverTransportCompany> find = new Finder<String, DriverTransportCompany>(DriverTransportCompany.class);


    @Id
    @Column(name = "DriverRef")
    private String id;
    @Column(name = "TransportCompanyRef")
    private String transportCompany;
    @Column(name = "NumberTruck")
    private String numberTruck;
    @Column(name = "Driver")
    private String fullName;
    @Column(name = "DriverPhone")
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(String transportCompany) {
        this.transportCompany = transportCompany;
    }

    public String getNumberTruck() {
        return numberTruck;
    }

    public void setNumberTruck(String numberTruck) {
        this.numberTruck = numberTruck;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
