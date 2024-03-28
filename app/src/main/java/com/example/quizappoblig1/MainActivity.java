package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Button galleryButton = findViewById(R.id.gallery);
        Button quizButton = findViewById(R.id.quiz);

        galleryButton.setOnClickListener(v -> {
            startActivity(new Intent(this, GalleryActivity.class));
        });
        quizButton.setOnClickListener(v -> {
            startActivity(new Intent(this, QuizActivity.class));
        });
    observerSetup();
    }
        private void observerSetup() {
            mainViewModel.getAllImageAndTexts().observe(this, imageAndTexts -> {
                if (imageAndTexts.isEmpty()) {
                    mainViewModel.insert(new ImageAndText("Golden Retriever", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gr)));
                    mainViewModel.insert(new ImageAndText("German Shepherd", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gs)));
                    mainViewModel.insert(new ImageAndText("Shiba Inu", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.shib)));
                }
            });
        }
}