package com.example.quizappoblig1;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ImageAndTextRepository repository;
    private LiveData<List<ImageAndText>> databaseData;
    private MutableLiveData<Integer> pointsCounter = new MutableLiveData<>(0);
    private MutableLiveData<Integer> roundCounter = new MutableLiveData<>(0);
    private MutableLiveData<String> answer = new MutableLiveData<>("");
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
    public LiveData<Integer> getPointsCounter() {
        return pointsCounter;
    }

    public LiveData<Integer> getRoundCounter() {
        return roundCounter;
    }

    public void incrementPointsCounter() {
        pointsCounter.setValue(pointsCounter.getValue() + 1);
    }

    public void incrementRoundCounter() {
        roundCounter.setValue(roundCounter.getValue() + 1);
    }
    public LiveData<String> getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer.setValue(answer);
    }

}
