package com.example.itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    Button btn_register;
    EditText et_item_name, et_item_id, et_item_model, et_item_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        et_item_name = findViewById(R.id.et_item_name);
        et_item_id = findViewById(R.id.et_item_id);
        et_item_model = findViewById(R.id.et_item_model);
        et_item_desc = findViewById(R.id.et_item_desc);

        btn_register.setOnClickListener(v -> {
            ItemModel itemModel;
            try {
                itemModel = new ItemModel(-1, et_item_id.getText().toString(), et_item_desc.getText().toString(), et_item_model.getText().toString(), et_item_name.getText().toString());
                Toast.makeText(AddItem.this, itemModel.toString(), Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(AddItem.this, "Error saving item", Toast.LENGTH_SHORT).show();
                itemModel = new ItemModel(-1, "wee","ee", "ee", "dd");
            }

            DatabaseHelper dataBaseHelper = new DatabaseHelper(AddItem.this);

            boolean success = dataBaseHelper.addOneItem(itemModel);

            Toast.makeText(AddItem.this, "Success" + success, Toast.LENGTH_SHORT).show();
        });

    }
}