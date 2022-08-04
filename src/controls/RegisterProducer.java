package controls;

import database.ConnectionParameters;
import database.DAO;
import database.dao.ProducerDAO;
import database.factory.FirebirdConnectionFactoryImpl;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Model;
import model.Producer;
import model.ProducerTableMapper;

/**
 *
 * @author AlessandroSampaio
 */
public class RegisterProducer extends Tab {

    private final DAO dao;

    private final TableView<ProducerTableMapper> tableView;

    public RegisterProducer() {
        super("Cadastro de Produtor");

        FirebirdConnectionFactoryImpl factory = new FirebirdConnectionFactoryImpl(ConnectionParameters.Default());
        DAO temp;
        try {
            temp = new ProducerDAO(factory.getDataSource());
        } catch (SQLException ex) {
            temp = null;
            Logger.getLogger(RegisterProducer.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dao = temp;
        this.tableView = getProducerTableView();
        this.setContent(getContentBox());
        loadData();
    }

    private AnchorPane getContentBox() {
        VBox contentBox = new VBox(20.0);
        contentBox.setPadding(new Insets(30.0));
        contentBox.getChildren().addAll(new Label("Cadastro de Produtor"), getProducerRegisterHBox(), tableView);
        
        AnchorPane.setTopAnchor(contentBox, 0.0);
        AnchorPane.setRightAnchor(contentBox, 0.0);
        AnchorPane.setBottomAnchor(contentBox, 0.0);
        AnchorPane.setLeftAnchor(contentBox, 0.0);
        
        AnchorPane pane = new AnchorPane(contentBox);
        return pane;
    }

    private HBox getProducerRegisterHBox() {
        TextField producerName = new TextField();
        Button btAddProducerregister = new Button("Adicionar");
        btAddProducerregister.setOnAction(e -> {
            String name = producerName.getText();
            if (!name.isEmpty()) {
                registerProducer(producerName.getText());
                producerName.clear();
                producerName.requestFocus();
            }
        });

        VBox nameBox = new VBox(5, new Label("Produtor"), producerName);

        HBox.setHgrow(nameBox, Priority.ALWAYS);

        HBox box = new HBox(20.0);
        box.setAlignment(Pos.BOTTOM_LEFT);
        box.getChildren().addAll(nameBox, btAddProducerregister);
        return box;
    }

    private TableView<ProducerTableMapper> getProducerTableView() {
        TableView<ProducerTableMapper> producerView = new TableView<>();
        VBox.setVgrow(producerView, Priority.ALWAYS);

        TableColumn<ProducerTableMapper, String> idColumn = new TableColumn<>("Cod.");
        idColumn.setCellValueFactory(param -> param.getValue().getId());        

        TableColumn<ProducerTableMapper, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setCellValueFactory(param -> param.getValue().getName());
        nameColumn.setPrefWidth(400f);

        TableColumn<ProducerTableMapper, String> registerDateColumn = new TableColumn<>("Cadastro");
        registerDateColumn.setCellValueFactory(param -> param.getValue().getRegisterDate());

        TableColumn<ProducerTableMapper, String> lastCollectColumn = new TableColumn<>("Ult. Coleta");
        lastCollectColumn.setCellValueFactory(param -> param.getValue().getLastCollect());

        TableColumn<ProducerTableMapper, Boolean> activeColumn = new TableColumn<>("Ativo");
        activeColumn.setCellValueFactory(param -> param.getValue().getActive());
        activeColumn.setCellFactory(tc -> new CheckBoxTableCell<>());

        producerView.getColumns().addAll(idColumn, nameColumn, registerDateColumn, lastCollectColumn, activeColumn);
        
        ContextMenu context = new ContextMenu();
        MenuItem changeStatus = new MenuItem("Ativar/Inativar");
        changeStatus.setOnAction(e -> {
            Producer producer = tableView.getSelectionModel().getSelectedItem().getProducer();
            producer.changeStatus();
            if(dao.update(producer)){
                tableView.getSelectionModel().getSelectedItem().getActive().set(producer.isActive());
                tableView.refresh();
            }            
        });
        
        context.getItems().add(changeStatus);
        
        producerView.setContextMenu(context);
        
        return producerView;
    }

    private void loadData() {
        final ObservableList<ProducerTableMapper> data = FXCollections.observableArrayList();
        List<Model> producers = dao.get();
        for (Model model : producers) {
            if (model instanceof Producer) {
                data.add(new ProducerTableMapper((Producer) model));
            }
        }

        tableView.setItems(data);
    }

    private void registerProducer(String producerName) {
        Producer producer = new Producer();
        producer.setName(producerName);
        producer.setRegisterDate(LocalDateTime.now());
        producer.setActive(true);

        if (dao.create(producer)) {
            tableView.getItems().add(new ProducerTableMapper(producer));
        }
    }
}
