package com.example.prac15;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

private Context context;
private ArrayList<Anime> List;

    public ItemAdapter(Context context, ArrayList<Anime> list) {
        this.context = context;
        this.List = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Anime anime = List.get(position);
        holder.tv.setText(anime.getName());
        Picasso.with(context).load(anime.getImg()).into(holder.iv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CardActivity.class);
                intent.putExtra("id", anime.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView iv;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.item_tv);
            iv = itemView.findViewById(R.id.item_iv);
        }
    }
}
