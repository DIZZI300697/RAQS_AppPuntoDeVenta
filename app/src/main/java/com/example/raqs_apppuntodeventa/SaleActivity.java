package com.example.raqs_apppuntodeventa;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SaleActivity extends AppCompatActivity {

    private ListView lvProducts;
    private TextView tvTotalAmount;
    private ArrayList<Product> productList;
    private ProductAdapter productAdapter;
    private double totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        lvProducts = findViewById(R.id.lvProducts);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        loadProducts();

        Button btnFinalizeSale = findViewById(R.id.btnFinalizeSale); // Asegúrate de que este botón esté en tu archivo XML
        btnFinalizeSale.setOnClickListener(v -> finalizeSale());
    }

    private void loadProducts() {
        productList = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double price = cursor.getDouble(2);
                int quantity = cursor.getInt(3);
                String image = cursor.getString(4);
                productList.add(new Product(id, name, price, quantity, image));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        productAdapter = new ProductAdapter(this, productList);
        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener((parent, view, position, id) -> openQuantityDialog(productList.get(position)));
    }

    private void openQuantityDialog(Product product) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_quantity, null);
        EditText etQuantity = dialogView.findViewById(R.id.etQuantity);

        builder.setView(dialogView)
                .setTitle("Enter Quantity")
                .setPositiveButton("Add", (dialog, which) -> {
                    String quantityStr = etQuantity.getText().toString().trim();
                    if (!quantityStr.isEmpty()) {
                        int quantity = Integer.parseInt(quantityStr);
                        double amount = product.getPrice() * quantity;
                        totalAmount += amount;
                        tvTotalAmount.setText(String.format("Total: $%.2f", totalAmount));
                        saveSale(product, quantity, amount);
                    } else {
                        Toast.makeText(SaleActivity.this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    private void saveSale(Product product, int quantity, double amount) {
        SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", product.getId());
        values.put("quantity", quantity);
        values.put("price", product.getPrice());
        values.put("amount", amount);
        db.insert("sale", null, values);
        db.close();
    }

    public void finalizeSale() {
        Intent intent = new Intent(this, FinalizeSaleActivity.class);
        startActivity(intent);
    }
}
