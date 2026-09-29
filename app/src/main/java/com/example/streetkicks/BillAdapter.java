package com.example.streetkicks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BillAdapter extends ArrayAdapter<Shoes> {
    Context context;
    ArrayList<Shoes> items;

    public BillAdapter(Context context, ArrayList<Shoes> items) {
        super(context, R.layout.bill_item_design, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bill_item_design, parent, false);
        }

        Shoes item = items.get(position);

        TextView tvBrand = convertView.findViewById(R.id.billItemBrand);
        TextView tvQty = convertView.findViewById(R.id.billItemQty);
        TextView tvPrice = convertView.findViewById(R.id.billItemPrice);

        tvBrand.setText(item.getBrand());
        tvQty.setText("Qty: " + item.getQuantity());
        tvPrice.setText("₹" + (item.getPrice() * item.getQuantity()));

        return convertView;
    }
}
