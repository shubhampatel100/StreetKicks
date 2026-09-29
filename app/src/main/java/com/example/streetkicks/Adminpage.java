package com.example.streetkicks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Adminpage extends Activity {
    Button btAdd,btShow,btDelete,btUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btAdd = findViewById(R.id.btAdd);
        btShow = findViewById(R.id.btShow);
        btDelete = findViewById(R.id.btDelete);
        btUpdate = findViewById(R.id.btUpdate);

        Intent i2 = getIntent();
        String ans = i2.getStringExtra("ans");


        btAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(),AddShoes.class);
                i1.putExtra("ans","SUCCESSFULL");
                startActivity(i1);

            }
        });
        btShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(getApplicationContext(),ShowShoes.class);
                i2.putExtra("ans","SUCCESSFULL");
                startActivity(i2);

            }
        });

        btDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(getApplicationContext(),Delete.class);
                i2.putExtra("ans","SUCCESSFULL");
                startActivity(i2);

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(getApplicationContext(),Update.class);
                i2.putExtra("ans","SUCCESSFULL");
                startActivity(i2);

            }
        });
    }
}