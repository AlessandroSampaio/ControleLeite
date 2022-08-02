package database;

/**
 *
 * @author AlessandroSampaio
 */
public class ConnectionParameters {

    public static ConnectionParameters Default() {
        ConnectionParameters parameters = new ConnectionParameters();
        parameters.setConnectionTimeout(1000);
        parameters.setDatabase("localhost:C:\\ControleLeite\\cleite.fdb");
        parameters.setUserName("SYSDBA");
        parameters.setPassword("masterkey");
        parameters.setSoTimeout(10000);
        parameters.setAutoCommit(false);
        parameters.setEncoding("ISO8859_1");
        return parameters;
    }

    private String charSet;
    private int connectionTimeout;
    private String database;
    private String encoding;
    private int loginTimeout;
    private String password;
    private int soTimeout;
    private String userName;
    private boolean autoCommit;

    public ConnectionParameters() {
        connectionTimeout = 0;
        loginTimeout = 0;
        soTimeout = 0;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public int getLoginTimeout() {
        return loginTimeout;
    }

    public void setLoginTimeout(int loginTimeout) {
        this.loginTimeout = loginTimeout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }
}
