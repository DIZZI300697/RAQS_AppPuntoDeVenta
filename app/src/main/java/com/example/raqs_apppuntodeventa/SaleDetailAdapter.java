package com.example.raqs_apppuntodeventa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SaleDetailAdapter extends BaseAdapter {
    private Context context;
    private List<SaleDetail> saleDetailList;

    public SaleDetailAdapter(Context context, List<SaleDetail> saleDetailList) {
        this.context = context;
        this.saleDetailList = saleDetailList;
    }

    @Override
    public int getCount() {
        return saleDetailList.size();
    }

    @Override
    public Object getItem(int position) {
        return saleDetailList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sale_detail, parent, false);
        }

        SaleDetail saleDetail = (SaleDetail) getItem(position);

        TextView tvProductId = convertView.findViewById(R.id.tvProductId);
        TextView tvQuantity = convertView.findViewById(R.id.tvQuantity);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvAmount = convertView.findViewById(R.id.tvAmount);

        tvProductId.setText(String.valueOf(saleDetail.getProductId()));
        tvQuantity.setText(String.valueOf(saleDetail.getQuantity()));
        tvPrice.setText(String.format("$%.2f", saleDetail.getPrice()));
        tvAmount.setText(String.format("$%.2f", saleDetail.getAmount()));

        return convertView;
    }
}
