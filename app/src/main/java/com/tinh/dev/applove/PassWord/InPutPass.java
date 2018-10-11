package com.tinh.dev.applove.PassWord;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.tinh.dev.applove.MainActivity;
import com.tinh.dev.applove.R;

import java.util.List;

public class InPutPass extends AppCompatActivity {
    PatternLockView mPatternLockView;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pass);
        textView=findViewById(R.id.baocao);
        SharedPreferences preferences=getSharedPreferences("PREFS",0);
        final String password=preferences.getString("password","0");

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                if (password.equals( PatternLockUtils.patternToString(mPatternLockView, pattern))){
                    Intent intent1=new Intent(getApplicationContext(), MainActivity.class);
                    textView.setVisibility(View.INVISIBLE);
                    startActivity(intent1);
                    finish();
                }else{
                  textView.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCleared() {

            }
        });

    }
}
