package com.example.Crypto;

import java.io.Serializable;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Crypto implements Serializable{


    private String name;
    private double price;
    private double quantity;
    private transient StringProperty nameProperty;
    private transient StringProperty priceProperty;
    private transient StringProperty quantityProperty;

    public Crypto(String name, double price, double quantity) {
        this.name=name;
        this.setPrice(price);
        this.quantity = quantity;
        this.nameProperty = new SimpleStringProperty(name);
        this.quantityProperty = new SimpleStringProperty(quantity+"");
    }

    public Crypto(Crypto c) {
        this.name=c.getName();
        this.setPrice(c.getPrice());
        this.quantity = c.getQuantity();
        this.nameProperty = new SimpleStringProperty(c.getName());
        this.quantityProperty = new SimpleStringProperty(c.getQuantity()+"");
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getName() {
        return this.name;
    }

    public StringProperty getPriceProperty() {
        return priceProperty;
    }

    public StringProperty getQuantityProperty() {
        return quantityProperty;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        this.priceProperty= new SimpleStringProperty(price+"");
    }

    public Double getQuantity() {
        return quantity;
    }
    

    public void setQuantityProperty(StringProperty quantityProperty) {
        this.quantityProperty = quantityProperty;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
        this.quantityProperty= new SimpleStringProperty(quantity+"");
    }

    public StringProperty getAssetValue(){
        return new SimpleStringProperty(price*quantity+"");
    }

    @Override
    public String toString() {
        return "Crypto [price=" + price + ", quantity=" + quantity + ", nameProperty=" + nameProperty
                + ", priceProperty=" + priceProperty + ", quantityProperty=" + quantityProperty + "]";
    }

    
    
}
