package com.tinh.dev.applove;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.WindowManager;

import com.tinh.dev.applove.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPaer;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mapped();
    }

    private void mapped(){
       viewPaer=findViewById(R.id.viewPaer);
       tab=findViewById(R.id.tab);

       fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager());
       viewPaer.setAdapter(fragmentPagerAdapter);
       tab.setupWithViewPager(viewPaer);


       }
}
