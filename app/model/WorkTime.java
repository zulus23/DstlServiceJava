package model;

import com.avaje.ebean.Model;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Zhukov on 22.10.2016.
 */

@Entity
@Table(name = "GTK_DSTL_WorkTime")
public class WorkTime  extends Model{
    @Id
    @Column(name = "ID")
    private Integer id;
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



}
