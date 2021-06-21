package com.example.cameraactivityinrecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SecondActivity extends AppCompatActivity {

    CircleImageView img2;
    TextView nametxt,emailtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        img2=findViewById(R.id.image2);
        nametxt=findViewById(R.id.txtname);
        emailtxt=findViewById(R.id.txtemail);

        Intent intent=getIntent();
        byte[] profile=intent.getByteArrayExtra("PROFILE");
        String name=intent.getStringExtra("NAME");
        String email=intent.getStringExtra("Email");
        Bitmap bitmap= BitmapFactory.decodeByteArray(profile,0,profile.length);

        img2.setImageBitmap(bitmap);
        nametxt.setText(name);
        emailtxt.setText(email);


    }
}