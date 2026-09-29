package com.example.streetkicks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopingAdepter extends ArrayAdapter<Shoes> {
    Context cont;
    int design;
    ArrayList<Shoes> listsp;

    ShopingAdepter(Context cont, int design, ArrayList<Shoes> list) {
        super(cont, design, list);
        this.cont = cont;
        this.design = design;
        this.listsp = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater lay = LayoutInflater.from(cont);
        View view = lay.inflate(design, null, false);

        ImageView iv = view.findViewById(R.id.iv);
        TextView tvType = view.findViewById(R.id.shType);
        TextView tvPrice = view.findViewById(R.id.shPrice);
        Button btAdd = view.findViewById(R.id.btadd);
        Button btRemove = view.findViewById(R.id.btremove);
        TextView numshow = view.findViewById(R.id.numshow);
        TextView Shcol = view.findViewById(R.id.shcol);
        TextView Shname = view.findViewById(R.id.shname);

        Shoes sh = listsp.get(position);

        tvType.setText("Type: " + sh.getType());
        tvPrice.setText("Price: " + sh.getPrice());
        Shcol.setText("Color: " + sh.getColor());
        Shname.setText("Name: " + sh.getBrand());
        numshow.setText(String.valueOf(sh.getQuantity()));

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sh.setQuantity(sh.getQuantity() + 1);
                numshow.setText(String.valueOf(sh.getQuantity()));
            }
        });

        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sh.getQuantity() > 0) {
                    sh.setQuantity(sh.getQuantity() - 1);
                    numshow.setText(String.valueOf(sh.getQuantity()));
                }
            }
        });

        return view;
    }
}
