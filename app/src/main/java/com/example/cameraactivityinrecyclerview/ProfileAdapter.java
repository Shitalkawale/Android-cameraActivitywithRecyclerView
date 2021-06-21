package com.example.cameraactivityinrecyclerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileHolder> {

    Context mContex;
    List<ModuleClass> profileList;

    public ProfileAdapter(Context mContex, List<ModuleClass> profileList) {
        this.mContex = mContex;
        this.profileList = profileList;
    }

    @NonNull
    @Override
    public ProfileAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View myview= LayoutInflater.from(mContex).inflate(R.layout.custome_design,parent,false);

        return new ProfileHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileHolder holder, int position)
    {
        ModuleClass mn=profileList.get(position);
        holder.nametxt.setText(mn.getName());
        //holder.emailtxt.setText(mn.getEmail());

        Bitmap myimg= BitmapFactory.decodeByteArray(mn.getProfile(),0,mn.getProfile().length);
        holder.profileimg.setImageBitmap(myimg);

        holder.nametxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContex,SecondActivity.class);
                intent.putExtra("PROFILE",mn.getProfile());
                intent.putExtra("NAME",mn.getName());
                intent.putExtra("EMAIL",mn.getEmail());
                mContex.startActivity(intent);
                Toast.makeText(mContex,"You clicked on: "+holder.nametxt.getText(),Toast.LENGTH_LONG).show();
            }
        });

//        holder.emailtxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{(String) holder.emailtxt.getText()});
//                intent.setType("message/rfc822");
//                mContex.startActivity(Intent.createChooser(intent,"Choose client"));
//                Toast.makeText(mContex,"You clicked on: "+holder.emailtxt.getText(),Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }


    public class ProfileHolder extends RecyclerView.ViewHolder {

        TextView nametxt,emailtxt;
        CircleImageView profileimg;

        public ProfileHolder(@NonNull View itemView)
        {
            super(itemView);

            nametxt=itemView.findViewById(R.id.getname);
            emailtxt=itemView.findViewById(R.id.getemail);
            profileimg=itemView.findViewById(R.id.getimg);
        }
    }
}
