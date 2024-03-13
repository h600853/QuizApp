package com.example.quizappoblig1;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ImageAndTextRepository repository;
    private LiveData<List<ImageAndText>> databaseData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ImageAndTextRepository(application);
         databaseData = repository.getAllImageAndTexts();

    }
    LiveData<List<ImageAndText>> getAllImageAndTexts() {
        return databaseData;
    }
    public void insert(ImageAndText imageAndText) {
        repository.insert(imageAndText);
    }
    public void delete(ImageAndText imageAndText) {
        repository.delete(imageAndText);
    }
    public void deleteAll() {
        repository.deleteAll();
    }

}
