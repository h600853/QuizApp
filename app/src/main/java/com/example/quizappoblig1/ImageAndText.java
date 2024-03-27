package com.example.quizappoblig1;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ImageAndText {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private final String name;
    private final Uri image;

    public ImageAndText(String name, Uri image) {
        this.name = name;
        this.image = image;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Uri getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }
}
