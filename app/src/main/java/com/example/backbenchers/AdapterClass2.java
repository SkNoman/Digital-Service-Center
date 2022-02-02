package com.example.backbenchers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass2 extends RecyclerView.Adapter<AdapterClass2.MyViewHolder> {




    Context context;
    ArrayList<UserHelperClass>mList;

    public AdapterClass2(Context context, ArrayList<UserHelperClass> mList) {
        this.mList = mList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutfile2,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText("Name: "+mList.get(position).getName());
        holder.phone.setText("Phone: "+mList.get(position).getPhone());
        holder.email.setText("Email: "+mList.get(position).getEmail());
        holder.city.setText("Address: "+mList.get(position).getCity());
        holder.username.setText("Username: "+mList.get(position).getUsername());
        holder.devicetoken.setText("Token:"+mList.get(position).getDevice_token());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,city,phone,username,devicetoken;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.aname);
            email=itemView.findViewById(R.id.aemail);
            phone=itemView.findViewById(R.id.aphone);
            city=itemView.findViewById(R.id.acity);
            username=itemView.findViewById(R.id.ausername);
            devicetoken=itemView.findViewById(R.id.atoken);





        }
    }
}
