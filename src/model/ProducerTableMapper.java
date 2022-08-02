package model;

import java.time.format.DateTimeFormatter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author AlessandroSampaio
 */
public class ProducerTableMapper {

    private final Producer producer;

    private final StringProperty Id;
    private final StringProperty Name;
    private final StringProperty RegisterDate;
    private final StringProperty LastCollect;
    private final BooleanProperty Active;

    private final DateTimeFormatter outDF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ProducerTableMapper(Producer producer) {
        this.producer = producer;
        this.Id = new SimpleStringProperty(String.valueOf(producer.getId()));
        this.Name = new SimpleStringProperty(producer.getName());
        this.RegisterDate = new SimpleStringProperty(producer.getRegisterDate().format(outDF));
        if (producer.getLastCollect() == null) {
            this.LastCollect = new SimpleStringProperty();
        } else {
            this.LastCollect = new SimpleStringProperty(producer.getLastCollect().format(outDF));
        }
        this.Active = new SimpleBooleanProperty(producer.isActive());
    }

    public Producer getProducer() {
        return producer;
    }

    public StringProperty getId() {
        return Id;
    }

    public StringProperty getName() {
        return Name;
    }

    public StringProperty getRegisterDate() {
        return RegisterDate;
    }

    public StringProperty getLastCollect() {
        return LastCollect;
    }

    public BooleanProperty getActive() {
        return Active;
    }

    public DateTimeFormatter getOutDF() {
        return outDF;
    }

}
