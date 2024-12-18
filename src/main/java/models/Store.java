package models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStore;
    
    private String name; 
    private String owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Computer> computers;

    public Store() {
    }

    public Store(int idStore, String name, String owner) {
        this.idStore = idStore;
        this.name = name;
        this.owner = owner;
    }

    public int getIdStore() {
        return idStore;
    }
    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    } 
    
}
