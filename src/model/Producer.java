package model;

import java.time.LocalDateTime;

/**
 *
 * @author AlessandroSampaio
 */
public class Producer implements Model {
    
    private int id;
    private String name;
    private LocalDateTime lastCollect;
    private LocalDateTime registerDate;
    private Boolean active;

    public Producer() {
    }        

    public Producer(int id, String name, LocalDateTime lastCollect, LocalDateTime registerDate, Boolean active) {
        this.id = id;
        this.name = name;
        this.lastCollect = lastCollect;
        this.registerDate = registerDate;
        this.active = active;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getLastCollect() {
        return lastCollect;
    }

    public void setLastCollect(LocalDateTime lastCollect) {
        this.lastCollect = lastCollect;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
    
    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void changeStatus() {
        this.active = !active;
    }
    
    
    

}
