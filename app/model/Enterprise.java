package model;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.Optional;

/**
 * Created by Gukov on 05.08.2016.
 */
@Entity
@Table(name = "GTK_DSTL_Enterprise")
public class Enterprise extends Model{

    public static final Finder<Integer,Enterprise> find = new Finder<Integer, Enterprise>(Enterprise.class);

    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "Name")
    private String name;

    @Column(name = "SlDB")
    private String nameInDb;
    @Column(name = "ServiceDSTL")
    private Boolean service;


    @Column(name = "BelongToService")
    private Integer belongToService;

    public Enterprise(int id, String name, String nameInDb, Boolean service) {
        this.id = id;
        this.name = name;
        this.nameInDb = nameInDb;
        this.service = service;
    }

    public Integer getBelongToService() {
        return belongToService;
    }

    public void setBelongToService(Integer belongToService) {
        this.belongToService = belongToService;
    }

    public Boolean isService() {
        return service;
    }

    public void setService(Boolean service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameInDb(String nameInDb) {
        this.nameInDb = nameInDb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enterprise)) return false;

        Enterprise that = (Enterprise) o;

        if (this.getId() != that.getId()) return false;
        return getName().equals(that.getName());

    }

    @Override
    public int hashCode() {
        int result = this.getId();
        result = 31 * result + getName().hashCode();
        return result;
    }

    public String getNameInDb() {
        return nameInDb;
    }


}
