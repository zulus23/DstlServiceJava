package model;

/**
 * Created by Zhukov on 30.10.2016.
 */
public class TransportCompanyWithDriver {

    private String id;
    private String driverFullName;
    private String driverPhone;
    private String codeCompany;
    private String nameCompany;
    private String addressCompany;
    private String contactPersonCompany;
    private String phoneContactPersonCompany;


    private String enterpriseName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverFullName() {
        return driverFullName;
    }

    public void setDriverFullName(String driverFullName) {
        this.driverFullName = driverFullName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCodeCompany() {
        return codeCompany;
    }

    public void setCodeCompany(String codeCompany) {
        this.codeCompany = codeCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public String getContactPersonCompany() {
        return contactPersonCompany;
    }

    public void setContactPersonCompany(String contactPersonCompany) {
        this.contactPersonCompany = contactPersonCompany;
    }

    public String getPhoneContactPersonCompany() {
        return phoneContactPersonCompany;
    }

    public void setPhoneContactPersonCompany(String phoneContactPersonCompany) {
        this.phoneContactPersonCompany = phoneContactPersonCompany;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }
}
