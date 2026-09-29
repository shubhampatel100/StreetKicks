package com.example.streetkicks;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.health.ServiceHealthStats;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Shoes>
{
    Context cont;
    int design;
    ArrayList<Shoes> list;

    CustomAdapter(Context cont, int design, ArrayList<Shoes> list)
    {
        super(cont,design,list);
        this.cont = cont;
        this.design = design;
        this.list = list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater lay = LayoutInflater.from(cont);
        View view = lay.inflate(design,null,false);

        ImageView iv = view.findViewById(R.id.iv);
        TextView tvBrand = view.findViewById(R.id.shBrand);
        TextView tvPrice = view.findViewById(R.id.shPrice);
        TextView tvType = view.findViewById(R.id.shType);
        TextView tvCategory = view.findViewById(R.id.shCategory);
        TextView tvModelNum = view.findViewById(R.id.shModelNum);

        Shoes sh = list.get(position);



        tvBrand.setText(sh.getBrand());
        tvPrice.setText("Price: "+sh.getPrice());
        tvType.setText("Type: "+sh.getType());
        tvCategory.setText("Category: "+sh.getCategory());
        tvModelNum.setText("Model: "+sh.getModelnum());


        return view;
    }




}
