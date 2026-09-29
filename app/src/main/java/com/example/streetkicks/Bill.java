package com.example.streetkicks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Bill extends Activity {

    ListView listView;
    TextView tvTotal;
    Button btnBuy;
    ArrayList<Shoes> selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        listView = findViewById(R.id.billListView);
        tvTotal = findViewById(R.id.billTotal);
        btnBuy = findViewById(R.id.btbuy);

        selectedItems = (ArrayList<Shoes>) getIntent().getSerializableExtra("selectedItems");

        if (selectedItems != null) {
            BillAdapter adapter = new BillAdapter(this, selectedItems);
            listView.setAdapter(adapter);

            int total = 0;
            for (Shoes s : selectedItems) {
                total += (s.getPrice() * s.getQuantity());
            }
            tvTotal.setText("₹" + total);
        }

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bill.this, Customer.class);
                startActivity(intent);
            }
        });
    }
}
