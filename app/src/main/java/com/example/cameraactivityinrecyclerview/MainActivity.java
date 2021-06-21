package com.example.cameraactivityinrecyclerview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CircleImageView circleImageView,camera;
    int CAMERA_REQUEST_CODE=101;
    int CAMERA_PERMISSION_CODE=102;

    List<ModuleClass> profileList;
    ProfileAdapter profileAdapter;

    Button addbtn1;
    EditText edtName,edtEmail;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        circleImageView=findViewById(R.id.circularimage);

        edtName=findViewById(R.id.name);
        edtEmail=findViewById(R.id.email);
        recyclerView=findViewById(R.id.recyclerview);
        addbtn1=findViewById(R.id.addbtn);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileList=new ArrayList<>();
        profileAdapter=new ProfileAdapter(this,profileList);
        recyclerView.setAdapter(profileAdapter);

        addbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleClass mn=new ModuleClass();
                String myname=edtName.getText().toString();
                //String myemail=edtEmail.getText().toString();
                byte[] bytearray=convert_to_byteArray(circleImageView);

               // mn.setEmail(myemail);
                mn.setName(myname);
                mn.setProfile(bytearray);

                profileList.add(mn);
                profileAdapter.notifyDataSetChanged();

            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.search:
                Toast.makeText(getApplicationContext(), "select search", Toast.LENGTH_SHORT).show();
                break;

            case R.id.add:
                Toast.makeText(getApplicationContext(), "select Add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.update:
                Toast.makeText(getApplicationContext(), "select Update", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete:
                Toast.makeText(getApplicationContext(), "select Delete", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    void openCamera()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageData=(Bitmap)data.getExtras().get("data");
        circleImageView.setImageBitmap(imageData);
        //getData from intent
    }

    public byte[] convert_to_byteArray(ImageView img)
    {
        Bitmap myimg=((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        myimg.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytearr=byteArrayOutputStream.toByteArray();
        return bytearr;
    }

}