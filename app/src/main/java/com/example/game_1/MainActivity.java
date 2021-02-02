package com.example.game_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private int PICTURE_SIZE = 100; // размер цели в dp
    private int hitCount = 0;  // счетчик попаданий
    private int GAME_TIME = 7;  // продолжительность игры в секундах
    private int FREQ = 500;  // частота обновления цели в мс
    private int maxHeightGameZone;
    private int maxWidthGameZone;

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

        buttonStart = findViewById(R.id.button_start_id);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSizeGameZone();
                Log.d(TAG, "высота экрана " + maxHeightGameZone);
                Log.d(TAG, "ширина экрана " + maxWidthGameZone);
                buttonStart.setVisibility(View.GONE);
                startGame();

            }
        });

        buttonTarget = findViewById(R.id.target_button_id);
        buttonTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hitCount++;
                buttonTarget.setVisibility(View.GONE);
            }
        });

        timerTextView = findViewById(R.id.timer_textview_id);
        counterTextView = findViewById(R.id.counter_textview_id);

        buttonTarget.setX(maxWidthGameZone);
        buttonTarget.setY(maxHeightGameZone);
    }

    private void startGame() {

        new CountDownTimer(GAME_TIME * 1000, 1000) {  // обратный отсчет
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Время: " + millisUntilFinished / 1000 + "");
                counterTextView.setText("Счет: " + hitCount);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Время вышло");
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("COUNT", hitCount);
                startActivity(intent);
            }
        }.start();

        new CountDownTimer(GAME_TIME * 1000, FREQ) {  // обновление цели
            public void onTick(long millisUntilFinished) {
                getRandomFruitsImage();
                buttonTarget.setVisibility(View.VISIBLE);
                int randomX = ThreadLocalRandom.current().nextInt(0, maxWidthGameZone + 1);
                int randomY = ThreadLocalRandom.current().nextInt(0, maxHeightGameZone + 1);

                buttonTarget.setX(randomX);
                buttonTarget.setY(randomY);

            }

            public void onFinish() {

            }
        }.start();

    }

    private void getSizeGameZone() {
        FrameLayout linearLayout = findViewById(R.id.layout_game_zone_id);
        maxHeightGameZone = (linearLayout.getHeight() - PICTURE_SIZE * 2);
        maxWidthGameZone = (linearLayout.getWidth() - PICTURE_SIZE * 2);
    }

    private void getRandomFruitsImage() {
        buttonTarget.setImageResource(Fruits.getFruit());
    }

    @Override
    public void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());

        //When BACK BUTTON is pressed, the activity on the stack is restarted
        //Do what you want on the refresh procedure here
    }

}
