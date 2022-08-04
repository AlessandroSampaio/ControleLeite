package database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Model;
import model.Producer;

/**
 *
 * @author AlessandroSampaio
 */
public class ProducerDAO extends BaseDaoImpl {

    public ProducerDAO(DataSource ds) {
        super(ds);
    }

    @Override
    public boolean update(Model model) {
        if (model instanceof Producer) {
            Producer producer = (Producer) model;

            String sql = "UPDATE PRODUTOR SET NOME = ?, ULTIMA_COLETA = ?, ATIVO = ?, CADASTRO = ? WHERE ID = ?";
            Connection con = null;
            try {
                con = getNewConnection();
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, producer.getName());
                if (producer.getLastCollect() == null) {
                    ps.setDate(2, null);
                } else {
                    ps.setDate(2, Date.valueOf(producer.getLastCollect().toLocalDate()));
                }
                ps.setBoolean(3, producer.isActive());
                ps.setDate(4, Date.valueOf(producer.getRegisterDate().toLocalDate()));
                ps.setInt(5, producer.getId());

                ps.execute();
                int affectedRows = ps.getUpdateCount();

                return affectedRows == 1;
            } catch (SQLException ex) {
                Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean create(Model model) {
        if (model instanceof Producer) {
            Producer producer = (Producer) model;

            String sql = "INSERT INTO PRODUTOR (NOME, ULTIMA_COLETA, CADASTRO, ATIVO ) VALUES (?, ?, ?, ?) RETURNING ID";

            Connection con = null;
            try {
                con = getNewConnection();
                PreparedStatement ps = con.prepareStatement(sql);

                ps.setString(1, producer.getName());
                ps.setObject(2, producer.getLastCollect());
                ps.setObject(3, producer.getRegisterDate());
                ps.setBoolean(4, producer.isActive());

                ps.execute();
                ResultSet result = ps.getResultSet();

                if (result.next()) {
                    producer.setId(result.getInt("ID"));
                    return true;
                }

                return false;

            } catch (SQLException ex) {
                Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model> get() {
        List<Model> producers = new ArrayList<>();
        String sql = "SELECT * FROM PRODUTOR";

        Connection con = null;
        try {
            con = getNewConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producer producer = new Producer();
                producer.setId(rs.getInt("ID"));
                producer.setName(rs.getString("NOME"));
                if (rs.getString("ULTIMA_COLETA") != null) {
                    String lastCollect = rs.getString("ULTIMA_COLETA");
                    lastCollect = lastCollect.substring(0, 19);
                    producer.setLastCollect(LocalDateTime.parse(lastCollect, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                }
                String registerDate = rs.getString("CADASTRO");
                registerDate = registerDate.substring(0, 19);
                producer.setRegisterDate(LocalDateTime.parse(registerDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                producer.setActive(rs.getBoolean("ATIVO"));
                producers.add(producer);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProducerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producers;
    }

    @Override
    public Model get(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Model> get(String parameter, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
