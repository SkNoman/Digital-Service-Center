package com.example.backbenchers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass3 extends RecyclerView.Adapter<AdapterClass3.MyViewHolder> {




    Context context;
    ArrayList<UserFeedbackHelperClass>mList;

    public AdapterClass3(Context context, ArrayList<UserFeedbackHelperClass> mList) {
        this.mList = mList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.layoutfile3,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText("Name: "+mList.get(position).getName());
        holder.email.setText("Email: "+mList.get(position).getEmail());
        holder.username.setText("Username: "+mList.get(position).getUsername());
        holder.feedback.setText("Message:"+mList.get(position).getFeedback());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,email,username,feedback;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.fname);
            email=itemView.findViewById(R.id.femail);

            username=itemView.findViewById(R.id.fusername);
            feedback=itemView.findViewById(R.id.ffeedback);





        }
    }
}
