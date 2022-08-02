package database;

import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author AlessandroSampaio
 */
public interface ConnectionFactory {
        
    DataSource getDataSource() throws SQLException;
    
}
