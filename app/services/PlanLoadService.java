package services;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import dto.PlanShipmentForLoadPlan;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by Gukov on 25.11.2016.
 */
public class PlanLoadService {

    @Inject
    private DstlService dstlService;


    public List<PlanShipmentForLoadPlan> shipmentForLoadPlanList(String nameDstl){
        String sql = "select pi.id, p.dateplan, e.name as enterprise,pi.TypeShipment,pi.NumberOrderDispatcher,\n" +
                "pi.DateShipmentDispatcher, pi.ExistInStore,pi.DateToStore\n" +
                "from gtk_dstl_planShipment p \n" +
                "JOIN gtk_dstl_planshipmentitem pi on p.id = pi.idPlan \n" +
                "join gtk_dstl_enterprise e on pi.identerprise = e.id\n" +
                "JOIN dbo.GTK_DSTL_Enterprise dstl ON dstl.ID = p.IdService \n" +
                "WHERE pi.datedeliveryfact is NULL\n" +
                "AND dstl.Name = :nameDstl \n";


       List<PlanShipmentForLoadPlan> planShipmentForLoadPlans =  Optional.ofNullable(Ebean.createSqlQuery(sql).setParameter("nameDstl",nameDstl).findList())
                .map(e ->  e.stream().map(this::mapSqlRowToPlanShipmentForLoadPlan)).map(e -> e.collect(toList())).orElseGet(null);




        return planShipmentForLoadPlans;
    }


    private PlanShipmentForLoadPlan mapSqlRowToPlanShipmentForLoadPlan(SqlRow sqlRow){
        PlanShipmentForLoadPlan result = new PlanShipmentForLoadPlan();
        result.setId(sqlRow.getLong("id"));
        result.setDatePlan(sqlRow.getDate("dateplan"));
        result.setEnterprise(sqlRow.getString("enterprise"));
        result.setTypeShipment(sqlRow.getString("TypeShipment"));
        result.setNumberDispatcher(sqlRow.getString("NumberDispatcher"));
        result.setDateShipmentDispatcher(sqlRow.getDate("DateShipmentDispatcher"));
        result.setExistInStore(sqlRow.getBoolean("ExistInStore"));
        result.setDateToStore(sqlRow.getTimestamp("DateToStore"));

        return  result;
    }



}
