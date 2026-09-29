package com.example.streetkicks;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowShoes extends Activity {

    ListView showlv;
    ArrayList<Shoes> list = new ArrayList<Shoes>();
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_shoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showlv = findViewById(R.id.showlv);
        dbRef = FirebaseDatabase.getInstance().getReference("shoes");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for(DataSnapshot snap : snapshot.getChildren())
                {
                    Shoes u1 = snap.getValue(Shoes.class);
                    list.add(u1);
                }
               // ArrayAdapter<Shoes> ad = new ArrayAdapter<Shoes>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                CustomAdapter ca = new CustomAdapter(getApplicationContext(),R.layout.mydesign,list);
                showlv.setAdapter(ca);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}