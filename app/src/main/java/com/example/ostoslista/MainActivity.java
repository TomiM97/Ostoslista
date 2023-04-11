package com.example.ostoslista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ShoppingList shoppingList;
    private ShoppingListAdapter shoppingListAdapter;
    private RecyclerView recyclerView;
    private AlertDialog dialog;
    private EditText newShopItem, newReminder;
    private int addingOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addingOrder = 0;

        shoppingList = ShoppingList.getInstance();
        recyclerView = findViewById(R.id.rvShoppingList);
        shoppingListAdapter = new ShoppingListAdapter(getApplicationContext(), ShoppingList.getInstance().getShopItems());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shoppingListAdapter);
    }


    public void reArrangeToCalendar(View view) {
        ShoppingList.getInstance().reArrangeShoppingList(1);
        shoppingListAdapter.updateData(ShoppingList.getInstance().getShopItems());
    }

    public void reArrangeToAlphabet(View view) {
        ShoppingList.getInstance().reArrangeShoppingList(0);
        shoppingListAdapter.updateData(ShoppingList.getInstance().getShopItems());
    }

    public void createNewShopItemDialog(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View shopItemPopupView = getLayoutInflater().inflate(R.layout.popup, null);
        newShopItem = shopItemPopupView.findViewById(R.id.txtNewShopItem);
        newReminder = shopItemPopupView.findViewById(R.id.txtNewReminder);

        Button popupSave = shopItemPopupView.findViewById(R.id.btnsave);
        Button popupCancel = shopItemPopupView.findViewById(R.id.btncancel);

        dialogBuilder.setView(shopItemPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        popupSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shopItemText = newShopItem.getText().toString();
                String reminderText = newReminder.getText().toString();
                ShoppingList.getInstance().addShopItem(new ShopItem(shopItemText, reminderText, addingOrder));
                shoppingListAdapter.updateData(ShoppingList.getInstance().getShopItems());
                addingOrder = addingOrder + 1;
                dialog.dismiss();
            }
        });

        popupCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

}