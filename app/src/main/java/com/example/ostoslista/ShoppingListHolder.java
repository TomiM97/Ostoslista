package com.example.ostoslista;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShoppingListHolder extends RecyclerView.ViewHolder {
    public ImageView imgDelete, imgEdit;
    public TextView txtShopItem, txtReminder;
    public EditText editShopItem, editReminder;
    public boolean isEditing;

    public ShoppingListHolder(@NonNull View itemView) {
        super(itemView);
        imgDelete = itemView.findViewById(R.id.imgDelete);
        imgEdit = itemView.findViewById(R.id.imgEdit);
        txtShopItem = itemView.findViewById(R.id.txtShopItem);
        txtReminder = itemView.findViewById(R.id.txtReminder);
        editShopItem = itemView.findViewById(R.id.txtEditShopItem);
        editReminder = itemView.findViewById(R.id.txtEditReminder);
        isEditing = false;
    }
}
