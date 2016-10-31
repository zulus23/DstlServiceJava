package model;

import com.avaje.ebean.Model;

import java.util.List;

/**
 * Created by Gukov on 31.10.2016.
 */
public class ServiceDstl extends Model {

    private Integer id;
    private String nameService;

    private List<Enterprise> enterpriseList;




}
