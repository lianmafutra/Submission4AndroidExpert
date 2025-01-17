package com.Sunflower.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class MovieResults implements Parcelable {
    private Double vote_average;
    private String title;
    private Double popularity;
    private String original_language;
    private String overview;
    private String release_date;
    private String photo;
    private String banner;
    private String vote_count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Creator<MovieResults> getCREATOR() {
        return CREATOR;
    }

    private String id;


    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MovieResults(JSONObject object) {
        try {
            String vote_count = object.getString("vote_count");
            String id = object.getString("id");
            Double vote_average = object.getDouble("vote_average");
            String title = object.getString("title");
            Double popularity = object.getDouble("popularity");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");

            this.vote_count = vote_count;
            this.id = id;
            this.vote_average = vote_average;
            this.title = title;
            this.popularity = popularity;
            this.original_language = original_language;
            this.overview = overview;
            this.release_date = release_date;
            this.photo = poster_path;
            this.banner = backdrop_path;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.vote_average);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.photo);
        dest.writeString(this.banner);
        dest.writeString(this.vote_count);
        dest.writeString(this.id);
    }

    protected MovieResults(Parcel in) {
        this.vote_average = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.original_language = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.photo = in.readString();
        this.banner = in.readString();
        this.vote_count = in.readString();
        this.id = in.readString();
    }

    public static final Creator<MovieResults> CREATOR = new Creator<MovieResults>() {
        @Override
        public MovieResults createFromParcel(Parcel source) {
            return new MovieResults(source);
        }

        @Override
        public MovieResults[] newArray(int size) {
            return new MovieResults[size];
        }
    };
}
