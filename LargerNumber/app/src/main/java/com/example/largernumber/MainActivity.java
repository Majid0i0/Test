package com.example.largernumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startGame;
    Button showBestScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();
    }

    private void findViews(){
        startGame=(Button) findViewById(R.id.start_game);
        showBestScore=(Button) findViewById(R.id.show_best_score);
    }
    private void configure(){



        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment=new GameFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,gameFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        showBestScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BestScoreFragment bestScoreFragment=new BestScoreFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,bestScoreFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}