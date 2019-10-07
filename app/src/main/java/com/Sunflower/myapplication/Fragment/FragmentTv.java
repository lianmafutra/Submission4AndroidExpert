package com.Sunflower.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Sunflower.myapplication.Adapter.AdapterTv;
import com.Sunflower.myapplication.Model.TvResults;
import com.Sunflower.myapplication.R;
import com.Sunflower.myapplication.viewModel.TvViewModel;

import java.util.ArrayList;


public class FragmentTv extends Fragment {

    private AdapterTv adapter;

    private ProgressBar progressBar;
    private TvViewModel tvViewModel;


    public FragmentTv() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new AdapterTv();
        View view = inflater.inflate(R.layout.fragment_fragment_tv, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        tvViewModel.getTv().observe(this, getTv);
        tvViewModel.setTv("EXTRA_TV");

        showLoading(true);

        return view;
    }

    private Observer<ArrayList<TvResults>> getTv = new Observer<ArrayList<TvResults>>() {
        @Override
        public void onChanged(ArrayList<TvResults> tv) {
            if (tv != null) {
                adapter.setData(tv);
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