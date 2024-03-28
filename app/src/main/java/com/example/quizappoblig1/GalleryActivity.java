package com.example.quizappoblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
   private ActivityResultLauncher<Intent> activityResultLauncher;
   private MainViewModel mainViewModel;
    private boolean sorted = true;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private Button sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);


        setupActivityResultLauncher();
        setupView();
        setupButtons();
        observerSetup();
    }

    private void observerSetup() {
        mainViewModel.getAllImageAndTexts().observe(this, imageAndTexts -> {
            if (imageAndTexts.isEmpty()) {
                mainViewModel.insert(new ImageAndText("Golden Retriever", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gr)));
                mainViewModel.insert(new ImageAndText("German Shepherd", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.gs)));
                mainViewModel.insert(new ImageAndText("Shiba Inu", Uri.parse("android.resource://com.example.quizappoblig1/" + R.drawable.shib)));
            }
        imageAdapter = new ImageAdapter(this, mainViewModel);
        imageAdapter.setList(imageAndTexts);
        recyclerView.setAdapter(imageAdapter);
        });
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }


    private void setupActivityResultLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        handleActivityResult(result.getData());

                    }
                });
    }

    private void handleActivityResult(Intent data) {
        if (data != null) {
            Uri uri = Uri.parse(data.getStringExtra("imageUri"));
            int flag = Intent.FLAG_GRANT_READ_URI_PERMISSION;
            getContentResolver().takePersistableUriPermission(uri, flag);
            String name = data.getStringExtra("inputText");
            mainViewModel.insert(new ImageAndText(name, uri));

        }
    }

    private void setupButtons() {
        Button add = findViewById(R.id.add);
        sort = findViewById(R.id.sort);

        add.setOnClickListener(v -> launchImageActivity());

        sort.setOnClickListener(v -> sortlogic());
    }


    private void sortlogic() {
        mainViewModel.getAllImageAndTexts().observe(this, imageList -> {

        if (!imageList.isEmpty()) {
            //sort a to z
            if(sorted) {
                imageList.sort(Comparator.comparing(ImageAndText::getName));
                sort.setText("Sort Z-A");
            } else {
                imageList.sort(Comparator.comparing(ImageAndText::getName).reversed());
                sort.setText("Sort A-Z");
            }
            }
        sorted = !sorted;
        recyclerView.setAdapter(imageAdapter);
        });
    }



    private void launchImageActivity() {
        Intent intent = new Intent(GalleryActivity.this, ImageActivity.class);
        activityResultLauncher.launch(intent);
    }

}
