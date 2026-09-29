package com.example.streetkicks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update extends Activity {
    Spinner spinner;
    EditText etColor, etSize, etPrice;
    Button btUpdate, Cancel;
    DatabaseReference dbRef;
    List<Shoes> shoeList = new ArrayList<>();
    List<Integer> modelNumList = new ArrayList<>();
    int index = 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etColor = findViewById(R.id.etColor);
        etSize = findViewById(R.id.etSize);
        etPrice = findViewById(R.id.etPrice);
        btUpdate = findViewById(R.id.btUpdate);
        Cancel = findViewById(R.id.Cancel);
        spinner = findViewById(R.id.spinner);
        
        dbRef = FirebaseDatabase.getInstance().getReference("shoes");

        final ArrayAdapter<Integer> ad = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modelNumList);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                shoeList.clear();
                modelNumList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Shoes shoe = snap.getValue(Shoes.class);
                    if (shoe != null) {
                        shoeList.add(shoe);
                        modelNumList.add(shoe.getModelnum());
                    }
                }
                ad.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Update.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                Shoes selectedShoe = shoeList.get(index);

                etColor.setText(selectedShoe.getColor());
                etSize.setText(String.valueOf(selectedShoe.getSize()));
                etPrice.setText(String.valueOf(selectedShoe.getPrice()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Color = etColor.getText().toString();
                String Size = etSize.getText().toString();
                String Price = etPrice.getText().toString();

                if (shoeList.size() > 0) {
                    Shoes selectedShoe = shoeList.get(index);
                    selectedShoe.setColor(Color);
                    selectedShoe.setSize(Integer.parseInt(Size));
                    selectedShoe.setPrice(Integer.parseInt(Price));

                    dbRef.child(selectedShoe.getSid()).setValue(selectedShoe);
                    Toast.makeText(Update.this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
                }
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
