package com.example.quizappoblig1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        FloatingActionButton add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);

        add.setOnClickListener(v -> {
            startActivity(new Intent(GalleryActivity.this, ImageActivity.class));
        });

    }
}
