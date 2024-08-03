package com.example.raqs_apppuntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    private Button btnGoToSale, btnGoToInventory, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnGoToSale = findViewById(R.id.btnGoToSale);
        btnGoToInventory = findViewById(R.id.btnGoToInventory);
        btnExit = findViewById(R.id.btnExit);

        btnGoToSale.setOnClickListener(v -> startActivity(new Intent(this, SaleActivity.class)));
        btnGoToInventory.setOnClickListener(v -> startActivity(new Intent(this, InventoryActivity.class)));
        btnExit.setOnClickListener(v -> finish());
    }
}
