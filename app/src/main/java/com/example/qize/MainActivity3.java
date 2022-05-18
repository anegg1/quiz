package com.example.qize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    Button startBtn;
    TextView TimeTextView;
    TextView ScoreTextView;
    TextView AlertTextView;
    TextView QuestionTextView;
    TextView FinalScoreTextView;
    Button button0;
    Button button1;
    Button button2;
    int maxtime = 30000;
    Button button3;
    CountDownTimer countDownTimer;
    ConstraintLayout constraintLayout;
    ConstraintLayout lastLayout;
    Button buttonPlayAgain;


    Random random =new Random();
    int a;
    int b;
    int indexOfCorrectAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int totalQuestions = 0;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn=(Button)findViewById(R.id.btnStart);

        TimeTextView = findViewById(R.id.TimeTextView);
        ScoreTextView = findViewById(R.id.ScoreTextView);
        FinalScoreTextView=findViewById(R.id.FinalscoreTextView);

        QuestionTextView =findViewById(R.id.QuestionTextView);
        ImageButton btnback = findViewById(R.id.imageButton);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(MainActivity3.this, MainActivity2.class);
                startActivity(intentback);
            }
        });


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        buttonPlayAgain=findViewById(R.id.buttonPlayAgain);

        constraintLayout=findViewById(R.id.quizUi);
        lastLayout=findViewById(R.id.lastUi);

        lastLayout.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.INVISIBLE);


    }

    @SuppressLint("SetTextI18n")
    public void NextQuestion(){
        a = random.nextInt(10);
        b = random.nextInt(10);
        QuestionTextView.setText(a+"+"+b);

        indexOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        for(int i = 0; i<4; i++){

            if(indexOfCorrectAnswer == i){
                answers.add(a+b);
            }else {
                int wrongAnswer = random.nextInt(20);
                while(wrongAnswer==a+b){

                    wrongAnswer = random.nextInt(20);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void optionSelect(View view){
        totalQuestions++;
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            points++;



        }else {

        }

        ScoreTextView.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions));

        NextQuestion();
    }

    public void playAgain(View view){
        points=0;
        totalQuestions=0;
        ScoreTextView.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions));
        countDownTimer.start();
        lastLayout.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);

    }





    public void start(View view) {
        startBtn.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(View.VISIBLE);
        NextQuestion();
        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
                if (millisUntilFinished == 10){
                    millisUntilFinished = millisUntilFinished + 3;
                }
            }

            @Override
            public void onFinish() {
                TimeTextView.setText("Time Up!");
                FinalScoreTextView.setText(Integer.toString(points)+"/"+Integer.toString(totalQuestions));
                constraintLayout.setVisibility(View.INVISIBLE);
                lastLayout.setVisibility(View.VISIBLE);

            }
        }.start();


    }
}