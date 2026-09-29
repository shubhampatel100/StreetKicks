package com.example.streetkicks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Delete extends Activity {
    Spinner spinner;
    Button btDelete,Cancel;
    DatabaseReference dbRef,dbDel;
    List<String> shoeIds = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    List<String> shoeNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // spinner = findViewById(R.id.spinner);
        btDelete = findViewById(R.id.btDelete);
        Cancel = findViewById(R.id.Cancel);
        spinner = findViewById(R.id.spinner);
        dbRef = FirebaseDatabase.getInstance().getReference("shoes");


        final ArrayAdapter<String> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, shoeNames);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for(DataSnapshot snap : snapshot.getChildren())
                {
                    Shoes u1 = snap.getValue(Shoes.class);
                    list.add(u1.modelnum);
                }


                ArrayAdapter<Integer> ad = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,list);
                spinner.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dbDel = FirebaseDatabase.getInstance().getReference();

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int modelno = Integer.parseInt(spinner.getSelectedItem().toString());

                Query delQ = dbDel.child("shoes").orderByChild("modelnum").equalTo(modelno);

                delQ.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snap : snapshot.getChildren())
                        {
                            snap.getRef().removeValue();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}