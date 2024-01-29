package com.example.quizappoblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button galleryButton = findViewById(R.id.gallery);
        galleryButton.setOnClickListener(v -> {
            startActivity(new Intent(this, GalleryActivity.class));
        });
        Button quizButton = findViewById(R.id.quiz);
        quizButton.setOnClickListener(v -> {
            startActivity(new Intent(this, QuizActivity.class));
        });
    }
}