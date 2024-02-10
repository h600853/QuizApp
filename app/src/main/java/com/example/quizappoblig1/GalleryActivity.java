package com.example.quizappoblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;

public class GalleryActivity extends AppCompatActivity {
   private ActivityResultLauncher<Intent> activityResultLauncher;
   private Content content;
    private boolean sorted = true;
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private Button sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        content = (Content) getApplication();

        setupActivityResultLauncher();
        setupView();
        setupImageAdapter();
        setupButtons();
    }

    private void setupImageAdapter() {
        imageAdapter = new ImageAdapter(this, content.getContent());
        recyclerView.setAdapter(imageAdapter);
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
            content.addContent(new ImageAndText(name, uri));
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            ImageAdapter imageAdapter = new ImageAdapter(this, content.getContent());
            recyclerView.setAdapter(imageAdapter);
        }
    }

    private void setupButtons() {
        Button add = findViewById(R.id.add);
        sort = findViewById(R.id.sort);

        add.setOnClickListener(v -> launchImageActivity());

        sort.setOnClickListener(v -> sortlogic());
    }

    private void sortlogic() {
        ArrayList<ImageAndText> imageList = content.getContent();

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
    }

    private void launchImageActivity() {
        Intent intent = new Intent(GalleryActivity.this, ImageActivity.class);
        activityResultLauncher.launch(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<ImageAndText> existingContent = content.getContent();

        if (!existingContent.isEmpty()) {
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            ImageAdapter imageAdapter = new ImageAdapter(this, existingContent);
            recyclerView.setAdapter(imageAdapter);
        }

    }
}
