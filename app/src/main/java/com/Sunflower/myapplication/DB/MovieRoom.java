package com.Sunflower.myapplication.DB;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbmovie")
public class MovieRoom implements Serializable, Parcelable {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @NonNull
    public String getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(@NonNull String idmovie) {
        this.idmovie = idmovie;
    }

    @PrimaryKey()
    @NonNull
    private String idmovie;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "overview")
    public String overview;

    @ColumnInfo(name = "release_date")
    public String release_date;

    @ColumnInfo(name = "photo")
    public String photo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ColumnInfo(name = "type")
    public String type;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idmovie);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.photo);
        dest.writeString(this.type);
    }

    public MovieRoom() {
    }

    protected MovieRoom(Parcel in) {
        this.idmovie = in.readString();
        this.title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.photo = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<MovieRoom> CREATOR = new Parcelable.Creator<MovieRoom>() {
        @Override
        public MovieRoom createFromParcel(Parcel source) {
            return new MovieRoom(source);
        }

        @Override
        public MovieRoom[] newArray(int size) {
            return new MovieRoom[size];
        }
    };
}
