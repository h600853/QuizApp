package com.example.quizappoblig1;

import android.net.Uri;

public class ImageAndText {

    private String name;
    private Uri image;

    public ImageAndText(String name, Uri image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public Uri getImage() {
        return image;
    }
}
