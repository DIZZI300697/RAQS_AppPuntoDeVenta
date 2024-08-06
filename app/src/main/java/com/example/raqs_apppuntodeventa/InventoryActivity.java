package com.example.raqs_apppuntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InventoryActivity extends AppCompatActivity {

    private Button btnInsertProduct, btnDeleteProduct, btnUpdateProduct, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        btnInsertProduct = findViewById(R.id.btnInsertProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnBack = findViewById(R.id.btnBack);

        btnInsertProduct.setOnClickListener(v -> startActivity(new Intent(this, InsertProductActivity.class)));
        btnDeleteProduct.setOnClickListener(v -> openDeleteProductDialog());
        btnUpdateProduct.setOnClickListener(v -> openUpdateProductDialog());
        btnBack.setOnClickListener(v -> finish()); // Termina esta actividad y regresa a la anterior
    }

    private void openDeleteProductDialog() {
        DeleteProductDialog deleteProductDialog = new DeleteProductDialog();
        deleteProductDialog.show(getSupportFragmentManager(), "DeleteProductDialog");
    }

    private void openUpdateProductDialog() {
        UpdateProductDialog updateProductDialog = new UpdateProductDialog();
        updateProductDialog.show(getSupportFragmentManager(), "UpdateProductDialog");
    }
}

