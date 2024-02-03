package com.example.quizappoblig1;

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
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    Uri imageUri;
    TextInputEditText textInputEditText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Button chooseButton = findViewById(R.id.choose);
        Button submitButton = findViewById(R.id.submit);
        ImageView imageView = findViewById(R.id.baseimage);
        textInputEditText = findViewById(R.id.textInputEditText);

        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                imageView.setImageURI(uri);
                imageUri = uri;
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });

        chooseButton.setOnClickListener(v -> {
            pickImage();
        });
        submitButton.setOnClickListener(v -> {
            sendImageAndText();
        });

    }

    private void sendImageAndText() {
        ImageView imageView = findViewById(R.id.baseimage);
        Intent intent = new Intent(this, GalleryActivity.class);
        if (imageUri != null) {
            intent.putExtra("imageUri", imageUri.toString());
        }
        Editable inputText = textInputEditText.getText();
        if (inputText != null) {
            intent.putExtra("inputText", inputText.toString());
        }
        finish();
        startActivity(intent);
    }

    private void  pickImage() {
        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(ActivityResultContracts.PickVisualMedia.ImageAndVideo.INSTANCE)
                .build());
    }
}
