package com.Sunflower.myapplication.Adapter;


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
import com.Sunflower.myapplication.Model.TvResults;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterTv extends RecyclerView.Adapter<AdapterTv.MovieViewHolder> {

    private ArrayList<TvResults> mData = new ArrayList<>();

    public void setData(ArrayList<TvResults> items) {
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

        void bind(TvResults movies) {
            String img_path = "https://image.tmdb.org/t/p/w185" + movies.getPoster_path();

            title.setText(movies.getName());
            date.setText(movies.getFirst_air_date());
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
            TvResults tv = mData.get(position);
            Intent moveWithObjectIntent = new Intent(itemView.getContext(), MovieDetail.class);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_TV, tv);
            moveWithObjectIntent.putExtra(MovieDetail.EXTRA_TYPE, "tv");
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}
