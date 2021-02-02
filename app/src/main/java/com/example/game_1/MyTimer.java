package com.example.game_1;

import android.os.CountDownTimer;

public class MyTimer extends CountDownTimer
{

    public MyTimer(long millisInFuture, long countDownInterval)
    {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish()
    {
        // Do something...
    }

    public void onTick(long millisUntilFinished)
    {

    }

}