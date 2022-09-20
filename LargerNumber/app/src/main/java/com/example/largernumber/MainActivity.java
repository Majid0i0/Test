package com.example.largernumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final int GAME_LEVEL_COUNT=10;
    private final int LEFT_BUTTON=0;
    private final int RIGHT_BUTTON=1;
    private final int EQUAL_BUTTON=2;

    private Button leftBtn;
    private Button rightBtn;
    private Button equal;
    private TextView points;
    private TextView gameLevel;

    private int leftNumberInt;
    private int rightNumberInt;
    private int gameLevelInt=0;
    private int pointsInt=0;
    private boolean gameInProgress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        configureViews();

        CountDownTimer countDownTimer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long remainingTime) {
//                gameLevel.setText(getString(R.string.game_level,gameLevelInt,GAME_LEVEL_COUNT));
                gameLevel.setText(getString(R.string.remaining_time,(int)remainingTime/1000));

            }

            @Override
            public void onFinish() {
                gameInProgress=false;
                gameLevel.setText(getString(R.string.game_finished_text));
            }
        };

        countDownTimer.start();

        generateOneLevel();
        gameInProgress=true;
        generateOneLevel();
    }
    private void configureViews(){
        points.setText(getString(R.string.user_points,0));
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndContinueGame(LEFT_BUTTON);
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndContinueGame(RIGHT_BUTTON);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateAndContinueGame(EQUAL_BUTTON);
            }
        });
    }
    private void evaluateAndContinueGame(int whatPressed){
        if(gameInProgress==false){
            return;
        }
        evaluate(whatPressed);
//        points.setText(String.valueOf("Your points: "+pointsInt));
        points.setText(getString(R.string.user_points,pointsInt));
        generateOneLevel();
    }
    private void evaluate(int whatPressed){
        if(whatPressed==LEFT_BUTTON){
            if (rightNumberInt < leftNumberInt)
                pointsInt++;
        }
        else if (whatPressed==RIGHT_BUTTON){
            if (leftNumberInt < rightNumberInt)
                pointsInt++;
        }
        else if (whatPressed==EQUAL_BUTTON){
            if (leftNumberInt == rightNumberInt)
                pointsInt++;
        }
    }
    private void findViews(){
        leftBtn=(Button)findViewById(R.id.left_number);
        rightBtn=(Button)findViewById(R.id.right_number);
        equal=(Button) findViewById(R.id.equal_button);

        points=(TextView) findViewById(R.id.user_point);
        gameLevel=(TextView) findViewById(R.id.game_level);
    }
    private void generateOneLevel(){
        if (gameInProgress==false){
            return;
        }
        gameLevelInt++;
        if (gameLevelInt==GAME_LEVEL_COUNT){
            gameLevel.setText(getString(R.string.game_finished_text));
            return;
        }
        gameLevelInt++;
        gameLevel.setText(getString(R.string.game_level,gameLevelInt,GAME_LEVEL_COUNT));

        rightNumberInt=generateInt();
        leftNumberInt= generateInt();
        leftBtn.setText(String.valueOf(leftNumberInt));
        rightBtn.setText(String.valueOf(rightNumberInt));
    }
    private int generateInt(){
        Random random=new Random();
        int number=random.nextInt();
        if (number<0)
            number*= -1;

        number%=30;
        return number;
    }
}