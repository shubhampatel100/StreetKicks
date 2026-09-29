package com.example.streetkicks;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddShoes extends Activity {

    EditText etBrand,etType,etCategory,etColor,etSize,etPrice,etRating,etModelnum;
    ImageView iv;
    Button AddImage,Add,Cancel;
    Uri imgPath;
    FirebaseAuth fAuth;
    DatabaseReference dbRef=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_shoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etBrand = findViewById(R.id.etBrand);
        etType = findViewById(R.id.etType);
        etCategory = findViewById(R.id.etCategory);
        etColor = findViewById(R.id.etColor);
        etSize = findViewById(R.id.etSize);
        etPrice = findViewById(R.id.etPrice);
        etRating = findViewById(R.id.etRating);
        etModelnum = findViewById(R.id.etModelnum);
        iv = findViewById(R.id.iv);
        AddImage = findViewById(R.id.AddImage);
        Add = findViewById(R.id.Add);
        Cancel = findViewById(R.id.Cancel);


        ///

        fAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("shoes");

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Brand = etBrand.getText().toString();
                String Type = etType.getText().toString();
                String Category = etCategory.getText().toString();
                String Color = etColor.getText().toString();

                if (Brand.isEmpty() || Type.isEmpty() || Category.isEmpty() || Color.isEmpty() ||
                        etSize.getText().toString().isEmpty() || etPrice.getText().toString().isEmpty() ||
                        etRating.getText().toString().isEmpty() || etModelnum.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (imgPath == null) {
                    Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_SHORT).show();
                    return;
                }

                int Size = Integer.parseInt(etSize.getText().toString());
                int Price = Integer.parseInt(etPrice.getText().toString());
                int Rating = Integer.parseInt(etRating.getText().toString());
                String imgUrl = imgPath.toString();
                int Modelnum = Integer.parseInt(etModelnum.getText().toString());

                String sid = dbRef.push().getKey();
                // generates unique id for current record

                Shoes u1 = new Shoes(sid,Brand,Type,Category,Color,Size,Price,Rating,imgUrl,Modelnum);
                dbRef.child(sid).setValue(u1);

                Toast.makeText(getApplicationContext(),"RECORD INSERTED.... ",Toast.LENGTH_LONG).show();

                etBrand.setText("");
                etType.setText("");
                etCategory.setText("");
                etColor.setText("");
                etSize.setText("");
                etPrice.setText("");
                etRating.setText("");
                etModelnum.setText("");
                iv.setImageURI(null);
                imgPath = null;

            }
        });


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                ii.addCategory(Intent.CATEGORY_OPENABLE);
                ii.setType("image/*");
                startActivityForResult(ii, 121);
            }
        });
    }

    @Override
    public void onActivityResult(int req,int res,Intent data) {
        if (req == 121 && res == RESULT_OK && data != null) {
            imgPath = data.getData();
            getContentResolver().takePersistableUriPermission(imgPath, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            iv.setImageURI(imgPath);
        } else if (req == 151 && res == RESULT_OK) {
            Bitmap img = (Bitmap) data.getExtras().get("data");
            iv.setImageBitmap(img);
        }
    }
}