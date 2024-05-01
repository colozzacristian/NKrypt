package com.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Crypto {

    private double price;
    private double quantity;
    private StringProperty nameProperty;
    private StringProperty priceProperty;
    private StringProperty quantityProperty;

    public Crypto(String name, double price, double quantity) {
        this.price = price;
        this.quantity = quantity;
        this.nameProperty = new SimpleStringProperty(name);
        this.priceProperty = new SimpleStringProperty(price+"");
        this.quantityProperty = new SimpleStringProperty(quantity+"");
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getName() {
        return nameProperty.get();
    }

    public StringProperty getPriceProperty() {
        return nameProperty;
    }

    public StringProperty getQuantityProperty() {
        return nameProperty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public StringProperty getAssetValue(){
        return new SimpleStringProperty(price*quantity+"");
    }
    
}
