package model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "GTK_DSTL_NormaTimeLoading")
public class NormaTimeLoading  extends Model{
    @Id
    @Column(name = "ID")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "IdEnterprise")
    private Enterprise enterprise;
    /*Пакет*/
    @Column(name = "PackageTime")
    private int packageTime;
    /*Коммисионная отгрузка*/
    @Column(name = "CommissionTime")
    private int commissionTime;
    /*Россыпь*/
    @Column(name = "PlacerTime")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormaTimeLoading)) return false;

        NormaTimeLoading that = (NormaTimeLoading) o;

        if (!id.equals(that.id)) return false;
        return enterprise.equals(that.enterprise);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + enterprise.hashCode();
        return result;
    }
}
