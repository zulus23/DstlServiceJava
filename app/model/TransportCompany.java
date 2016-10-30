package model;

/**
 * Created by Gukov on 28.10.2016.
 */
public class TransportCompany {

    private Integer id;
    private String code;
    private String name;
    private String address;
    private String contactPerson;
    private String phoneContactPerson;
    private Enterprise serviceDstl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Enterprise getServiceDstl() {
        return serviceDstl;
    }

    public void setServiceDstl(Enterprise serviceDstl) {
        this.serviceDstl = serviceDstl;
    }
}
