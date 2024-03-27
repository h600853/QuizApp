package com.example.quizappoblig1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

   private Button button;
   private Button button2;
   private Button button3;
    private ImageView imageView;
    private TextView points;
    private TextView round;
    private TextView answer;
    private MainViewModel mainViewModel;



    private LiveData<List<ImageAndText>> allImageAndTexts;
    private LiveData<ImageAndText> currentAnswer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_quiz_land);
        }else{
        setContentView(R.layout.activity_quiz);
        }

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        allImageAndTexts = mainViewModel.getAllImageAndTexts();
        currentAnswer = mainViewModel.getCurrentAnswer();
        init();

    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.option1);
        button2 = findViewById(R.id.option2);
        button3 = findViewById(R.id.option3);
        points = findViewById(R.id.points);
        round = findViewById(R.id.round);
        answer = findViewById(R.id.answer);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        observerSetup();
    }
    private void observerSetup() {
        mainViewModel.getPointsCounter().observe(this, currentPoints -> points.setText(String.valueOf(currentPoints)));
        mainViewModel.getRoundCounter().observe(this, rounds -> round.setText(String.valueOf(rounds)));
        mainViewModel.getAnswerText().observe(this, currentAnswer -> answer.setText(currentAnswer));
        allImageAndTexts.observe(this, content -> setupQuiz(content, currentAnswer.getValue()));
    }

    public void setupQuiz(List<ImageAndText> content, ImageAndText currentAnswer) {

        if (content.isEmpty()) return;


        imageView.setImageURI(currentAnswer.getImage());

        setButtonsTexts(content, currentAnswer);
        setButtonClickListeners(currentAnswer, content);
    }



    private void setButtonsTexts(List<ImageAndText> content, ImageAndText answer) {
        switch (mainViewModel.getRandomVal()) {
            case 1:
                button.setText(answer.getName());
                button2.setText(getRandomName(content));
                button3.setText(getRandomName(content));
                break;
            case 2:
                button2.setText(answer.getName());
                button.setText(getRandomName(content));
                button3.setText(getRandomName(content));
                break;
            case 3:
                button3.setText(answer.getName());
                button.setText(getRandomName(content));
                button2.setText(getRandomName(content));
                break;
        }
    }
    private ImageAndText getRandomAnswer(List<ImageAndText> content) {
        int random = (int) (Math.random() * content.size());
        return content.get(random);
    }
    private String getRandomName(List<ImageAndText> content) {

        return content.get(mainViewModel.getRandomName()).getName();
    }
    private void setButtonClickListeners(ImageAndText answer, List<ImageAndText> content) {
        button.setOnClickListener(v -> checkAnswer (answer, button, content));
        button2.setOnClickListener(v -> checkAnswer(answer, button2, content));
        button3.setOnClickListener(v -> checkAnswer(answer, button3, content));
    }
    private void checkAnswer(ImageAndText answer, Button button, List<ImageAndText> content) {

            if (button.getText().equals(answer.getName())) {
                mainViewModel.incrementPointsCounter();
                mainViewModel.setAnswerText("Correct!");
            } else {
                mainViewModel.setAnswerText("The correct answer was: " + answer.getName());
            }
            newRound(content);

    }
    private void newRound(List<ImageAndText> content) {
        mainViewModel.incrementRoundCounter();
        ImageAndText newAnswer = getRandomAnswer(content);
        mainViewModel.setRandomVal((int) (Math.random() * 3) + 1);
        mainViewModel.setRandomName((int) (Math.random() * content.size()));
        mainViewModel.setCurrentAnswer(newAnswer);
        setupQuiz(content, newAnswer);
    }


}
