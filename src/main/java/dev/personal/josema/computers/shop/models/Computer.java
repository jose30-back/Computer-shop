package dev.personal.josema.computers.shop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;
    private int ramMemory;
    private String processor;
    private String operatingSystem; 
    private double price;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    public Computer() {
    }

    public Computer(String brand, int ramMemory, String processor, String operatingSystem, double price) {
        this.brand = brand;
        this.ramMemory = ramMemory;
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public int getRamMemory() {
        return ramMemory;
    }
    public void setRamMemory(int ramMemory) {
        this.ramMemory = ramMemory;
    }
    public String getProcessor() {
        return processor;
    }
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    public String getOperatingSystem() {
        return operatingSystem;
    }
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}