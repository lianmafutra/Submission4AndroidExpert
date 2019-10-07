package com.Sunflower.myapplication.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.Sunflower.myapplication.Model.TvResults;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class TvViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TvResults>> listtv = new MutableLiveData<>();

    public void setTv(final String tv) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvResults> listItems = new ArrayList<>();

        String url_tv= "https://api.themoviedb.org/3/discover/tv?api_key=d1fc8e1957ff243040727607da2fa44b&language=en-US";


        client.get(url_tv, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject weather = list.getJSONObject(i);
                        TvResults tvItems = new TvResults(weather);
                        listItems.add(tvItems);
                    }
                    listtv.postValue(listItems);
                } catch (Exception e) {

                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public LiveData<ArrayList<TvResults>> getTv() {
        return listtv;
    }
}
