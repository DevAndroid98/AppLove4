package com.tinh.dev.applove;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.tinh.dev.applove.dataBase.DataBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InitializationActivity extends AppCompatActivity {
    private Button btnDateLove;
    private EditText edtNamBoy;
    private EditText edtNameGirl;
    Calendar calendarOne, calendarTwo;
    SimpleDateFormat simpleDateFormat;
    private Cursor cursorData, cursorData1;
    private DataBase dataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialization);
        btnDateLove = findViewById(R.id.btnDateLove);
        edtNamBoy = findViewById(R.id.edtNamBoy);
        edtNameGirl = findViewById(R.id.edtNameGirl);
        calendarTwo = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dataBase = new DataBase(this);
        btnDateLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });
    }

    public void Create(View view) {
        if (edtNamBoy.getText().toString().trim().equals("")) {
            edtNamBoy.setError(getString(R.string.error));
            return;
        }
        if (edtNameGirl.getText().toString().trim().equals("")) {
            edtNameGirl.setError(getString(R.string.error));
            return;
        }
        if (btnDateLove.getText().toString().trim().equals("")) {
            btnDateLove.setError(getString(R.string.error));
            return;
        }
        String t1 = edtNamBoy.getText().toString();
        String t2 = edtNameGirl.getText().toString();
        int ngayyeu = (int) (calendarOne.getTimeInMillis() / (1000 * 60 * 60 * 24));
        int ngay = (int) ((calendarTwo.getTimeInMillis() - calendarOne.getTimeInMillis()) / (1000 * 60 * 60 * 24));

        if (ngay < 0) {
            btnDateLove.setText("Chưa yêu mà đã tính ngày rồi");
            return;
        } else {
            dataBase.insertSoNgayYeu(ngay);
            dataBase.insertTen(t1, t2);
            dataBase.insertNgayYeu(ngayyeu);
            }
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(150);
                } catch (Exception e) {
                    Log.e("Tình", e + "");
                } finally {
                    startActivity(new Intent(InitializationActivity.this, Gif.class));
                    overridePendingTransition(R.anim.divao, R.anim.dira);
                    finish();
                }
            }
        };
        thread.start();
    }



    private void chonngay(){
        calendarOne= Calendar.getInstance();
        int ngay=calendarOne.get(Calendar.DATE);
        int thang=calendarOne.get(Calendar.MONTH);
        int nam=calendarOne.get(Calendar.YEAR);
        DatePickerDialog pickerDialog =new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarOne.set(i,i1,i2);
                        btnDateLove.setText("Ngày yêu nhau:"+simpleDateFormat.format(calendarOne.getTime()));

                    }
                },nam,thang,ngay);
        pickerDialog.show();

    }
}
