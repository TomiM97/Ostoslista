package com.example.ostoslista;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShoppingList {
    private static ShoppingList shoppingListInstance = null;
    public ArrayList<ShopItem> shopItems = new ArrayList<>();

    public ShoppingList() {}

    public ArrayList<ShopItem> getShopItems() { return shopItems; }

    public ShopItem getOneShopItem(int pos) {return shopItems.get(pos); }

    public void addShopItem(ShopItem shopItem) {
        shopItems.add(shopItem);
    }

    public void removeShopItem(int key) {
        int i = 0;
        for (ShopItem s : shopItems) {
            if (s.getAddingOrder() == key) {
                break;
            }
        i++;
        }
        shopItems.remove(i);
    }

    public static ShoppingList getInstance() {
        if (shoppingListInstance == null) {
            shoppingListInstance = new ShoppingList();
        }
        return shoppingListInstance;
    }

    public void reArrangeShoppingList(int key) {
        // to calendar
        if (key == 1) {
            shopItems.sort(new Comparator<ShopItem>() {
                @Override
                public int compare(ShopItem shopItem1, ShopItem shopItem2) {
                    return Integer.compare(shopItem1.getAddingOrder(), shopItem2.getAddingOrder());
                }
            });
        }
        // to alphabet
        else {
            shopItems.sort(new Comparator<ShopItem>() {
                @Override
                public int compare(ShopItem shopItem1, ShopItem shopItem2) {
                    return shopItem1.getItem().compareTo(shopItem2.getItem());
                }
            });
        }
    }

}
