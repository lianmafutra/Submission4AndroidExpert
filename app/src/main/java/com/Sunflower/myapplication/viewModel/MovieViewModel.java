package com.Sunflower.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Sunflower.myapplication.Model.MovieResults;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<MovieResults>> listMovies = new MutableLiveData<>();

    public void setMovies(final String movies) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MovieResults> listItems = new ArrayList<>();

        String url_movie= "https://api.themoviedb.org/3/discover/movie?api_key=d1fc8e1957ff243040727607da2fa44b&language=en-US";


        client.get(url_movie, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        MovieResults movieItems = new MovieResults(weather);
                        listItems.add(movieItems);
                    }
                    listMovies.postValue(listItems);
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }
    public LiveData<ArrayList<MovieResults>> getMovies() {
        return listMovies;
    }
}
