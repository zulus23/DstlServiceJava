package model;

/**
 * Created by Gukov on 28.10.2016.
 */
public class DriverTransportCompany {

    private String id;
    private TransportCompany transportCompany;
    private String fullName;
    private String phone;
    private Enterprise serviceDstl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransportCompany getTransportCompany() {
        return transportCompany;
    }

    public void setTransportCompany(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
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

    public Enterprise getServiceDstl() {
        return serviceDstl;
    }

    public void setServiceDstl(Enterprise serviceDstl) {
        this.serviceDstl = serviceDstl;
    }
}
