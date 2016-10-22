package model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "gtk_NormaTimeLoading")
public class NormaTimeLoading {
    @ManyToOne
    @JoinColumn(name = "idEnterprise")
    private Enterprise enterprise;
    /*Пакет*/
    private int packageTime;
    /*Коммисионная отгрузка*/
    private int commissionTime;
    /*Россыпь*/
    private int placerTime;

    public NormaTimeLoading(Enterprise enterprise, int packageTime, int commissionTime, int placerTime) {
        this.enterprise = enterprise;
        this.packageTime = packageTime;
        this.commissionTime = commissionTime;
        this.placerTime = placerTime;
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
