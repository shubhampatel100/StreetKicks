package com.example.streetkicks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Shopping extends Activity {
    ListView shoppinglv;
    Button btAddCard;
    ArrayList<Shoes> listsp = new ArrayList<Shoes>();
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        shoppinglv = findViewById(R.id.shoppinglv);
        btAddCard = findViewById(R.id.btAddcard);

        btAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Shoes> selectedItems = new ArrayList<>();
                for (Shoes sh : listsp) {
                    if (sh.getQuantity() > 0) {
                        selectedItems.add(sh);
                    }
                }

                if (!selectedItems.isEmpty()) {
                    Intent i = new Intent(Shopping.this, Bill.class);
                    i.putExtra("selectedItems", selectedItems);
                    startActivity(i);
                } else {
                    Toast.makeText(Shopping.this, "Please select at least one item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference("shoes");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listsp.clear();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    Shoes u1 = snap.getValue(Shoes.class);
                    if (u1 != null) {
                        u1.setQuantity(0); // Reset quantity on data load
                        listsp.add(u1);
                    }
                }
                ShopingAdepter sa = new ShopingAdepter(Shopping.this, R.layout.shoppingdesing, listsp);
                shoppinglv.setAdapter(sa);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
