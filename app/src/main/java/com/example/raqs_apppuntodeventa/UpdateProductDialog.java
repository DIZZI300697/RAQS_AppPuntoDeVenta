package com.example.raqs_apppuntodeventa;

import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class UpdateProductDialog extends AppCompatDialogFragment {

    private EditText etProductId, etProductPrice, etProductQuantity;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_product, null);

        etProductId = view.findViewById(R.id.etProductId);
        etProductPrice = view.findViewById(R.id.etProductPrice);
        etProductQuantity = view.findViewById(R.id.etProductQuantity);

        builder.setView(view)
                .setTitle("Actualizar Producto")
                .setNegativeButton("Cancelar", (dialogInterface, i) -> {
                })
                .setPositiveButton("Actualizar", (dialogInterface, i) -> updateProduct());

        return builder.create();
    }

    private void updateProduct() {
        String productId = etProductId.getText().toString().trim();
        String price = etProductPrice.getText().toString().trim();
        String quantity = etProductQuantity.getText().toString().trim();

        if (productId.isEmpty() || price.isEmpty() || quantity.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = new DatabaseHelper(getActivity()).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price", price);
        values.put("quantity", quantity);
        db.update("products", values, "id = ?", new String[]{productId});
        db.close();
        Toast.makeText(getActivity(), "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show();
    }
}
