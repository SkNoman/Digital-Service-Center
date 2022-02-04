package com.example.backbenchers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {




    Context context;
    ArrayList<OrderHelperClass>mList;

    public AdapterClass(Context context,ArrayList<OrderHelperClass> mList) {
        this.mList = mList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutfile,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.serviceid.setText("Service Id: "+mList.get(position).getService_id());
        holder.phone.setText("Phone: "+mList.get(position).getPhone());
        holder.servicetype.setText("Servicy Type: "+mList.get(position).getService_type());
        holder.city.setText("Address: "+mList.get(position).getCity());
        holder.message.setText("Message: "+mList.get(position).getMessage());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView serviceid,servicetype,city,phone,message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            servicetype=itemView.findViewById(R.id.ost);
            serviceid=itemView.findViewById(R.id.oi);
            phone=itemView.findViewById(R.id.op);
            message=itemView.findViewById(R.id.om);
            city=itemView.findViewById(R.id.oc);





        }
    }
}
