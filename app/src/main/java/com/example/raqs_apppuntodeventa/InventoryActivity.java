package com.example.raqs_apppuntodeventa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class InventoryActivity extends AppCompatActivity {

    private Button btnInsertProduct, btnDeleteProduct, btnUpdateProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        btnInsertProduct = findViewById(R.id.btnInsertProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);

        btnInsertProduct.setOnClickListener(v -> startActivity(new Intent(this, InsertProductActivity.class)));
        btnDeleteProduct.setOnClickListener(v -> openDeleteProductDialog());
        btnUpdateProduct.setOnClickListener(v -> openUpdateProductDialog());
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
