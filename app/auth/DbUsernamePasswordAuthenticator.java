package auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import org.pac4j.core.context.Pac4jConstants;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.credentials.authenticator.Authenticator;
import org.pac4j.core.exception.CredentialsException;
import org.pac4j.core.exception.HttpAction;
import org.pac4j.core.util.CommonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Configuration;
import play.db.Database;
import play.db.Databases;
import play.libs.Json;

import javax.inject.Inject;
import java.sql.*;

/**
 * Created by Zhukov on 24.10.2016.
 */
public class DbUsernamePasswordAuthenticator implements Authenticator<UsernamePasswordEnterpriseCredentials> {


    protected static final Logger logger = LoggerFactory.getLogger(DbUsernamePasswordAuthenticator.class);

    private Configuration configuration;

    @Inject
    public DbUsernamePasswordAuthenticator(Configuration configuration) {
      super();
      this.configuration = configuration;

    }

    @Override
    public void validate(UsernamePasswordEnterpriseCredentials credentials, WebContext context) throws HttpAction {
        if (credentials == null) {
            throwsException("No credential");
        }
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String service  = credentials.getEnterprise();
        if (CommonHelper.isBlank(username)) {
            throwsException("Username cannot be blank");
        }
        if (CommonHelper.isBlank(password)) {
            throwsException("Password cannot be blank");
        }
        /*if (CommonHelper.areNotEquals(username, password)) {
            throwsException("Username : '" + username + "' does not match password");
        }*/


        if(!userExistInDb(username,password,service)){
            throwsException("Ошибка. Пользователь : '" + username + "' с таким паролем отсутствует");
        }
        final DstlProfile profile = new DstlProfile();
        profile.setId(username);
        profile.addAttribute(Pac4jConstants.USERNAME, username);
        profile.addAttribute(AuthConstants.SERVICE_DSTL,service);

        credentials.setUserProfile(profile);
    }

    protected void throwsException(final String message) {
        throw new CredentialsException(message);
    }


    private boolean userExistInDb(String user,String password,String serviceName){
        Configuration _configuration= configuration.getConfig("login");

        try(Connection conn =  DriverManager.getConnection(_configuration.getString("url"),
                _configuration.getString("username"),
                _configuration.getString("password"));
            PreparedStatement preparedStatement = conn.prepareStatement("select u.name ,u.password,e.name  from gtk_dstl.dbo.gtk_dstl_user u " +
                    " join gtk_dstl_enterprise e on u.idService = e.id where u.Name = ? AND  u.Password = ? AND e.name = ?");) {

            /*preparedStatement.executeQuery("select u.name ,u.password,e.name  from gtk_dstl.dbo.gtk_dstl_user u " +
                            " join gtk_dstl_enterprise e on u.idService = e.id where u.Name = ? AND  u.Password = ? AND e.name = ?");*/

            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,serviceName);
            ResultSet  result = preparedStatement.executeQuery();

            while ( result.next() ){
               return true;
            }

            //return  resultSet.getRow()!= 0  ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    private InnerUser getUserFromDb(String user,String password,String serviceName){
        Configuration _configuration= configuration.getConfig("login");
        InnerUser _innerUser = null;
        try(Connection conn =  DriverManager.getConnection(_configuration.getString("url"),
                _configuration.getString("username"),
                _configuration.getString("password"));
            PreparedStatement preparedStatement = conn.prepareStatement("select u.name ,u.password,e.name  from gtk_dstl.dbo.gtk_dstl_user u " +
                    " join gtk_dstl_enterprise e on u.idService = e.id where u.Name = ? AND  u.Password = ? AND e.name = ?");) {

            /*preparedStatement.executeQuery("select u.name ,u.password,e.name  from gtk_dstl.dbo.gtk_dstl_user u " +
                            " join gtk_dstl_enterprise e on u.idService = e.id where u.Name = ? AND  u.Password = ? AND e.name = ?");*/

            preparedStatement.setString(1,user);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,serviceName);
            ResultSet  result = preparedStatement.executeQuery();

            while ( result.next() ){
                _innerUser = new InnerUser(result.getString(1),true);


            }

            //return  resultSet.getRow()!= 0  ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return _innerUser;
    }

    private class  InnerUser {
        private String name;
        private Boolean dispatcher;

        public InnerUser(String name, Boolean dispatcher) {
            this.name = name;
            this.dispatcher = dispatcher;
        }

        public String getName() {
            return name;
        }

        public Boolean getDispatcher() {
            return dispatcher;
        }
    }
}
