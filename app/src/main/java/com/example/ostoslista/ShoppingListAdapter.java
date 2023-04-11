package com.example.ostoslista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListHolder> {
    private Context context;
    private ArrayList<ShopItem> shopItems = new ArrayList<>();

    public ShoppingListAdapter(Context context, ArrayList<ShopItem> shopItems) {
        this.context = context;
        this.shopItems = shopItems;
    }

    @NonNull
    @Override
    public ShoppingListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShoppingListHolder(LayoutInflater.from(context).inflate(R.layout.shoppinglist_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingListHolder holder, int position) {
        holder.txtShopItem.setText(shopItems.get(position).getItem());
        holder.txtReminder.setText(shopItems.get(position).getReminder());

        // remove option
        holder.imgDelete.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            ShoppingList.getInstance().removeShopItem(shopItems.get(pos).getAddingOrder());
            notifyItemRemoved(pos);
        });

        // edit option
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                holder.isEditing = !holder.isEditing;

                if (holder.isEditing) {
                    // enter edit mode
                    holder.editShopItem.setVisibility(View.VISIBLE);
                    holder.editReminder.setVisibility(View.VISIBLE);
                    holder.txtShopItem.setVisibility(View.GONE);
                    holder.txtReminder.setVisibility(View.GONE);
                    holder.editShopItem.setText(shopItems.get(pos).getItem());
                    holder.editReminder.setText(shopItems.get(pos).getReminder());
                } else {
                    // exit edit mode
                    holder.editShopItem.setVisibility(View.GONE);
                    holder.editReminder.setVisibility(View.GONE);
                    holder.txtShopItem.setVisibility(View.VISIBLE);
                    holder.txtReminder.setVisibility(View.VISIBLE);

                    ShopItem shopItem = ShoppingList.getInstance().getOneShopItem(pos);
                    shopItem.setItem(holder.editShopItem.getText().toString());
                    shopItem.setReminder(holder.editReminder.getText().toString());
                    notifyDataSetChanged();
                }
            }
        });
    }
    public void updateData(ArrayList<ShopItem> newShopItems) {
        this.shopItems = newShopItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }
}
