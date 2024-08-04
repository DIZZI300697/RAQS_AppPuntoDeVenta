package com.example.raqs_apppuntodeventa;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        }

        Product product = (Product) getItem(position);

        TextView tvID = convertView.findViewById(R.id.tvProductID);
        TextView tvName = convertView.findViewById(R.id.tvProductName);
        TextView tvPrice = convertView.findViewById(R.id.tvProductPrice);
        TextView tvQuantity = convertView.findViewById(R.id.tvProductQuantity);
        ImageView ivImage = convertView.findViewById(R.id.ivProductImage);

        tvID.setText("ID: " + product.getId());
        tvName.setText(product.getName());
        tvPrice.setText(String.format("$%.2f", product.getPrice()));
        tvQuantity.setText(String.valueOf(product.getQuantity()));

        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ivImage.setImageBitmap(decodedByte);

        return convertView;
    }
}
