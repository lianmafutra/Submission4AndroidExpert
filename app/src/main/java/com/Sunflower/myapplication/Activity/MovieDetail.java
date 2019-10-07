package com.Sunflower.myapplication.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.Sunflower.myapplication.DB.AppDatabase;
import com.Sunflower.myapplication.DB.MovieRoom;
import com.Sunflower.myapplication.Model.MovieResults;
import com.Sunflower.myapplication.Model.TvResults;
import com.Sunflower.myapplication.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_TYPE = "extra_type";

    @BindView(R.id.btn_fav)
    Button btn_fav;
    @BindView(R.id.icon_favorite_clicked)
    Button btn_fav_clik;
    @BindView(R.id.tv_title)
    TextView title;
    @BindView(R.id.tv_date)
    TextView date;
    @BindView(R.id.tv_desc)
    TextView desc;
    @BindView(R.id.img_poster)
    ImageView img;
    String imagePath;


    private AppDatabase db;
    MovieResults movie;
    TvResults tv;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        tv = getIntent().getParcelableExtra(EXTRA_TV);
        type = getIntent().getStringExtra(EXTRA_TYPE);
        if (type.equals("movie")) {
            title.setText(movie.getTitle());
            date.setText(movie.getRelease_date());
            getSupportActionBar().setTitle("Movie Detail");
            desc.setText(movie.getOverview());
            imagePath = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

        }
        if (type.equals("tv")) {
            title.setText(tv.getName());
            date.setText(tv.getFirst_air_date());
            getSupportActionBar().setTitle("Tv Show Detail");
            desc.setText(tv.getOverview());
            imagePath = "https://image.tmdb.org/t/p/w185" + tv.getPoster_path();

        }


        Glide.with(this)
                .load(imagePath)
                .apply(new RequestOptions()
                        .centerCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

    }

    @OnClick(R.id.btn_fav)
    void klik_fav() {
        favoritetrue();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "moviedb").build();

        if (type.equals("movie")) {
            MovieRoom m = new MovieRoom();
            m.setTitle(movie.getTitle());
            m.setRelease_date(movie.getRelease_date());
            m.setOverview(movie.getOverview());
            m.setPhoto(movie.getPhoto());
            m.setType("1"); // type movie
            insertData(m);
        }
        if (type.equals("tv")) {
            MovieRoom m = new MovieRoom();
            m.setTitle(tv.getName());
            m.setRelease_date(tv.getFirst_air_date());
            m.setOverview(tv.getOverview());
            m.setPhoto(tv.getPoster_path());
            m.setType("2"); // type tv
            insertData(m);
        }

    }

    void favoritetrue() {
        btn_fav.setVisibility(View.INVISIBLE);
        btn_fav_clik.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.icon_favorite_clicked)
    void klik() {
        Toast.makeText(this, "Movie already favorite list", Toast.LENGTH_SHORT).show();
    }

    private void insertData(final MovieRoom movie) {

        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.movieDao().insertmovie(movie);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {
                Toast.makeText(MovieDetail.this, "status row " + status, Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}