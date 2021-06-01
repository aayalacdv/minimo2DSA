package com.example.minimo2;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minimo2.responses.Medalla;

import java.util.ArrayList;

public class MedalsListAdapter extends RecyclerView.Adapter<MedalsListAdapter.ViewHolder>{

    private ArrayList<Medalla> medallas;
    private Context context;

    public MedalsListAdapter (Context context){
        this.context = context;
        medallas = new ArrayList<>();
    }

    public ArrayList<Medalla> getMedallas() {
        return medallas;
    }

    public void setMedallas(ArrayList<Medalla> medallas) {
        this.medallas = medallas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.medal_image_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(this.context).load(medallas.get(position).getImgUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return this.medallas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public ViewHolder (View view){
            super(view);
            image = (ImageView)view.findViewById(R.id.medalimage);

        }

    }
}
