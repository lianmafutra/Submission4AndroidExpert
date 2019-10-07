package com.Sunflower.myapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.Sunflower.myapplication.Activity.MovieDetail;
import com.Sunflower.myapplication.DB.AppDatabase;
import com.Sunflower.myapplication.DB.MovieRoom;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AdapterFavorit extends RecyclerView.Adapter<AdapterFavorit.MovieViewHolder>{
    private Context context;
    private AppDatabase db;
    private ArrayList<MovieRoom> mData;

    public AdapterFavorit(ArrayList<MovieRoom> movies, Context ctx){

        mData = movies;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "moviedb").allowMainThreadQueries().build();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder,final int position) {
        movieViewHolder.bind(mData.get(position));
        final MovieRoom movList = mData.get(position);
        movieViewHolder.btn_fav.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onDeteleMovie(position);
                Toast.makeText(context, movList.getTitle()+", Delete from favorite: ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPhoto;
        TextView title,date,desc;
        public  Button btn_fav;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_movie);
            date = itemView.findViewById(R.id.txt_release_date);
            desc = itemView.findViewById(R.id.txt_desc_movie);
            imgPhoto = itemView.findViewById(R.id.img_movie);
            btn_fav = itemView.findViewById(R.id.btn_fav);
            itemView.setOnClickListener(this);
        }
        void bind(MovieRoom movies) {
            String img_path = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();

            title.setText(movies.getTitle());
            date.setText(movies.getRelease_date());
            desc.setText(movies.getOverview());
            Glide.with(itemView.getContext())
                    .load(img_path)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);

        }

        @Override
        public void onClick(View v) {
        }
    }

    private void onDeteleMovie(int position){
        db.movieDao().deleteMovie(mData.get(position));
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, mData.size());
    }




}
