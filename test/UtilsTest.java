import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import org.avaje.datasource.DataSourceConfig;

/**
 * Created by Zhukov on 20.11.2016.
 */
public class UtilsTest {

    private static  EbeanServer getObjectLocalWork() {

        ServerConfig config = new ServerConfig();
        config.setName("default");
        config.loadFromProperties();
        // load test-ebean.properties if present for running tests
        // typically using H2 in memory database
        config.loadTestProperties();

        // set as default and register so that Model can be
        // used if desired for save() and update() etc
        config.setDefaultServer(true);
        config.setRegister(true);
        config.addPackage("model");
        /*config.addClass(WorkTime.class);
        config.addClass(Enterprise.class);*/

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://SRV-SLDB:1433;databaseName=GTK_DSTL");
        ds.setUsername("report");
        ds.setPassword("report");
        config.setDataSourceConfig(ds);



        return EbeanServerFactory.create(config);
    }

    private static   EbeanServer getObjectLocalHome() {

        ServerConfig config = new ServerConfig();
        config.setName("default");
        config.loadFromProperties();
        // load test-ebean.properties if present for running tests
        // typically using H2 in memory database
        config.loadTestProperties();

        // set as default and register so that Model can be
        // used if desired for save() and update() etc
        config.setDefaultServer(true);
        config.setRegister(true);
        config.addPackage("model");
        /*config.addClass(WorkTime.class);
        config.addClass(Enterprise.class);*/

        DataSourceConfig ds = new DataSourceConfig();
        ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://localhost\\MSSQL2014_DEV;databaseName=GTK_DSTL");
        ds.setUsername("sa");
        ds.setPassword("415631234");
        config.setDataSourceConfig(ds);



        return EbeanServerFactory.create(config);
    }


    public static EbeanServer getObjectLocal() {
        if(System.getProperty("user.name").toLowerCase().equals("gukov")){
            return  getObjectLocalWork();
        }else{
            return  getObjectLocalHome();
        }

    }


}
