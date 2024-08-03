package com.example.raqs_apppuntodeventa;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteProductDialog extends AppCompatDialogFragment {

    private EditText etProductId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_delete_product, null);

        etProductId = view.findViewById(R.id.etProductId);

        builder.setView(view)
                .setTitle("Delete Product")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                })
                .setPositiveButton("Delete", (dialogInterface, i) -> deleteProduct());

        return builder.create();
    }

    private void deleteProduct() {
        String productId = etProductId.getText().toString().trim();

        if (productId.isEmpty()) {
            Toast.makeText(getActivity(), "Please enter a product ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = new DatabaseHelper(getActivity()).getWritableDatabase();
        db.delete("products", "id = ?", new String[]{productId});
        db.close();
        Toast.makeText(getActivity(), "Product deleted successfully", Toast.LENGTH_SHORT).show();
    }
}
