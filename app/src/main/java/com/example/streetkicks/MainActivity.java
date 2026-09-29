package com.example.streetkicks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {
    EditText etUser, etPass;
    Button btLogin, btShop, btCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btLogin = findViewById(R.id.btLogin);
        btCancel = findViewById(R.id.btCancel);
        btShop = findViewById(R.id.btShop);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String a = etUser.getText().toString();
//                String b = etPass.getText().toString();
//
//                if (a.equals("admin") && b.equals("1234")) {
                    Intent i2 = new Intent(getApplicationContext(), Adminpage.class);
                    i2.putExtra("ans", "SUCCESSFULL");
                    startActivity(i2);
//                } else {
//                    Toast.makeText(getApplicationContext(), "INVALID USER NAME OR PASSWORD ", Toast.LENGTH_LONG).show();
//                }
            }

        });
        btShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String a = etUser.getText().toString();
//                String b = etPass.getText().toString();
//
//                if (a.equals("admin") && b.equals("1234")) {
                Intent i2 = new Intent(getApplicationContext(), Shopping.class);
                i2.putExtra("ans", "SUCCESSFULL");
                startActivity(i2);
//                } else {
//                    Toast.makeText(getApplicationContext(), "INVALID USER NAME OR PASSWORD ", Toast.LENGTH_LONG).show();
//                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}