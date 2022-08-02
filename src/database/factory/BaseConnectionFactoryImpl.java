package database.factory;

import database.ConnectionFactory;
import database.ConnectionParameters;

/**
 *
 * @author AlessandroSampaio
 */
public abstract class BaseConnectionFactoryImpl implements ConnectionFactory{
    
    private final ConnectionParameters parameters;

    public BaseConnectionFactoryImpl(ConnectionParameters parameters) {
        this.parameters = parameters;
    }      

    public ConnectionParameters getParameters() {
        return parameters;
    }        
}
