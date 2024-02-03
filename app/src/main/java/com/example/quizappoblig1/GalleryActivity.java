package com.example.quizappoblig1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GalleryActivity extends AppCompatActivity {
   //get images from drawable folder
    int[] images = {
            R.drawable.gr,
            R.drawable.gs,
            R.drawable.shib,
   };
    String[] names = {"Golden Retriever", "German Shepherd", "Shiba Inu"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2)); // or GridLayoutManager, StaggeredGridLayoutManager, etc.

        ImageAdapter imageAdapter = new ImageAdapter(this, images, names);
        recyclerView.setAdapter(imageAdapter);
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);

        add.setOnClickListener(v -> {
            startActivity(new Intent(GalleryActivity.this, ImageActivity.class));
        });

    }
}
