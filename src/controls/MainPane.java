package controls;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alessandro Sampaio
 */
public class MainPane extends AnchorPane {

    private final TabPane tabPane;

    public MainPane() {
        super();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        this.tabPane = new TabPane();
        setChildren();
    }

    private void setChildren() {
        VBox box = new VBox(0, getMenuBar(), getButtonBar(), tabPane);
        box.setAlignment(Pos.CENTER_LEFT);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);

        this.getChildren().add(box);
    }

    private MenuBar getMenuBar() {
        MenuBar bar = new MenuBar();

        Menu configuration = new Menu("_Configuração");

        MenuItem configurationPrinter = new MenuItem("_Impressora");
        configuration.getItems().add(configurationPrinter);
        configurationPrinter.setOnAction(e -> {
            addTab(new PrinterConfiguration());
        });

        Menu provider = new Menu("_Produtores");
        MenuItem providerRegister = new MenuItem("_Cadastrar");
        providerRegister.setOnAction(e -> addTab(new RegisterProducer()));
        
        MenuItem providerColect = new MenuItem("C_oletar");
        providerColect.setOnAction(e -> addTab(new RegisterCollect()));
        
        MenuItem providerPay = new MenuItem("_Pagar");

        provider.getItems().addAll(providerRegister, new SeparatorMenuItem(), providerColect, providerPay);

        Menu report = new Menu("_Relatórios");
        MenuItem reportColect = new MenuItem("_Coletas");
        report.getItems().add(reportColect);

        Menu exit = new Menu("_Sair");
        exit.getItems().add(new MenuItem());
        exit.setOnShowing(e -> System.exit(1));

        bar.getMenus().addAll(configuration, provider, report, exit);

        return bar;
    }

    private ButtonBar getButtonBar() {
        ButtonBar bar = new ButtonBar();
        bar.setStyle("-fx-background-color: #DEECF6");
        bar.setCursor(Cursor.HAND);

        bar.getButtons().add(new Button("TESTE"));
        return bar;
    }

    private void addTab(Tab tab) {
        if(this.tabPane.getTabs().stream().noneMatch(t -> t.getId().equals(tab.getId()))){
            this.tabPane.getTabs().add(tab);
            this.tabPane.getSelectionModel().select(tab);
        }
    }       
}
