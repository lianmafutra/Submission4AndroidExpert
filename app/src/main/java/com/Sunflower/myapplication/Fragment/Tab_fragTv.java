package com.Sunflower.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.Sunflower.myapplication.Adapter.AdapterFavorit;
import com.Sunflower.myapplication.DB.AppDatabase;
import com.Sunflower.myapplication.DB.MovieRoom;
import com.Sunflower.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Tab_fragTv extends Fragment {


    private AppDatabase db;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MovieRoom> movielistdb;

    public Tab_fragTv() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_frag_tv, container, false);
        movielistdb = new ArrayList<>();
        db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "moviedb").allowMainThreadQueries().build();
        rvView = view.findViewById(R.id.rv_tv_fav);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        rvView.setLayoutManager(layoutManager);

        movielistdb.addAll(Arrays.asList(db.movieDao().select_tv_favorit()));

        adapter = new AdapterFavorit(movielistdb, getContext());
        rvView.setAdapter(adapter);
        return view;
    }

}
