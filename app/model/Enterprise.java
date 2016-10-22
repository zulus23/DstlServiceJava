package model;

import com.avaje.ebean.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Gukov on 05.08.2016.
 */
@Entity
@Table(name = "gtk_Enterprise")
public class Enterprise extends Model{
    @Id
    private int id;
    private String name;

    @Column(name = "slDb")
    private String nameInDb;

    public Enterprise(int id, String name, String nameInDb) {
        this.id = id;
        this.name = name;

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
