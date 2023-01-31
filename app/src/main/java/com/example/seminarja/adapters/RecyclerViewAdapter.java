package com.example.seminarja.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seminarja.R;
import com.example.seminarja.data.User;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<User> userArrayList;

    public RecyclerViewAdapter(ArrayList<User> userList){
        userArrayList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.user_layout,parent,  false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.position.setText(position+1 + ".");
        holder.name.setText(userArrayList.get(position).getName());
        holder.points.setText(userArrayList.get(position).getPoints().toString());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView position,name,points;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            position = itemView.findViewById(R.id.position);
            name = itemView.findViewById(R.id.user);
            points = itemView.findViewById(R.id.points);

        }
    }

}
