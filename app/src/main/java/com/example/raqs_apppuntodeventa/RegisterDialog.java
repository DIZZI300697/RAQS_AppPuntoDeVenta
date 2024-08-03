package com.example.raqs_apppuntodeventa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RegisterDialog extends AppCompatDialogFragment {

    private EditText etUsername, etPassword;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_register, null);

        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);

        builder.setView(view)
                .setTitle("Register")
                .setNegativeButton("Cancel", (dialogInterface, i) -> {
                })
                .setPositiveButton("Register", (dialogInterface, i) -> registerUser());

        return builder.create();
    }

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = new DatabaseHelper(getActivity()).getWritableDatabase();
        db.execSQL("INSERT INTO users (username, password) VALUES (?, ?)", new Object[]{username, password});
        db.close();
        Toast.makeText(getActivity(), "User registered successfully", Toast.LENGTH_SHORT).show();
    }
}
