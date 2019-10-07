package com.Sunflower.myapplication.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Sunflower.myapplication.Activity.MovieDetail;
import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MovieViewHolder> {
    private Context context;

    public AdapterMovie(Context context) {
        this.context = context;
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(MovieResults data);
    }

    private ArrayList<MovieResults> mData = new ArrayList<>();

    public void setData(ArrayList<MovieResults> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
        final MovieResults movList = mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView title, date, desc;
        public Button btn_fav;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_movie);
            date = itemView.findViewById(R.id.txt_release_date);
            desc = itemView.findViewById(R.id.txt_desc_movie);
            imgPhoto = itemView.findViewById(R.id.img_movie);
            btn_fav = itemView.findViewById(R.id.btn_fav);
            btn_fav.setVisibility(View.GONE);
            itemView.setOnClickListener(this);


        }

        void bind(MovieResults movies) {
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
            int position = getAdapterPosition();
            MovieResults movie = mData.get(position);
            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetail.class);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_MOVIE, movie);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_TYPE, "movie");
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
