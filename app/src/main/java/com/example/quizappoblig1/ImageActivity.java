package com.example.quizappoblig1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ImageActivity extends AppCompatActivity {
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private Uri imageUri;
    private TextInputEditText textInputEditText;
    private Button chooseButton;
    private Button submitButton;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        init();

        setupActivityForResult();

        setupOnClickListeners();

    }
    private void init() {
        chooseButton = findViewById(R.id.choose);
        submitButton = findViewById(R.id.submit);
        imageView = findViewById(R.id.baseimage);
        textInputEditText = findViewById(R.id.textInputEditText);
    }

    private void setupActivityForResult() {
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {

            if (uri != null) {
                imageView.setImageURI(uri);
                imageUri = uri;
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
    }

    private void setupOnClickListeners() {
        chooseButton.setOnClickListener(v -> pickImage());
        submitButton.setOnClickListener(v -> sendImageAndText());
    }

    private void sendImageAndText() {
        Intent intent = new Intent();

        if (imageUri != null) {
            intent.putExtra("imageUri", imageUri.toString());
        }
        Editable inputText = textInputEditText.getText();
        if (inputText != null) {
            intent.putExtra("inputText", inputText.toString());
        }
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void  pickImage() {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE)
                .build());
    }
}
