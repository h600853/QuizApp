package com.example.quizappoblig1;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ImageAndTextRepository repository;
    private LiveData<List<ImageAndText>> databaseData;
    private MutableLiveData<Integer> pointsCounter = new MutableLiveData<>(0);
    private MutableLiveData<Integer> roundCounter = new MutableLiveData<>(0);
    private MutableLiveData<String> answerText = new MutableLiveData<>("");
    private MutableLiveData<ImageAndText> currentAnswer = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new ImageAndTextRepository(application);
         databaseData = repository.getAllImageAndTexts();
        databaseData.observeForever(imageAndTexts -> {
            if (imageAndTexts != null && !imageAndTexts.isEmpty()) {
                setCurrentAnswer(getRandomAnswer(imageAndTexts));
            }
        });
    }

    public void setCurrentAnswer(ImageAndText randomAnswer) {
        this.currentAnswer.setValue(randomAnswer);
    }
    public LiveData<ImageAndText> getCurrentAnswer() {
        return currentAnswer;
    }

    private ImageAndText getRandomAnswer(List<ImageAndText> content) {
        int random = (int) (Math.random() * content.size());
        return content.get(random);
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
    public LiveData<String> getAnswerText() {
        return answerText;
    }
    public void setAnswerText(String answer) {
        this.answerText.setValue(answer);
    }
}
