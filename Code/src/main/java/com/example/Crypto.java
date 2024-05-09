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
        this.setPrice(price);
        this.quantity = quantity;
        this.nameProperty = new SimpleStringProperty(name);
        this.quantityProperty = new SimpleStringProperty(quantity+"");
    }

    public StringProperty getNameProperty() {
        return nameProperty;
    }

    public String getName() {
        return nameProperty.get();
    }

    public StringProperty getPriceProperty() {
        return priceProperty;
    }

    public StringProperty getQuantityProperty() {
        return quantityProperty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.priceProperty= new SimpleStringProperty(price+"");
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

    @Override
    public String toString() {
        return "Crypto [price=" + price + ", quantity=" + quantity + ", nameProperty=" + nameProperty
                + ", priceProperty=" + priceProperty + ", quantityProperty=" + quantityProperty + "]";
    }

    
    
}
