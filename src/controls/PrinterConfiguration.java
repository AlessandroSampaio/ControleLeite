package controls;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;

/**
 *
 * @author AlessandroSampaio
 */
public class PrinterConfiguration extends Tab {
    
    public PrinterConfiguration(){
        super();
        this.setText("Configura Impressora");
        this.setId(this.getClass().getName());
        setContent();
    }

    private void setContent() {
        VBox contentVBox = new VBox(15.0);
        contentVBox.setPadding(new Insets(20.0));
        
        ComboBox<String> selectCom = new ComboBox<>();
        
        contentVBox.getChildren().add(selectCom);
        this.setContent(contentVBox);
    }
    
    public boolean equals(){
        return true;
    }
    
}
