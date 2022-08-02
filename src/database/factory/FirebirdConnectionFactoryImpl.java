package database.factory;

import database.ConnectionParameters;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.firebirdsql.ds.FBSimpleDataSource;

/**
 *
 * @author AlessandroSampaio
 */
public class FirebirdConnectionFactoryImpl extends BaseConnectionFactoryImpl {

    public FirebirdConnectionFactoryImpl(ConnectionParameters parameters) {
        super(parameters);
    }        

    @Override
    public DataSource getDataSource() throws SQLException {
        
        if(getParameters() == null) throw new SQLException("Conexão não definida");
        
        FBSimpleDataSource dataSource = new FBSimpleDataSource();
        
        dataSource.setCharSet(getParameters().getCharSet());
        dataSource.setConnectTimeout(getParameters().getConnectionTimeout());
        dataSource.setDatabase(getParameters().getDatabase());
        dataSource.setEncoding(getParameters().getEncoding());
        dataSource.setLoginTimeout(getParameters().getLoginTimeout());
        dataSource.setPassword(getParameters().getPassword());
        dataSource.setSoTimeout(getParameters().getSoTimeout());
        dataSource.setUserName(getParameters().getUserName());
        dataSource.setUseFirebirdAutocommit(getParameters().isAutoCommit());

        return dataSource;
    }

}
