package com.example.ostoslista;

import java.io.Serializable;

public class ShopItem implements Serializable {
    public String item;
    public String reminder;
    public int addingOrder;

    public ShopItem(String item, String reminder, int addingOrder) {
        this.item = item;
        this.reminder = reminder;
        this.addingOrder = addingOrder;
    }
    public String getItem() { return item; }

    public String getReminder() { return reminder; }

    public int getAddingOrder() { return addingOrder; }

    public void setItem(String item) {
        this.item = item;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

}
