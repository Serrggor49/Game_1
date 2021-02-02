package com.example.game_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "myLogs";
    private int PICTURE_SIZE = 100; // размер цели
    private int maxHeightGameZone;
    private int maxWidthGameZone;
    private int hitCount = 0;  // количество попаданий
    private int GAME_TIME = 30;
    TextView timerTextView;
    TextView counterTextView;
    ImageButton buttonTarget;
    Button buttonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }


    private void init() {

        showPic();
        buttonStart = findViewById(R.id.button_start_id);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSizeGameZone();
                Log.d(TAG, "высота экрана " + maxHeightGameZone);
                Log.d(TAG, "ширина экрана " + maxWidthGameZone);
                buttonStart.setVisibility(View.GONE);
                //showPic();
                startGame();

            }
        });

        buttonTarget = findViewById(R.id.target_button_id);
        buttonTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitCount++;
                buttonTarget.setVisibility(View.GONE);
                //startGame();
            }
        });

        timerTextView = findViewById(R.id.timer_textview_id);
        counterTextView = findViewById(R.id.counter_textview_id);

    }

    private void showPic() {
        ImageButton imageButton = findViewById(R.id.target_button_id);
        imageButton.setX(maxWidthGameZone);
        imageButton.setY(maxHeightGameZone);
        imageButton.refreshDrawableState();
    }


    private void startGame() {


//            try {
//                wait(1000);
//            } catch (Exception e) {
//////            }


        // while (GAME_TIME > 0) {
        CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + "");
                counterTextView.setText(hitCount+"");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Время вышло");

            }
        }.start();

        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                buttonTarget.setVisibility(View.VISIBLE);
                int randomX = ThreadLocalRandom.current().nextInt(0, maxWidthGameZone + 1);
                int randomY = ThreadLocalRandom.current().nextInt(0, maxHeightGameZone + 1);

                buttonTarget.setX(randomX);
                buttonTarget.setY(randomY);

            }

            public void onFinish() {
                Log.d(TAG, "Время вышло " + maxHeightGameZone);

            }
        }.start();


        //  sleep(500);
        //GAME_TIME --;

        //   }


    }


    private void refreshTarget() {

    }

    private void getSizeGameZone() {
        FrameLayout linearLayout = findViewById(R.id.layout_game_zone_id);
        maxHeightGameZone = (linearLayout.getHeight() - PICTURE_SIZE * 2);
        maxWidthGameZone = (linearLayout.getWidth() - PICTURE_SIZE * 2);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
            // Do some stuff
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

}
