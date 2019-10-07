package com.Sunflower.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MovieDetailFavorit extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_TV = "extra_tv";
    public static final String EXTRA_TYPE = "extra_type";

    @BindView(R.id.btn_fav)
    ImageButton btn_fav;
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
    MovieRoom movie;
    String type;
    String id_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail_favorit);
        ButterKnife.bind(this);
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        type = getIntent().getStringExtra(EXTRA_TYPE);


        if (movie.getType().equals("1")) {
            id_movie= movie.getIdmovie();
            title.setText(movie.getTitle());
            date.setText(movie.getRelease_date());
            getSupportActionBar().setTitle("Movie Detail");
            desc.setText(movie.getOverview());
            imagePath = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

        }
        if (movie.getType().equals("2")) {
            id_movie= movie.getIdmovie();
            title.setText(movie.getTitle());
            date.setText(movie.getRelease_date());
            getSupportActionBar().setTitle("Tv Show Detail");
            desc.setText(movie.getOverview());
            imagePath = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "moviedb").build();

        if (movie.getType().equals("1")) {
            MovieRoom m = new MovieRoom();
            m.setIdmovie(id_movie);
            m.setTitle(movie.getTitle());
            m.setRelease_date(movie.getRelease_date());
            m.setOverview(movie.getOverview());
            m.setPhoto(movie.getPhoto());
            m.setType("1"); // type movie
            insertData(m);
        }
        if (movie.getType().equals("2")) {
            MovieRoom m = new MovieRoom();
            m.setIdmovie(id_movie);
            m.setTitle(movie.getTitle());
            m.setRelease_date(movie.getRelease_date());
            m.setOverview(movie.getOverview());
            m.setPhoto(movie.getPhoto());
            m.setType("2"); // type tv
            insertData(m);
        }

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
                Toast.makeText(MovieDetailFavorit.this, "Succes add to favorit list", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

}