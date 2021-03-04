package com.example.vendingmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.InputStream;
import java.net.URL;

public class FoodActivity extends AppCompatActivity {

    TextView name,price;
    ImageView imageView;
    ImageView imageButton;
    Button button,b2;
    TextureView textureView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        name = findViewById(R.id.itemnameCart);
        textureView = findViewById(R.id.textureView);
        price = findViewById(R.id.itempriceCart);
        imageView = findViewById(R.id.imageCart);
        imageButton = findViewById(R.id.button);
        button = findViewById(R.id.orderbutton);

        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this,R.raw.welcome);
        mediaPlayer2.start();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("order");
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.plzwait);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = getIntent().getExtras();

             String item = bundle.getString("itemName");
             myRef.setValue(item);

             Toast.makeText(FoodActivity.this, "Order proceed", Toast.LENGTH_SHORT).show();
             mediaPlayer.start();
//                Intent intent = new Intent(FoodActivity.this,MainActivity.class);
//                startActivity(intent);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FoodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
//        Bundle extras = getIntent().getExtras();
        if (bundle != null) {


            final String imgPath = bundle.getString("itemImage");
            StrictMode();

            try {
                URL url = new URL(imgPath);
                imageView.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            name.setText(bundle.getString("itemName"));
            price.setText(bundle.getString("itemPrice"));
            
        }
    }
        private void StrictMode(){
            android.os.StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            android.os.StrictMode.setThreadPolicy(policy);
        }
}