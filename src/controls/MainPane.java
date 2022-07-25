package controls;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alessandro Sampaio
 */
public class MainPane extends AnchorPane {
    
    public MainPane(){
        super();
        setChildren();
    }

    private void setChildren() {
        VBox box = new VBox(0, getMenuBar(), getButtonBar());
        box.setAlignment(Pos.CENTER_LEFT);
        AnchorPane.setTopAnchor(box, 0.0);
        AnchorPane.setLeftAnchor(box, 0.0);
        AnchorPane.setRightAnchor(box, 0.0);
        
        this.getChildren().add(box);
    }
    
    private MenuBar getMenuBar(){
        MenuBar bar = new MenuBar();
        
        Menu configuration = new Menu("_Configuração");
        
        MenuItem configurationPrinter = new MenuItem("_Impressora");
        configuration.getItems().add(configurationPrinter);
        configurationPrinter.setOnAction(e -> System.out.println("Configura Impressora"));
        
        Menu provider = new Menu("_Produtores");
        MenuItem providerRegister = new MenuItem("_Cadastrar");
        MenuItem providerColect = new MenuItem("C_oletar");
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
    
    private ButtonBar getButtonBar(){
        ButtonBar bar = new ButtonBar();
        bar.setStyle("-fx-background-color: #DEECF6");
        bar.setCursor(Cursor.HAND);
        
        bar.getButtons().add(new Button("TESTE"));
        return bar;
    }
    
}
