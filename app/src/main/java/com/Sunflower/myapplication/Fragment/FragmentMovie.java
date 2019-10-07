package com.Sunflower.myapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.Sunflower.myapplication.Adapter.AdapterMovie;
import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.R;
import com.Sunflower.myapplication.viewModel.MovieViewModel;

import java.util.ArrayList;


public class FragmentMovie extends Fragment {

    private AdapterMovie adapter;

    private ProgressBar progressBar;
    private MovieViewModel moviesViewModel;



    public FragmentMovie() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new AdapterMovie(getContext());
        View view = inflater.inflate(R.layout.fragment_fragment_movie,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        moviesViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies("EXTRA_MOVIE");

        showLoading(true);


        return view;
    }







    private Observer<ArrayList<MovieResults>> getMovie = new Observer<ArrayList<MovieResults>>() {
        @Override
        public void onChanged(ArrayList<MovieResults> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }

            showLoading(false);

        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}