package model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.View;
import model.plan.PlanShipmentItem;

import java.util.UUID;

/**
 * Created by Gukov on 28.10.2016.
 */
@Entity
@View(name = "GTK_DSTL_TransportCompany")
public class TransportCompany extends Model {


    public static final Model.Finder<String,TransportCompany> find = new Model.Finder<String,TransportCompany>(TransportCompany.class);
    @Id
    @Column(name = "RowPointer")
    private String rowPointer;
    @Column(name = "vend_num")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contactPerson;
    @Column(name = "phone")
    private String phoneContactPerson;


    public String getRowPointer() {
        return rowPointer;
    }

    public void setRowPointer(String rowPointer) {
        this.rowPointer = rowPointer;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhoneContactPerson() {
        return phoneContactPerson;
    }

    public void setPhoneContactPerson(String phoneContactPerson) {
        this.phoneContactPerson = phoneContactPerson;
    }


}
