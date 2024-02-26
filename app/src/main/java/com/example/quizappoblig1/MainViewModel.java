package com.example.quizappoblig1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ImageAndTextRepository repository;
    private LiveData<List<ImageAndText>> allImageAndTexts;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ImageAndTextRepository(application);
        allImageAndTexts = repository.getAllImageAndTexts();

    }
    LiveData<List<ImageAndText>> getAllImageAndTexts() {
        return allImageAndTexts;
    }
    public void insert(ImageAndText imageAndText) {
        repository.insert(imageAndText);
    }
    public void insertDefaults() {
        repository.insertDefaults();
    }
}
