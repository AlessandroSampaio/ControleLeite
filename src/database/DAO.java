package database;

import java.util.List;
import model.Model;

/**
 *
 * @author AlessandroSampaio
 */
public interface DAO {    
    
    boolean update(Model model);
    
    boolean create(Model model);
    
    boolean delete(Model model);
    
    List<Model> get();
    
    Model get(Object id);
    
    List<Model> get(String parameter, Object value);            
}
