package com.tinh.dev.applove.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.tinh.dev.applove.R;

public class MusicService extends Service {
    private MediaPlayer player;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(getApplicationContext(), R.raw.the_girl);
        player.isLooping();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        RunAble runAble1 = new RunAble(5,MusicService.this);
        new Thread(runAble1).start();
        return START_STICKY;

        }
    class RunAble implements Runnable{
        int seconds;
        Context context;

        public RunAble(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i=0;i<=8;i++){
                Handler handler=new Handler(Looper.getMainLooper());
                final int intI=i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI==8){
                            player.start();
                            RunAble  runAble1=new RunAble(5,context);
                            new Thread(runAble1).start();
                        } }

                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }



        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
