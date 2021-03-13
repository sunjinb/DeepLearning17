package com.example.firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomVieHolder> {

    private ArrayList<user> arraylist;
    private Context context;

    public CustomAdapter(ArrayList<user> arraylist, Context context) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomVieHolder holder = new CustomVieHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVieHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arraylist.get(position).getProfile())
                .into(holder.iv_profile);

        holder.tv_id.setText(arraylist.get(position).getId());
        holder.tv_pw.setText(String.valueOf(arraylist.get(position).getPw()));
        holder.tv_username.setText(arraylist.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return (arraylist != null ? arraylist.size() : 0);
    }

    public class CustomVieHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_username;

        public CustomVieHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_username = itemView.findViewById(R.id.tv_username);
        }
    }
}
