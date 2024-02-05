package com.example.quizappoblig1;

import static android.os.SystemClock.sleep;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private Content content;
   private Button button;
   private Button button2;
   private Button button3;
    private ImageView imageView;
    private TextView points;
    int pointsCounter = 0;
    int roundCounter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.option1);
        button2 = findViewById(R.id.option2);
        button3 = findViewById(R.id.option3);
        points = findViewById(R.id.points);
        content = (Content) getApplication();
        getRandomQuestion();


    }
    public void getRandomQuestion() {
        int random = (int) (Math.random() * content.getContent().size());
        ImageAndText answer = content.getContent().get(random);
        imageView.setImageURI(answer.getImage());

        int correctButton = (int) (Math.random() * 3) + 1;

        switch (correctButton) {
            case 1:
                button.setText(answer.getName());
                button2.setText(getRandomName());
                button3.setText(getRandomName());
                break;
            case 2:
                button2.setText(answer.getName());
                button.setText(getRandomName());
                button3.setText(getRandomName());
                break;
            case 3:
                button3.setText(answer.getName());
                button.setText(getRandomName());
                button2.setText(getRandomName());
                break;
        }

        setButtonClickListeners(answer, button);
        setButtonClickListeners(answer, button2);
        setButtonClickListeners(answer, button3);
    }

    private String getRandomName() {
        return content.getContent().get((int) (Math.random() * content.getContent().size())).getName();
    }

    private void setButtonClickListeners(ImageAndText answer, Button button) {
        button.setOnClickListener(v -> {
            if (button.getText().equals(answer.getName())) {
                pointsCounter++;
            }
            newRound();
        });
    }
    private void newRound() {
        roundCounter++;
        points.setText(pointsCounter + "/" + roundCounter);
        getRandomQuestion();
    }


}
