package com.example.raqs_apppuntodeventa;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FinalizeSaleActivity extends AppCompatActivity {

    private ListView lvSaleDetails;
    private TextView tvTotalAmount;
    private ArrayList<SaleDetail> saleDetailList;
    private SaleDetailAdapter saleDetailAdapter;
    private double totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_sale);

        lvSaleDetails = findViewById(R.id.lvSaleDetails);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        loadSaleDetails();
    }

    private void loadSaleDetails() {
        saleDetailList = new ArrayList<>();
        SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();

        String query = "SELECT s.product_id, s.quantity, s.price, s.amount, p.name " +
                "FROM sale s JOIN products p ON s.product_id = p.id";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int productId = cursor.getInt(0);
                int quantity = cursor.getInt(1);
                double price = cursor.getDouble(2);
                double amount = cursor.getDouble(3);
                String productName = cursor.getString(4);

                totalAmount += amount;
                saleDetailList.add(new SaleDetail(productId, productName, quantity, price, amount));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        saleDetailAdapter = new SaleDetailAdapter(this, saleDetailList);
        lvSaleDetails.setAdapter(saleDetailAdapter);
        tvTotalAmount.setText(String.format("Total: $%.2f", totalAmount));
    }
}
