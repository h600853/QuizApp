package com.example.quizappoblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
   private ActivityResultLauncher<Intent> activityResultLauncher;
    private final int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;

    ArrayList<Uri> imageList = new ArrayList<>(Arrays.asList(
            Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gr),
            Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gs),
            Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.shib)
    ));

    ArrayList<String> nameList = new ArrayList<>(Arrays.asList(
            "Golden Retriever", "German Shepherd", "Shiba Inu"
    ));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

       activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();

                        if (data != null) {
                            Uri uri = Uri.parse(data.getStringExtra("imageUri"));
                            getContentResolver().takePersistableUriPermission(uri, flag);
                            String name = data.getStringExtra("inputText");
                            imageList.add(uri);
                            nameList.add(name);
                            RecyclerView recyclerView = findViewById(R.id.recyclerView);
                            ImageAdapter imageAdapter = new ImageAdapter(this, imageList, nameList);
                            recyclerView.setAdapter(imageAdapter);

                        }

                    }
                });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2)); // or GridLayoutManager, StaggeredGridLayoutManager, etc.

        ImageAdapter imageAdapter = new ImageAdapter(this, imageList, nameList);
        recyclerView.setAdapter(imageAdapter);
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);

        add.setOnClickListener(v -> {
        Intent intent = new Intent(GalleryActivity.this, ImageActivity.class);
        activityResultLauncher.launch(intent);
        });
        delete.setOnClickListener(v -> {
            if (!imageList.isEmpty() && !nameList.isEmpty()) {
            imageList.remove(imageList.size()-1);
            nameList.remove(nameList.size()-1);
            recyclerView.setAdapter(imageAdapter);
            }
        });

    }
}
