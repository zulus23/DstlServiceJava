package utils;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import org.avaje.datasource.DataSourceConfig;

import java.util.Optional;

/**
 * Created by Gukov on 28.10.2016.
 */
public class DbUtils {
    public static Optional<EbeanServer> connectToSLGotek(){
        Optional<EbeanServer>  _server = null;
        try{
                 ServerConfig config = new ServerConfig();
                config.setName("slgotek");
                config.loadFromProperties();
                // load test-ebean.properties if present for running tests
                // typically using H2 in memory database
                config.loadTestProperties();

                // set as default and register so that Model can be
                // used if desired for save() and update() etc
                config.setDefaultServer(false);
                config.setRegister(true);
                DataSourceConfig ds = new DataSourceConfig();
                ds.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                ds.setUrl("jdbc:sqlserver://SRV-SLDB:1433;databaseName=SL_GOTEK");
                ds.setUsername("report");
                ds.setPassword("report");
                config.setDataSourceConfig(ds);


             _server = Optional.ofNullable(EbeanServerFactory.create(config));


        }catch (Exception ex) {
           ex.printStackTrace();
        }
        return  _server;
    }

}
