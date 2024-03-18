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
    private MainViewModel mainViewModel;


    private LiveData<List<ImageAndText>> allImageAndTexts;

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
        init();

    }

    private void init() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.option1);
        button2 = findViewById(R.id.option2);
        button3 = findViewById(R.id.option3);
        points = findViewById(R.id.points);
        round = findViewById(R.id.round);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        observerSetup();
    }
    private void observerSetup() {
        mainViewModel.getPointsCounter().observe(this, currentPoints -> points.setText(String.valueOf(currentPoints)));
        mainViewModel.getRoundCounter().observe(this, rounds -> round.setText(String.valueOf(rounds)));

        allImageAndTexts.observe(this, this::getRandomQuestion);
    }

    public void getRandomQuestion(List<ImageAndText> content) {

        if (content.isEmpty()) return;

        int random = (int) (Math.random() * content.size());
        ImageAndText answer = content.get(random);
        imageView.setImageURI(answer.getImage());

        int correctButton = (int) (Math.random() * 3) + 1;

        switch (correctButton) {
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

        setButtonClickListeners(answer, button, content);
        setButtonClickListeners(answer, button2, content);
        setButtonClickListeners(answer, button3, content);
    }

    private String getRandomName(List<ImageAndText> content) {

        return content.get((int) (Math.random() * content.size())).getName();
    }

    private void setButtonClickListeners(ImageAndText answer, Button button, List<ImageAndText> content) {
        TextView answerText = findViewById(R.id.answer);
        button.setOnClickListener(v -> {
            if (button.getText().equals(answer.getName())) {
                mainViewModel.incrementPointsCounter();
                answerText.setText("Correct!");
            } else {
                answerText.setText("Correct answer was: " + answer.getName());
            }
            newRound(content);
        });
    }
    private void newRound(List<ImageAndText> content) {
        mainViewModel.incrementRoundCounter();
      //  points.setText(pointsCounter + "/" + roundCounter);
        getRandomQuestion(content);
    }


}
