package com.dev.myapp01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList id, name, innings, score, wickets;


    CustomAdapter(Activity activity, Context context,
                  ArrayList id,
                  ArrayList name,
                  ArrayList innings,
                  ArrayList score,
                  ArrayList wickets
                  //ArrayList image
    ){

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.name = name;
        this.innings = innings;
        this.score = score;
        this.wickets = wickets;
        //this.image = image;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.name_txt.setText(String.valueOf(name.get(position)));
        holder.innings_txt.setText(String.valueOf(innings.get(position)));
        holder.score_txt.setText(String.valueOf(score.get(position)));
        holder.wickets_txt.setText(String.valueOf(wickets.get(position)));
        //holder.image_txt.setImageResource((Integer) image.get(position));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePlayer.class);
                intent.putExtra("id", String.valueOf(id.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("innings", String.valueOf(innings.get(position)));
                intent.putExtra("score", String.valueOf(score.get(position)));
                intent.putExtra("wickets", String.valueOf(wickets.get(position)));
                //intent.putExtra("image", String.valueOf(image.get(position)));
                activity.startActivityForResult(intent, 1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt;
        TextView name_txt;
        TextView innings_txt;
        TextView score_txt;
        TextView wickets_txt;
        //ImageView image_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.txtId);
            name_txt = itemView.findViewById(R.id.txtName);
            innings_txt = itemView.findViewById(R.id.txtInnings);
            score_txt = itemView.findViewById(R.id.txtScore);
            wickets_txt = itemView.findViewById(R.id.txtWickets);
            //image_txt = itemView.findViewById(R.id.imgProf);
            //image_txt = (ImageView) itemView.findViewById(R.id.imgProf);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
