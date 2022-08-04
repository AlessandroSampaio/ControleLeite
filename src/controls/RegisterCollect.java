package controls;

import database.ConnectionParameters;
import database.DAO;
import database.dao.ProducerDAO;
import database.factory.FirebirdConnectionFactoryImpl;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Model;
import model.Producer;
import util.gui.AutoCompleteComboBoxListener;

/**
 *
 * @author AlessandroSampaio
 */
public class RegisterCollect extends Tab {

    private final DAO dao;

    public RegisterCollect() {
        super("Coletas");
        this.setId("CollectRegister");
        FirebirdConnectionFactoryImpl factory = new FirebirdConnectionFactoryImpl(ConnectionParameters.Default());
        DAO temp;
        try {
            temp = new ProducerDAO(factory.getDataSource());
        } catch (SQLException ex) {
            temp = null;
            Logger.getLogger(RegisterProducer.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dao = temp;

        this.createContent();
    }

    private void createContent() {
        VBox contentBox = new VBox(20.0);
        contentBox.setFillWidth(true);
        contentBox.setPadding(new Insets(30.0));
        contentBox.getChildren().addAll(new Label("Coletas"), getProducerHBox());

        AnchorPane.setTopAnchor(contentBox, 0.0);
        AnchorPane.setRightAnchor(contentBox, 0.0);
        AnchorPane.setBottomAnchor(contentBox, 0.0);
        AnchorPane.setLeftAnchor(contentBox, 0.0);

        AnchorPane pane = new AnchorPane(contentBox);
        this.setContent(pane);
    }

    private HBox getProducerHBox() {
        ComboBox<Producer> cmb = new ComboBox<>(loadData());

        new AutoCompleteComboBoxListener(cmb);

        HBox producerHBox = new HBox(25.0, cmb);

        HBox.setHgrow(cmb, Priority.ALWAYS);

        return producerHBox;
    }

    private ObservableList<Producer> loadData() {
        final ObservableList<Producer> data = FXCollections.observableArrayList();
        List<Model> producers = dao.get();
        for (Model model : producers) {
            data.add((Producer) model);
        }
        return data;
    }
}
