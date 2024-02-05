package com.example.quizappoblig1;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Content extends Application {

    ArrayList<ImageAndText> imagesAndTexts;
    public Content() {
        imagesAndTexts = new ArrayList<>(Arrays.asList(
                new ImageAndText("Golden Retriever", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gr)),
                new ImageAndText("German Shepherd", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gs)),
                new ImageAndText( "Shiba Inu", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.shib))
        ));
    }

    public ArrayList<ImageAndText> getContent() {
        return imagesAndTexts;
    }
    public void addContent(ImageAndText content) {
        imagesAndTexts.add(content);
    }
}
