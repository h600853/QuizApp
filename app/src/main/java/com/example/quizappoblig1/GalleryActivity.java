package com.example.quizappoblig1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

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
    private Content content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        content = (Content) getApplication();

        setupActivityResultLauncher();
        setupView();
        updateView();
        setupButtons();
        observerSetup();
    }
    private void observerSetup() {
        mainViewModel.getAllImageAndTexts().observe(this, imageAndTexts -> {
            imageAndTexts.forEach(imageAndText -> {
                if (!content.getContent().contains(imageAndText)) {
                    content.getContent().add(imageAndText);
                    updateView();
                }
            });
        });
    }

    private void setupView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    }
    private void updateView() {
        imageAdapter = new ImageAdapter(this, content.getContent());
        recyclerView.setAdapter(imageAdapter);
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
        List<ImageAndText> imageList = content.getContent();

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

}
