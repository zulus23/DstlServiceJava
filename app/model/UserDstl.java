package model;

import com.avaje.ebean.Model;

import javax.persistence.*;

/**
 * Created by Zhukov on 23.10.2016.
 */

@Entity
@Table(name = "GTK_DSTL_User")
public class UserDstl extends Model {
    @Id
    @Column(name = "ID")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "IdService")
    private Enterprise serviceDstl;


    public Enterprise getServiceDstl() {
        return serviceDstl;
    }

    public void setServiceDstl(Enterprise serviceDstl) {
        this.serviceDstl = serviceDstl;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
