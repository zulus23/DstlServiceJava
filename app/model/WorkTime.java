package model;

import com.avaje.ebean.Model;


import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "GTK_DSTL_WorkTime")
public class WorkTime  extends Model{

    public static final  Finder<Integer,WorkTime> find = new Finder<Integer, WorkTime>(WorkTime.class);
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "IdServiceDstl")
    private Enterprise serviceDstl;
    @Column(name = "StartTime")
    @Temporal(TemporalType.TIME)
    private Time startTime;
    @Column(name = "EndTime")
    @Temporal(TemporalType.TIME)
    private Time endTime;

    @Column(name = "WorkTime")
    private Boolean workTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Enterprise getServiceDstl() {
        return serviceDstl;
    }

    public void setServiceDstl(Enterprise serviceDstl) {
        this.serviceDstl = serviceDstl;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Boolean getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Boolean workTime) {
        this.workTime = workTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkTime)) return false;

        WorkTime workTime = (WorkTime) o;

        if (!id.equals(workTime.id)) return false;
        return serviceDstl.equals(workTime.serviceDstl);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + serviceDstl.hashCode();
        return result;
    }
}
