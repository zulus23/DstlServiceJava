package model;

import javax.persistence.*;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "gtk_NormaTimeLoading")
public class NormaTimeLoading {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;
    /*Пакет*/
    private int packageTime;
    /*Коммисионная отгрузка*/
    private int commissionTime;
    /*Россыпь*/
    private int placerTime;

    public NormaTimeLoading(Integer id, Enterprise enterprise, int packageTime, int commissionTime, int placerTime) {
        this.id = id;
        this.enterprise = enterprise;
        this.packageTime = packageTime;
        this.commissionTime = commissionTime;
        this.placerTime = placerTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public int getPackageTime() {
        return packageTime;
    }

    public void setPackageTime(int packageTime) {
        this.packageTime = packageTime;
    }

    public int getCommissionTime() {
        return commissionTime;
    }

    public void setCommissionTime(int commissionTime) {
        this.commissionTime = commissionTime;
    }

    public int getPlacerTime() {
        return placerTime;
    }

    public void setPlacerTime(int placerTime) {
        this.placerTime = placerTime;
    }


}
