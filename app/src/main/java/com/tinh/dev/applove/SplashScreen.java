package com.tinh.dev.applove;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tinh.dev.applove.PassWord.CreatPass;
import com.tinh.dev.applove.PassWord.InPutPass;
import com.tinh.dev.applove.dataBase.DataBase;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBase=new DataBase(this);
        ImageView imageView=findViewById(R.id.span);
        TextView textView=findViewById(R.id.txt);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim7);
        imageView.setAnimation(animation);
        textView.setAnimation(animation);
       Thread thread=new Thread(){
           @Override
           public void run() {
               try {
                   sleep(2800);
               }catch (Exception e){

               }finally {
                  ChuyenTrang();
                   finish();
               }
           }
       };
       thread.start();

    }

    private void ChuyenTrang(){
        final Intent intent=new Intent(this,InitializationActivity.class);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(100);
                }catch (Exception e){

                }finally {
                    Cursor cursor=dataBase.getPassWord();
                    SharedPreferences preferences=getSharedPreferences("PREFS",0);
                    String password=preferences.getString("password","0");
                    if (cursor.moveToNext()){
                        int a=cursor.getInt(1);

                        if (a==0){
                            if (password.equals("0")){
                                Intent intent1=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent1);
                                overridePendingTransition(R.anim.divao,R.anim.dira);
                                finish();
                            }else {
                                Intent intent1=new Intent(getApplicationContext(), InPutPass.class);
                                startActivity(intent1);
                                finish();
                            }

                        }else {
                            dulieu();
                            overridePendingTransition(R.anim.divao,R.anim.dira);
                            finish();
                        }


                    }else {
                        Log.e("Timh","Oki");
                        dulieu();
                        overridePendingTransition(R.anim.divao,R.anim.dira);
                        finish();
                    }


                }
            }
        };
        thread.start();
    }
    void dulieu() {
        Cursor cursorData = dataBase.getSoNgayYeu();
        Cursor cursorData1 = dataBase.getTen();
        if (!cursorData1.moveToNext() || !cursorData.moveToNext()) {
            startActivity(new Intent(SplashScreen.this, InitializationActivity.class));
            overridePendingTransition(R.anim.divao,R.anim.dira);
            finish();
        } else {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
            overridePendingTransition(R.anim.divao,R.anim.dira);
            finish();
        }
    }
}
