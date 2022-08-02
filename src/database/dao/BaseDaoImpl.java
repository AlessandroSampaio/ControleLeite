package database.dao;

import database.DAO;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author AlessandroSampaio
 */
abstract class BaseDaoImpl implements DAO {
    
    private final DataSource ds;

    public BaseDaoImpl(DataSource ds) {
        this.ds = ds;
    }                
    
    public Connection getNewConnection() throws SQLException{
        return ds.getConnection();
    }
}
