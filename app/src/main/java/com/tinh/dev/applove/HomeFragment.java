package com.tinh.dev.applove;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.tinh.dev.applove.PassWord.CreatPass;
import com.tinh.dev.applove.dataBase.BaseNgaySinhNhat;
import com.tinh.dev.applove.dataBase.DataBase;
import com.tinh.dev.applove.service.Brosd;
import com.tinh.dev.applove.service.ChatHeadService;
import com.tinh.dev.applove.service.DongHoService;
import com.tinh.dev.applove.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import me.itangqi.waveloadingview.WaveLoadingView;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {
    private CardView cardView;
    private Window window;

    private int REQUEST_CODE_BACKGROUND = 789;

    private Animation anim1;
    private TextView textView;
    private CardView testa;
    private CircularImageView imgBoy;
    private int currentBackgroundColor = 0xffffffff;
    Bitmap b;
    private ImageView imgbackground;
    private SimpleDateFormat simpleDateFormat;
    Calendar calendarOne, calendarTwo, calendar3, calendar4;

    private DataBase dataBase;
    private BaseNgaySinhNhat baseNgaySinhNhat;
    private Cursor cursor, cursor1, cursor3, cursor4;

    private SharedPreferences sharedPreferences;
    private int REQUEST_CODE_FOLDER = 123;
    private int REQUEST_CODE_FOLDER1 = 456;

    private TextView txtNgayYeu;
    private LinearLayout linearLayout;
    private TextView txtHanhPhuc;
    private CircularImageView imgGirl;
    private TextView txtNameBoy;
    private TextView txtNameGirl;
    private LinearLayout relativeLayout;
    private CardView test;
    private RelativeLayout relativeLayout1;

    private CardView test3;
    private CardView test1;
    private CardView test2;


    private TextView txtAgeBoy;
    private TextView txtCungBoy;
    private TextView txtAgeGirl;
    private TextView txtCungGirl;
    private TextView txtYeuThuong;
    private TextView txtHiHi;
    private SharedPreferences.Editor editor;

    private ProgressBar pro1;
    private ProgressBar pro2;

    private TextView nam;
    private TextView thang;
    private TextView ngay;
    private TextView gio;
    private TextView phut;
    private TextView giay;

    private ImageView imgLove;
    private ImageView imgLove1;
    private ImageView imgLove2;
    private ImageView imgLove3;
    private ImageView imgLove4;
    private ImageView imgLove5;

    private TextView txtnam;
    private TextView txtthang;
    private TextView txtngay;
    private TextView txtgio;
    private TextView txtphut;
    private TextView txtgiay;
    private Typeface font_1, font_2, font_3, font_4, font_5, font_6, font_7, font_8, font_9, font_10, font_11, typeface;
    private CardView viewA;
    private TextView fontA;
    private CardView viewB;
    private TextView fontB;
    private CardView viewC;
    private TextView fontC;
    private CardView viewD;
    private TextView fontD;
    private CardView viewE;
    private TextView fontE;
    private CardView viewF;
    private TextView fontF;
    private CardView viewG;
    private TextView fontG;
    private CardView viewH;
    private TextView fontH;
    private CardView viewI;
    private TextView fontI;
    private CardView viewK;
    private TextView fontK;
    private CardView viewL;
    private CardView view0;
    private TextView fontL;
    private TextView font0;
    private Dialog dialog;
    private int i, i1, i2, i3, i4;
    private ImageView settings, love;
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    private  boolean t = false;
    private CardView tolbar;


    private WaveLoadingView loaddingWave;

    private TextView ngay1;
    private TextView ngay2;
    private String uribackground, uriGirl, uriBoy, hp;
    private ImageView mp3;

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        typeFace();
        mp3=view.findViewById(R.id.mp3);
        dataBase = new DataBase(getActivity());
        baseNgaySinhNhat = new BaseNgaySinhNhat(getActivity());
        cardView = view.findViewById(R.id.toolbar);
        cardView.setBackgroundResource(R.drawable.style4);
        sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        test = view.findViewById(R.id.test);
        test3 = view.findViewById(R.id.test3);
        test1 = view.findViewById(R.id.test1);
        test2 = view.findViewById(R.id.test2);
        testa = view.findViewById(R.id.test);
        love = view.findViewById(R.id.love);
        Animation lv = AnimationUtils.loadAnimation(getActivity(), R.anim.anim8);
        love.setAnimation(lv);

        imgLove=view.findViewById(R.id.img_love);
        imgLove1=view.findViewById(R.id.img_love1);
        imgLove2=view.findViewById(R.id.img_love2);
        imgLove3=view.findViewById(R.id.img_love3);
        imgLove4=view.findViewById(R.id.img_love4);
        imgLove5=view.findViewById(R.id.img_love5);
        tolbar=view.findViewById(R.id.toolbar);

        imgbackground = view.findViewById(R.id.background);
        // window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.pink));
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.anim3);
        txtNgayYeu = view.findViewById(R.id.txtNgayYeu);
        imgBoy = view.findViewById(R.id.imgBoy);
        imgGirl = view.findViewById(R.id.imgGirl);
        relativeLayout = view.findViewById(R.id.relativeLayout);
        linearLayout = view.findViewById(R.id.linearLayout);
        txtAgeBoy = view.findViewById(R.id.txtAgeBoy);
        txtCungBoy = view.findViewById(R.id.txtCungBoy);
        txtAgeGirl = view.findViewById(R.id.txtAgeGirl);
        txtCungGirl = view.findViewById(R.id.txtCungGirl);
        loaddingWave = view.findViewById(R.id.loaddingWave);
        txtYeuThuong = view.findViewById(R.id.txtYeuThuong);
        txtHiHi = view.findViewById(R.id.txtHiHi);
        txtHanhPhuc = view.findViewById(R.id.txtHanhPhuc);
        test = view.findViewById(R.id.test);
        txtNameBoy = view.findViewById(R.id.txtNameBoy);
        txtNameGirl = view.findViewById(R.id.txtNameGirl);
        pro1 = view.findViewById(R.id.pro1);
        pro2 = view.findViewById(R.id.pro2);
        settings = view.findViewById(R.id.setting);
        imgBoy.setAnimation(animation);
        imgGirl.setAnimation(animation);
        linearLayout.setAnimation(animation);
        txtHanhPhuc.setAnimation(animation);
        txtNameBoy.setAnimation(animation);
        txtNameGirl.setAnimation(animation);
        relativeLayout.setAnimation(animation);
        test.setAnimation(animation);



        typeface = Typeface.createFromAsset(getActivity().getAssets(), "love_girl.ttf");
        Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "font_holidays.ttf");
        txtNameBoy.setTypeface(typeface);
        txtNameGirl.setTypeface(typeface);
        txtHanhPhuc.setTypeface(typeface);
        nam = view.findViewById(R.id.nam);
        thang = view.findViewById(R.id.thang);
        ngay = view.findViewById(R.id.ngay);
        gio = view.findViewById(R.id.gio);
        phut = view.findViewById(R.id.phut);
        giay = view.findViewById(R.id.giay);
        txtnam = view.findViewById(R.id.txtnam);
        txtthang = view.findViewById(R.id.txtthang);
        txtngay = view.findViewById(R.id.txtngay);
        txtgio = view.findViewById(R.id.txtgio);
        txtphut = view.findViewById(R.id.txtphut);
        txtgiay = view.findViewById(R.id.txtgiay);
        nam.setTypeface(typeface);
        thang.setTypeface(typeface);
        ngay.setTypeface(typeface);
        gio.setTypeface(typeface);
        phut.setTypeface(typeface);
        giay.setTypeface(typeface);

        final Date date = new Date();
        String strDateFormat24 = "HH";
        String strDateFormat25 = "mm";
        String strDateFormat26 = "ss";
        SimpleDateFormat sdf = null;
        SimpleDateFormat sdf1 = null;
        SimpleDateFormat sdf2 = null;
        sdf = new SimpleDateFormat(strDateFormat24);
        sdf1 = new SimpleDateFormat(strDateFormat25);
        sdf2 = new SimpleDateFormat(strDateFormat26);
        txtgio.setText(sdf.format(date) + "");
        txtphut.setText(sdf1.format(date) + "");
        RunAble runAble1 = new RunAble(5);
        new Thread(runAble1).start();

        txtAgeBoy.setTypeface(typeface1);
        txtCungBoy.setTypeface(typeface1);
        txtAgeGirl.setTypeface(typeface1);
        txtCungGirl.setTypeface(typeface1);
        txtNgayYeu.setTypeface(typeface1);
        txtYeuThuong.setTypeface(typeface1);
        txtHiHi.setTypeface(typeface1);
        txtYeuThuong.setText("");
        txtHiHi.setText("chazố!!");
        txtHiHi.setTextSize(10f);
        Animation animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.anim1);
        settings.setAnimation(animation1);
        getData();
        uribackground = sharedPreferences.getString("uribackground", "");
        uriBoy = sharedPreferences.getString("uriBoy", "");
        uriGirl = sharedPreferences.getString("uriGirl", "");
        int colorboy = sharedPreferences.getInt("colorboy", 0);
        int colorgirl = sharedPreferences.getInt("colorgirl", 0);
        int hanhphuc = sharedPreferences.getInt("hanhphuc", 0);
        int cungboy = sharedPreferences.getInt("cungboy", 0);
        int cunggirl = sharedPreferences.getInt("cunggirl", 0);
        int testa = sharedPreferences.getInt("test2", 0);
        int testb = sharedPreferences.getInt("test3", 0);
        if (colorboy != 0) {
            txtNameBoy.setTextColor(colorboy);
        }

        if (colorgirl != 0) {
            txtNameGirl.setTextColor(colorgirl);
        }

        if (hanhphuc != 0) {
            txtHanhPhuc.setTextColor(hanhphuc);
        }
        if (cungboy != 0) {
            txtCungBoy.setTextColor(cungboy);
        }
        if (cunggirl != 0) {
            txtCungGirl.setTextColor(cunggirl);
        }
        if (testa != 0) {
            test3.setCardBackgroundColor(testa);
            test3.setRadius(46f);

        }

        if (testb != 0) {
            test2.setCardBackgroundColor(testa);
            test2.setRadius(46f);
        }
        i = sharedPreferences.getInt("font1", 0);
        i1 = sharedPreferences.getInt("font2", 0);
        i2 = sharedPreferences.getInt("font3", 0);
        i3 = sharedPreferences.getInt("font4", 0);
        i4 = sharedPreferences.getInt("font5", 0);
        hp = sharedPreferences.getString("hanhphucx", "");
        xetfont();
        xetfont1();
        xetfont2();
        xetfont3();
        xetfont4();
        getDataName();
        getDataDate();
        cungHoangDao();
        updateNgayyeu();
        toolbarx();
        lovex();
        laymau();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(getActivity())) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getActivity().getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicksetting();
                }
            });
        }

        if (!hp.equals("")) {
            txtHanhPhuc.setText(hp);
        }
            int xx=sharedPreferences.getInt("test",1);
        if (xx!=1){
            test.setCardBackgroundColor(xx);
            test.setRadius(46f);
        }
        int xxx=sharedPreferences.getInt("test1",1);
        if (xxx!=1){
            test1.setCardBackgroundColor(xxx);
            test1.setRadius(46f);
        }

        requestRead();
        clicktext();
        return view;
    }


    public void imgBackground() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_BACKGROUND);
    }

    public void imgBoy() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER);
    }


    public void imgGirl() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_BACKGROUND && resultCode == RESULT_OK && data != null) {
            Uri uribackground = data.getData();
            imgbackground.setImageURI(uribackground);
            editor.putString("uribackground", String.valueOf(uribackground));
            editor.commit();
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uriBoy = data.getData();
            imgBoy.setImageURI(uriBoy);
            pro1.setVisibility(View.INVISIBLE);
            editor.putString("uriBoy", String.valueOf(uriBoy));
            editor.commit();
        }

        if (requestCode == REQUEST_CODE_FOLDER1 && resultCode == RESULT_OK && data != null) {
            Uri uriGirl = data.getData();
            imgGirl.setImageURI(uriGirl);
            pro2.setVisibility(View.INVISIBLE);
            editor.putString("uriGirl", String.valueOf(uriGirl));
            editor.commit();
        }
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
              if (Settings.canDrawOverlays(getActivity())) {
                  settings.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          clicksetting();
                      }
                  });
            } else {
                Toast.makeText(getActivity(),
                        "Cửa sổ sẽ không hoạt động được",
                        Toast.LENGTH_LONG).show();
            }

    } else
        {
        super.onActivityResult(requestCode, resultCode, data);
    }


}

    public void requestRead() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        } else {
            if (uribackground.equals("")) {
                imgbackground.setImageResource(R.drawable.hinhnen_1);
            } else {
                imgbackground.setImageURI(Uri.parse(Uri.decode(uribackground)));
            }
            if (uriBoy.equals("")) {
                pro1.setVisibility(View.VISIBLE);
                imgBoy.setImageResource(R.drawable.hinhnen_1);
            } else {
                pro1.setVisibility(View.INVISIBLE);
                imgBoy.setImageURI(Uri.parse(Uri.decode(uriBoy)));
            }

            if (uriGirl.equals("")) {
                pro2.setVisibility(View.VISIBLE);
                imgGirl.setImageResource(R.drawable.hinhnen_1);
            } else {
                pro2.setVisibility(View.INVISIBLE);
                imgGirl.setImageURI(Uri.parse(Uri.decode(uriGirl)));
            }


        }
    }


    private void typeFace() {
        font_1 = Typeface.createFromAsset(getActivity().getAssets(), "font_1.ttf");
        font_2 = Typeface.createFromAsset(getActivity().getAssets(), "font_2.ttf");
        font_3 = Typeface.createFromAsset(getActivity().getAssets(), "font_3.ttf");
        font_4 = Typeface.createFromAsset(getActivity().getAssets(), "font_4.TTF");
        font_5 = Typeface.createFromAsset(getActivity().getAssets(), "font_5.TTF");
        font_6 = Typeface.createFromAsset(getActivity().getAssets(), "font_6.ttf");
        font_7 = Typeface.createFromAsset(getActivity().getAssets(), "font_7.ttf");
        font_8 = Typeface.createFromAsset(getActivity().getAssets(), "font_8.otf");
        font_9 = Typeface.createFromAsset(getActivity().getAssets(), "font_9.ttf");
        font_10 = Typeface.createFromAsset(getActivity().getAssets(), "font_holidays.ttf");
        font_11 = Typeface.createFromAsset(getActivity().getAssets(), "font_11.ttf");

    }

    private void dialogfont() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card_font);
        anhxaDialog();
        font();
        clickfont();
        dialog.show();
    }
    private void dialogfont1() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card_font);
        anhxaDialog();
        font();
        clickfont1();
        dialog.show();
    }
    private void dialogfont2() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card_font);
        anhxaDialog();
        font();
        clickfont2();
        dialog.show();
    }
    private void dialogfont3() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card_font);
        anhxaDialog();
        font();
        clickfont3();
        dialog.show();
    }
    private void dialogfont4() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_card_font);
        anhxaDialog();
        font();
        clickfont4();
        dialog.show();
    }

    private void anhxaDialog() {
        viewA = dialog.findViewById(R.id.view_a);
        view0 = dialog.findViewById(R.id.view_0);
        fontA = dialog.findViewById(R.id.font_a);
        font0 = dialog.findViewById(R.id.font_0);
        viewB = dialog.findViewById(R.id.view_b);
        fontB = dialog.findViewById(R.id.font_b);
        viewC = dialog.findViewById(R.id.view_c);
        fontC = dialog.findViewById(R.id.font_c);
        viewD = dialog.findViewById(R.id.view_d);
        fontD = dialog.findViewById(R.id.font_d);
        viewE = dialog.findViewById(R.id.view_e);
        fontE = dialog.findViewById(R.id.font_e);
        viewF = dialog.findViewById(R.id.view_f);
        fontF = dialog.findViewById(R.id.font_f);
        viewG = dialog.findViewById(R.id.view_g);
        fontG = dialog.findViewById(R.id.font_g);
        viewH = dialog.findViewById(R.id.view_h);
        fontH = dialog.findViewById(R.id.font_h);
        viewI = dialog.findViewById(R.id.view_i);
        fontI = dialog.findViewById(R.id.font_i);
        viewK = dialog.findViewById(R.id.view_k);
        fontK = dialog.findViewById(R.id.font_k);
        viewL = dialog.findViewById(R.id.view_l);
        fontL = dialog.findViewById(R.id.font_l);
    }

    private void color() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("colorboy", i);
                        txtNameBoy.setTextColor(i);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color1() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("colorgirl", i);
                        txtNameGirl.setTextColor(i);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color3() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("hanhphuc", i);
                        txtHanhPhuc.setTextColor(i);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color4() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("cungboy", i);
                        txtCungBoy.setTextColor(i);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color5() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("cunggirl", i);
                        txtCungGirl.setTextColor(i);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color6() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("test3", i);
                        test3.setCardBackgroundColor(i);
                        test3.setRadius(46f);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void color7() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("test2", i);
                        test2.setCardBackgroundColor(i);
                        test2.setRadius(46f);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }
    private void color8() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("test", i);
                        test.setCardBackgroundColor(i);
                        test.setRadius(46f);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }
    private void color9() {

        changeBackgroundColor(currentBackgroundColor);
        ColorPickerDialogBuilder
                .with(getActivity())
                .setTitle("Chọn màu")
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int i) {

                    }
                })
                .setPositiveButton("Oki", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, Integer[] integers) {
                        editor = sharedPreferences.edit();
                        editor.putInt("test1", i);
                        test1.setCardBackgroundColor(i);
                        test1.setRadius(46f);
                        editor.commit();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).showColorEdit(true)
                .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                .build()
                .show();
    }

    private void changeBackgroundColor(int selectedColor) {
        currentBackgroundColor = selectedColor;

    }

    public void popupNameBoy() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtNameBoy);
        popupMenu.getMenuInflater().inflate(R.menu.popup_name_font, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.name:
                        doiNameBoy();
                        break;
                    case R.id.font:
                        dialogfont();
                        break;
                    case R.id.color:
                        color();
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }
    public void popupNameGirl() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtNameGirl);
        popupMenu.getMenuInflater().inflate(R.menu.popup_name_font, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.name:
                        doiNameGirl();
                        break;
                    case R.id.font:
                        dialogfont1();
                        break;
                    case R.id.color:
                        color1();
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }
    public void popupHanhPhuc() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), txtHanhPhuc);
        popupMenu.getMenuInflater().inflate(R.menu.popup_name_font, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.name:
                        doiHanhPhuc();
                        break;
                    case R.id.font:
                        dialogfont2();
                        break;
                    case R.id.color:
                        color3();
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }
    public void popupCungBoy() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), test3);
        popupMenu.getMenuInflater().inflate(R.menu.popup_age_color, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.age:
                        themNgaySinhNhatNam();
                        break;
                    case R.id.colorbackground:
                        color6();
                        break;
                    case R.id.font:
                        dialogfont3();
                        break;
                    case R.id.colortext:
                        color4();
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }
    public void popupCungGirl() {
        PopupMenu popupMenu = new PopupMenu(getActivity(), test2);
        popupMenu.getMenuInflater().inflate(R.menu.popup_age_color, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.age:
                        themNgaySinhNhatNu();
                        break;
                    case R.id.colorbackground:
                        color7();
                        break;
                    case R.id.font:
                        dialogfont4();
                        break;
                    case R.id.colortext:
                        color5();
                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }

    public void clicksetting(){
        PopupMenu setting=new PopupMenu(getActivity(),settings);

        setting.getMenuInflater().inflate(R.menu.navigation,setting.getMenu());

        setting.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.thongbao:
                        getActivity().sendBroadcast(new Intent(getActivity(), Brosd.class));
                        break;
                    case R.id.amnhac:
                       Dialog dialog=new Dialog(getActivity());
                       editor=sharedPreferences.edit();
                       dialog.setContentView(R.layout.dialog_nhac);
                        final ImageView nhac=dialog.findViewById(R.id.nhac);
                        int x=sharedPreferences.getInt("nhac",0);
                        if (x==1){
                            nhac.setImageResource(R.drawable.stop);
                            t=true;
                        }else if (x==2){
                            nhac.setImageResource(R.drawable.start);
                            t=false;
                        }
                        nhac.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (t==false){
                                   getActivity().startService(new Intent(getActivity(), MusicService.class));
                                   nhac.setImageResource(R.drawable.stop);
                                   editor.putInt("nhac",1);
                                   editor.commit();
                                    t=true;
                                }else {
                                    getActivity().stopService(new Intent(getActivity(), MusicService.class));
                                    nhac.setImageResource(R.drawable.start);
                                    editor.putInt("nhac",2);
                                    editor.commit();
                                    t=false;
                                }
                            }
                        });
                       dialog.show();


                        break;
                    case  R.id.cuasonoi:
                      Intent intent = new Intent(getActivity(), ChatHeadService.class);
                      intent.putExtra("a",uribackground);
                      intent.putExtra("b",uriBoy);
                      intent.putExtra("c",uriGirl);
                      getActivity().startService(intent);

                        break;
                    case R.id.dongho:
                        getActivity().startService(new Intent(getActivity(), DongHoService.class));
                        break;
                    case R.id.exit:
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("Thoát");
                        builder.setMessage("Bạn có muốn thoát không?");
                        builder.setIcon(R.drawable.icons8_cancel_48);
                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                              getActivity().finish();
                            }
                        });
                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        builder.show();
                        break;
                    case R.id.matkhau:
                         editor=sharedPreferences.edit();
                          int a=sharedPreferences.getInt("matkhau",1);
                           if (a==1){
                               editor.putInt("matkhau",2);
                               editor.commit();
                               startActivity(new Intent(getActivity(), CreatPass.class));
                           }else {
                              AlertDialog.Builder builder1=new AlertDialog.Builder(getActivity());
                              builder1.setTitle("Thông báo");
                              builder1.setMessage("Bạn có muốn tắt mật khẩu không?");
                              builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                  @Override
                                  public void onClick(DialogInterface dialog, int which) {
                                      dataBase.updatetPassWord(2,1);
                                      editor.putInt("matkhau",1);
                                      editor.commit();
                                  }
                              });
                           builder1.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {

                               }
                           });
                           builder1.show();
                           }

                           break;
                }
                return true;
            }
        });
        setting.show();
    }


    public void font() {
        font0.setTypeface(typeface);
        fontA.setTypeface(font_1);
        fontB.setTypeface(font_2);
        fontC.setTypeface(font_3);
        fontD.setTypeface(font_4);
        fontE.setTypeface(font_5);
        fontF.setTypeface(font_6);
        fontG.setTypeface(font_7);
        fontH.setTypeface(font_8);
        fontI.setTypeface(font_9);
        fontK.setTypeface(font_10);
        fontL.setTypeface(font_11);

    }

    public void clickfont() {
        view0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 0);
                txtNameBoy.setTypeface(typeface);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 1);
                txtNameBoy.setTypeface(font_1);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 2);
                txtNameBoy.setTypeface(font_2);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 3);
                txtNameBoy.setTypeface(font_3);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 4);
                txtNameBoy.setTypeface(font_4);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 5);
                txtNameBoy.setTypeface(font_5);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 6);
                txtNameBoy.setTypeface(font_6);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 7);
                txtNameBoy.setTypeface(font_7);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 8);
                txtNameBoy.setTypeface(font_8);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 9);
                txtNameBoy.setTypeface(font_9);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 10);
                txtNameBoy.setTypeface(font_10);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font1", 11);
                txtNameBoy.setTypeface(font_11);
                editor.commit();
                dialog.dismiss();
            }
        });

    }
    public void clickfont1() {
        view0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 0);
                txtNameGirl.setTypeface(typeface);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 1);
                txtNameGirl.setTypeface(font_1);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 2);
                txtNameGirl.setTypeface(font_2);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 3);
                txtNameGirl.setTypeface(font_3);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 4);
                txtNameGirl.setTypeface(font_4);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 5);
                txtNameGirl.setTypeface(font_5);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 6);
                txtNameGirl.setTypeface(font_6);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 7);
                txtNameGirl.setTypeface(font_7);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 8);
                txtNameGirl.setTypeface(font_8);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 9);
                txtNameGirl.setTypeface(font_9);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 10);
                txtNameGirl.setTypeface(font_10);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font2", 11);
                txtNameGirl.setTypeface(font_11);
                editor.commit();
                dialog.dismiss();
            }
        });

    }
    public void clickfont2() {
        view0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 0);
                txtHanhPhuc.setTypeface(typeface);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 1);
                txtHanhPhuc.setTypeface(font_1);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 2);
                txtHanhPhuc.setTypeface(font_2);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 3);
                txtHanhPhuc.setTypeface(font_3);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 4);
                txtHanhPhuc.setTypeface(font_4);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 5);
                txtHanhPhuc.setTypeface(font_5);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 6);
                txtHanhPhuc.setTypeface(font_6);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 7);
                txtHanhPhuc.setTypeface(font_7);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 8);
                txtHanhPhuc.setTypeface(font_8);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 9);
                txtHanhPhuc.setTypeface(font_9);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 10);
                txtHanhPhuc.setTypeface(font_10);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font3", 11);
                txtHanhPhuc.setTypeface(font_11);
                editor.commit();
                dialog.dismiss();
            }
        });

    }
    public void clickfont3() {
        view0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 0);
                txtCungBoy.setTypeface(typeface);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 1);
                txtCungBoy.setTypeface(font_1);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 2);
                txtCungBoy.setTypeface(font_2);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 3);
                txtCungBoy.setTypeface(font_3);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 4);
                txtCungBoy.setTypeface(font_4);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 5);
                txtCungBoy.setTypeface(font_5);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 6);
                txtCungBoy.setTypeface(font_6);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 7);
                txtCungBoy.setTypeface(font_7);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 8);
                txtCungBoy.setTypeface(font_8);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 9);
                txtCungBoy.setTypeface(font_9);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 10);
                txtCungBoy.setTypeface(font_10);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font4", 11);
                txtCungBoy.setTypeface(font_11);
                editor.commit();
                dialog.dismiss();
            }
        });

    }
    public void clickfont4() {
        view0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 0);
                txtCungGirl.setTypeface(typeface);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 1);
                txtCungGirl.setTypeface(font_1);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 2);
                txtCungGirl.setTypeface(font_2);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 3);
                txtCungGirl.setTypeface(font_3);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 4);
                txtCungGirl.setTypeface(font_4);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 5);
                txtCungGirl.setTypeface(font_5);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 6);
                txtCungGirl.setTypeface(font_6);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 7);
                txtCungGirl.setTypeface(font_7);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 8);
                txtCungGirl.setTypeface(font_8);
                editor.commit();
                dialog.dismiss();
            }
        });

        viewI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 9);
                txtCungGirl.setTypeface(font_9);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 10);
                txtCungGirl.setTypeface(font_10);
                editor.commit();
                dialog.dismiss();
            }
        });
        viewL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = sharedPreferences.edit();
                editor.putInt("font5", 11);
                txtCungGirl.setTypeface(font_11);
                editor.commit();
                dialog.dismiss();
            }
        });

    }

    public void clicktext() {
        imgbackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBackground();
            }
        });
        imgBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBoy();
            }
        });
        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGirl();
            }
        });
        txtNameBoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupNameBoy();
            }
        });
        txtNameGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupNameGirl();
            }
        });
        txtHanhPhuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupHanhPhuc();
            }
        });
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCungBoy();
            }
        });
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCungGirl();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksetting();
            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_love();
            }
        });
        loaddingWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
popNoidung();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color8();
            }
        });
        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color9();
            }
        });
    }

    private void xetfont() {
        if (i == 0) {
            txtNameBoy.setTypeface(typeface);
        }
        if (i == 1) {
            txtNameBoy.setTypeface(font_1);
        }
        if (i == 2) {
            txtNameBoy.setTypeface(font_2);
        }
        if (i == 3) {
            txtNameBoy.setTypeface(font_3);
        }
        if (i == 4) {
            txtNameBoy.setTypeface(font_4);
        }
        if (i == 5) {
            txtNameBoy.setTypeface(font_5);
        }
        if (i == 6) {
            txtNameBoy.setTypeface(font_6);
        }
        if (i == 7) {
            txtNameBoy.setTypeface(font_7);
        }
        if (i == 8) {
            txtNameBoy.setTypeface(font_8);
        }
        if (i == 9) {
            txtNameBoy.setTypeface(font_9);
        }
        if (i == 10) {
            txtNameBoy.setTypeface(font_10);
        }
        if (i == 11) {
            txtNameBoy.setTypeface(font_11);
        }
    }

    private void xetfont1() {
        if (i1 == 0) {
            txtNameGirl.setTypeface(typeface);
        }
        if (i1 == 1) {
            txtNameGirl.setTypeface(font_1);
        }
        if (i1 == 2) {
            txtNameGirl.setTypeface(font_2);
        }
        if (i1 == 3) {
            txtNameGirl.setTypeface(font_3);
        }
        if (i1 == 4) {
            txtNameGirl.setTypeface(font_4);
        }
        if (i1 == 5) {
            txtNameGirl.setTypeface(font_5);
        }
        if (i1 == 6) {
            txtNameGirl.setTypeface(font_6);
        }
        if (i1 == 7) {
            txtNameGirl.setTypeface(font_7);
        }
        if (i1 == 8) {
            txtNameGirl.setTypeface(font_8);
        }
        if (i1 == 9) {
            txtNameGirl.setTypeface(font_9);
        }
        if (i1 == 10) {
            txtNameGirl.setTypeface(font_10);
        }
        if (i1 == 11) {
            txtNameGirl.setTypeface(font_11);
        }
    }

    private void xetfont2() {
        if (i2 == 0) {
            txtHanhPhuc.setTypeface(typeface);
        }
        if (i2 == 1) {
            txtHanhPhuc.setTypeface(font_1);
        }
        if (i2 == 2) {
            txtHanhPhuc.setTypeface(font_2);
        }
        if (i2 == 3) {
            txtHanhPhuc.setTypeface(font_3);
        }
        if (i2 == 4) {
            txtHanhPhuc.setTypeface(font_4);
        }
        if (i2 == 5) {
            txtHanhPhuc.setTypeface(font_5);
        }
        if (i2 == 6) {
            txtHanhPhuc.setTypeface(font_6);
        }
        if (i2 == 7) {
            txtHanhPhuc.setTypeface(font_7);
        }
        if (i2 == 8) {
            txtHanhPhuc.setTypeface(font_8);
        }
        if (i2 == 9) {
            txtHanhPhuc.setTypeface(font_9);
        }
        if (i2 == 10) {
            txtHanhPhuc.setTypeface(font_10);
        }
        if (i2 == 11) {
            txtHanhPhuc.setTypeface(font_11);
        }
    }

    private void xetfont3() {
        if (i3 == 0) {
            txtCungBoy.setTypeface(typeface);
        }
        if (i3 == 1) {
            txtCungBoy.setTypeface(font_1);
        }
        if (i3 == 2) {
            txtCungBoy.setTypeface(font_2);
        }
        if (i3 == 3) {
            txtCungBoy.setTypeface(font_3);
        }
        if (i3 == 4) {
            txtCungBoy.setTypeface(font_4);
        }
        if (i3 == 5) {
            txtCungBoy.setTypeface(font_5);
        }
        if (i3 == 6) {
            txtCungBoy.setTypeface(font_6);
        }
        if (i3 == 7) {
            txtCungBoy.setTypeface(font_7);
        }
        if (i3 == 8) {
            txtCungBoy.setTypeface(font_8);
        }
        if (i3 == 9) {
            txtCungBoy.setTypeface(font_9);
        }
        if (i3 == 10) {
            txtCungBoy.setTypeface(font_10);
        }
        if (i3 == 11) {
            txtCungBoy.setTypeface(font_11);
        }
    }

    private void xetfont4() {
        if (i4 == 0) {
            txtCungGirl.setTypeface(typeface);
        }
        if (i4 == 1) {
            txtCungGirl.setTypeface(font_1);
        }
        if (i4 == 2) {
            txtCungGirl.setTypeface(font_2);
        }
        if (i4 == 3) {
            txtCungGirl.setTypeface(font_3);
        }
        if (i4 == 4) {
            txtCungGirl.setTypeface(font_4);
        }
        if (i4 == 5) {
            txtCungGirl.setTypeface(font_5);
        }
        if (i4 == 6) {
            txtCungGirl.setTypeface(font_6);
        }
        if (i4 == 7) {
            txtCungGirl.setTypeface(font_7);
        }
        if (i4 == 8) {
            txtCungGirl.setTypeface(font_8);
        }
        if (i4 == 9) {
            txtCungGirl.setTypeface(font_9);
        }
        if (i4 == 10) {
            txtCungGirl.setTypeface(font_10);
        }
        if (i4 == 11) {
            txtCungGirl.setTypeface(font_11);
        }
    }

    private void getDataName() {
        Cursor cursor = dataBase.getTen();
        if (cursor.moveToNext()) {
            String nameboy = cursor.getString(1);
            String namegirl = cursor.getString(2);
            txtNameBoy.setText(nameboy);
            txtNameGirl.setText(namegirl);
        }
    }

    public void getDataDate() {
        Cursor cursor = dataBase.getSoNgayYeu();
        if (cursor.moveToNext()) {
            int datelove = cursor.getInt(1);
            if (datelove == 0) {
                txtNgayYeu.setText("Start love");
            } else {
                txtNgayYeu.setText(String.valueOf(datelove) + "\tngày" + "\n\t\tyêu");
            }

        }
       getData();

    }

    private void cungHoangDao() {
        calendarOne = Calendar.getInstance();
        int namhientai = calendarOne.get(Calendar.YEAR);
        Cursor b = baseNgaySinhNhat.getNam();
        Cursor c = baseNgaySinhNhat.getNu();
        if (b.moveToNext()) {
            int ngay = b.getInt(1);
            int thang = b.getInt(2);
            int nam = b.getInt(3);
            int tuoiNam = namhientai - nam;

            if (tuoiNam == 0) {
                txtAgeBoy.setText("?");
            } else {
                txtAgeBoy.setText(tuoiNam + "");
            }

            if (ngay >= 21 && ngay <= 31 && thang == 3) {
                txtCungBoy.setText("Bạch Dương");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 4) {
                txtCungBoy.setText("Bạch Dương");
            }

            if (ngay >= 21 && ngay <= 31 && thang == 4) {
                txtCungBoy.setText("Kim Ngưu");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 5) {
                txtCungBoy.setText("Kim Ngưu");
            }

            if (ngay >= 21 && ngay <= 31 && thang == 5) {
                txtCungBoy.setText("Song Tử");
            }

            if (ngay >= 1 && ngay <= 21 && thang == 6) {
                txtCungBoy.setText("Song Tử");
            }

            if (ngay >= 22 && ngay <= 31 && thang == 6) {
                txtCungBoy.setText("Cự Giải");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 7) {
                txtCungBoy.setText("Cự Giải");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 7) {
                txtCungBoy.setText("Sư Tử");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 8) {
                txtCungBoy.setText("Sư Tử");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 8) {
                txtCungBoy.setText("Xử Nữ");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 9) {
                txtCungBoy.setText("Xử Nữ");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 9) {
                txtCungBoy.setText("Thiên Bình");
            }

            if (ngay >= 1 && ngay <= 23 && thang == 10) {
                txtCungBoy.setText("Thiên Bình");
            }

            if (ngay >= 24 && ngay <= 31 && thang == 10) {
                txtCungBoy.setText("Bọ Cạp");
            }

            if (ngay >= 1 && ngay <= 22 && thang == 11) {
                txtCungBoy.setText("Bọ Cạp");
            }

            if (ngay >= 23 && ngay <= 31 && thang == 11) {
                txtCungBoy.setText("Nhân Mã");
            }

            if (ngay >= 1 && ngay <= 21 && thang == 12) {
                txtCungBoy.setText("Nhân Mã");
            }
            if (ngay >= 22 && ngay <= 31 && thang == 12) {
                txtCungBoy.setText("Ma Kết");
            }

            if (ngay >= 1 && ngay <= 19 && thang == 1) {
                txtCungBoy.setText("Ma Kết");
            }

            if (ngay >= 20 && ngay <= 31 && thang == 1) {
                txtCungBoy.setText("Bảo Bình");
            }

            if (ngay >= 1 && ngay <= 18 && thang == 2) {
                txtCungBoy.setText("Bảo Bình");
            }

            if (ngay >= 19 && ngay <= 31 && thang == 2) {
                txtCungBoy.setText("Song Ngư");
            }

            if (ngay >= 1 && ngay <= 20 && thang == 3) {
                txtCungBoy.setText("Song Ngư");
            }

            }
            if (c.moveToNext()){
                int ngay3 = c.getInt(1);
                int thang3 = c.getInt(2);
                int nam3 = c.getInt(3);
                int tuoiNu = namhientai - nam3;
                if (tuoiNu == 0) {
                    txtAgeGirl.setText("?");
                } else {
                    txtAgeGirl.setText(tuoiNu + "");
                }
                if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 3) {
                    txtCungGirl.setText("Bạch Dương");
                }

                if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 4) {
                    txtCungGirl.setText("Bạch Dương");
                }

                if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 4) {
                    txtCungGirl.setText("Kim Ngưu");
                }

                if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 5) {
                    txtCungGirl.setText("Kim Ngưu");
                }

                if (ngay3 >= 21 && ngay3 <= 31 && thang3 == 5) {
                    txtCungGirl.setText("Song Tử");
                }

                if (ngay3 >= 1 && ngay3 <= 21 && thang3 == 6) {
                    txtCungGirl.setText("Song Tử");
                }

                if (ngay3 >= 22 && ngay3 <= 31 && thang3 == 6) {
                    txtCungGirl.setText("Cự Giải");
                }

                if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 7) {
                    txtCungGirl.setText("Cự Giải");
                }

                if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 7) {
                    txtCungGirl.setText("Sư Tử");
                }

                if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 8) {
                    txtCungGirl.setText("Sư Tử");
                }

                if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 8) {
                    txtCungGirl.setText("Xử Nữ");
                }

                if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 9) {
                    txtCungGirl.setText("Xử Nữ");
                }

                if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 9) {
                    txtCungGirl.setText("Thiên Bình");
                }

                if (ngay3 >= 1 && ngay3 <= 23 && thang3 == 10) {
                    txtCungGirl.setText("Thiên Bình");
                }

                if (ngay3 >= 24 && ngay3 <= 31 && thang3 == 10) {
                    txtCungGirl.setText("Bọ Cạp");
                }

                if (ngay3 >= 1 && ngay3 <= 22 && thang3 == 11) {
                    txtCungGirl.setText("Bọ Cạp");
                }

                if (ngay3 >= 23 && ngay3 <= 31 && thang3 == 11) {
                    txtCungGirl.setText("Nhân Mã");
                }

                if (ngay3 >= 1 && ngay3 <= 21 && thang3 == 12) {
                    txtCungGirl.setText("Nhân Mã");
                }
                if (ngay3 >= 22 && ngay3 <= 31 && thang3 == 12) {
                    txtCungGirl.setText("Ma Kết");
                }

                if (ngay3 >= 1 && ngay3 <= 19 && thang3 == 1) {
                    txtCungGirl.setText("Ma Kết");
                }

                if (ngay3 >= 20 && ngay3 <= 31 && thang3 == 1) {
                    txtCungGirl.setText("Bảo Bình");
                }

                if (ngay3 >= 1 && ngay3 <= 18 && thang3 == 2) {
                    txtCungGirl.setText("Bảo Bình");
                }

                if (ngay3 >= 19 && ngay3 <= 31 && thang3 == 2) {
                    txtCungGirl.setText("Song Ngư");
                }

                if (ngay3 >= 1 && ngay3 <= 20 && thang3 == 3) {
                    txtCungGirl.setText("Song Ngư");
                }
            }

    }

    private void doiNameGirl(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_name);
        final EditText editText=dialog.findViewById(R.id.txtName);
        Button thaydoi=dialog.findViewById(R.id.thaydoi);
        editText.setText(txtNameGirl.getText().toString().trim());
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")){
                    editText.setError(getString(R.string.error));
                    return;
                }
                if (editText.getText().toString().trim().length()>20){
                    editText.setError("Tối đa 20 kí tự");
                    return;
                }
                dataBase.updateTen(txtNameBoy.getText().toString().trim(),editText.getText().toString().trim(),1);
                getDataName();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void doiNameBoy(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_name);
        final EditText editText=dialog.findViewById(R.id.txtName);
        Button thaydoi=dialog.findViewById(R.id.thaydoi);
        editText.setText(txtNameBoy.getText().toString().trim());
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")){
                    editText.setError(getString(R.string.error));
                    return;
                }
                if (editText.getText().toString().trim().length()>20){
                    editText.setError("Tối đa 20 kí tự");
                    return;
                }
                dataBase.updateTen(editText.getText().toString().trim(),txtNameGirl.getText().toString().trim(),1);
                getDataName();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    private void doiHanhPhuc(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_name);
        final EditText editText=dialog.findViewById(R.id.txtName);
        Button thaydoi=dialog.findViewById(R.id.thaydoi);
        editText.setText(txtHanhPhuc.getText().toString().trim());
        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")){
                    editText.setError(getString(R.string.error));
                    return;
                }
                if (editText.getText().toString().trim().length()>20){
                    editText.setError("Tối đa 20 kí tự");
                    return;
                }
                txtHanhPhuc.setText(editText.getText().toString().trim());
                editor=sharedPreferences.edit();
                editor.putString("hanhphucx",editText.getText().toString().trim());
                editor.commit();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void themNgaySinhNhatNam() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_age);
        dialog.getActionBar();
        dialog.setCancelable(false);
        dialog.setTitle("Thêm");
        Button chonngay1 = dialog.findViewById(R.id.chonngay1);
        TextView txtLogo=dialog.findViewById(R.id.txtLogo);

        final EditText ngay1;
        Button luu = dialog.findViewById(R.id.luu);
        Button huy = dialog.findViewById(R.id.huybo);
        ngay1 = dialog.findViewById(R.id.ngaysinh1);

        ngay1.setEnabled(false);


        Animation animation=AnimationUtils.loadAnimation(getActivity(),R.anim.anim3);
        chonngay1.setAnimation(animation);
        ngay1.setAnimation(animation);

        luu.setAnimation(animation);
        huy.setAnimation(animation);
        Cursor b = baseNgaySinhNhat.getNam();

        if (b.moveToNext()) {
            int ngay = b.getInt(1);
            int thang = b.getInt(2);
            int nam = b.getInt(3);
            ngay1.setText(ngay + "-" + thang + "-" + nam + "(Bạn Nam)");
            txtLogo.setText("Cập nhật ngày sinh");
        }


        chonngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar3 = Calendar.getInstance();
                int ngay = calendar3.get(Calendar.DATE);
                int thang = calendar3.get(Calendar.MONTH);
                int nam = calendar3.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar3.set(i, i1, i2);
                                Cursor a = baseNgaySinhNhat.getNam();
                                if (!a.moveToNext()) {
                                    baseNgaySinhNhat.insertNgaySinhNhatNam(i2, i1 + 1, i);
                                } else {
                                    baseNgaySinhNhat.updateNgaySinhNhatNam(i2, i1 + 1, i, 1);
                                }
                                ngay1.setText(simpleDateFormat.format(calendar3.getTime()));

                            }
                        }, nam, thang, ngay);

                pickerDialog.show();
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.cancel();
            }
        });


        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay1.getText().toString().equals("") ) {
                    Toast.makeText(getActivity(), "Chưa có gì để thêm", Toast.LENGTH_SHORT).show();
                } else {

                    cungHoangDao();
                    dialog.dismiss();
                }

            }

        });

        dialog.show();


    }
    private void themNgaySinhNhatNu() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_age);
        dialog.getActionBar();
        dialog.setCancelable(false);
        dialog.setTitle("Thêm");
        Button chonngay1 = dialog.findViewById(R.id.chonngay1);

        final EditText ngay2;
        Button luu = dialog.findViewById(R.id.luu);
        Button huy = dialog.findViewById(R.id.huybo);
        ngay1 = dialog.findViewById(R.id.ngaysinh1);
        TextView txtLogo=dialog.findViewById(R.id.txtLogo);
        ngay1.setEnabled(false);

        Animation animation=AnimationUtils.loadAnimation(getActivity(),R.anim.anim3);
        chonngay1.setAnimation(animation);
        ngay1.setAnimation(animation);

        luu.setAnimation(animation);
        huy.setAnimation(animation);
        Cursor c = baseNgaySinhNhat.getNu();
        if (c.moveToNext()){
        int ngay3 = c.getInt(1);
        int thang3 = c.getInt(2);
        int nam3 = c.getInt(3);
        ngay1.setText(ngay3 + "-" + thang3 + "-" + nam3 + "(Bạn Nữ)");
        txtLogo.setText("Cập nhật ngày sinh");
        }

        chonngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar3 = Calendar.getInstance();
                int ngay = calendar3.get(Calendar.DATE);
                int thang = calendar3.get(Calendar.MONTH);
                int nam = calendar3.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar3.set(i, i1, i2);
                                Cursor a = baseNgaySinhNhat.getNu();
                                if (!a.moveToNext()) {
                                    baseNgaySinhNhat.insertNgaySinhNhatNu(i2, i1 + 1, i);
                                } else {
                                    baseNgaySinhNhat.updateNgaySinhNhatNu(i2, i1 + 1, i, 1);
                                }
                                ngay1.setText(simpleDateFormat.format(calendar3.getTime()));

                            }
                        }, nam, thang, ngay);

                pickerDialog.show();
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.cancel();
            }
        });


        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay1.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Chưa có gì để thêm", Toast.LENGTH_SHORT).show();
                } else {

                    cungHoangDao();
                    dialog.dismiss();
                }

            }

        });

        dialog.show();


    }

    public void updateNgayyeu() {
        cursor4 = dataBase.getNgayyeu();
        calendarTwo = Calendar.getInstance();
        int a1 = (int) (calendarTwo.getTimeInMillis() / (1000 * 60 * 60 * 24));
        if (cursor4.moveToNext()) {
            int a2 = cursor4.getInt(1);
            int a = a1 - a2;
            dataBase.updateSoNgayYeu(a, 1);

        }
        getDataDate();
        getData();
    }

    public void dialog_love(){
        final Dialog dialog=new Dialog(getActivity());
        editor=sharedPreferences.edit();
        dialog.setContentView(R.layout.dialog_trai_tim);
         CardView love1;
         CardView love3;
         CardView love6;
         CardView love2;
         CardView love4;
         CardView love5;

        love1 =  dialog.findViewById(R.id.love1);
        love3 =  dialog.findViewById(R.id.love3);
        love6 =  dialog.findViewById(R.id.love6);
        love2 =  dialog.findViewById(R.id.love2);
        love4 =  dialog.findViewById(R.id.love4);
        love5 =  dialog.findViewById(R.id.love5);

        love1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love1);
                editor.putInt("love",1);
                editor.commit();
                dialog.dismiss();
            }
        });

        love2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love2);
                editor.putInt("love",2);
                editor.commit();
                dialog.dismiss();
            }
        });


        love3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love3);
                editor.putInt("love",3);
                editor.commit();
                dialog.dismiss();
            }
        });


        love4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love4);
                editor.putInt("love",4);
                editor.commit();
                dialog.dismiss();
            }
        });


        love5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love5);
                editor.putInt("love",5);
                editor.commit();
                dialog.dismiss();
            }
        });

        love6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love.setImageResource(R.drawable.love6);
                editor.putInt("love",6);
                editor.commit();
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    public void lovex(){
        int i=sharedPreferences.getInt("love",0);
        if (i==0){
            love.setImageResource(R.drawable.love4);
        }
        if (i==1){
            love.setImageResource(R.drawable.love1);
        }
        if (i==2){
            love.setImageResource(R.drawable.love2);
        }
        if (i==3){
            love.setImageResource(R.drawable.love3);
        }
        if (i==4){
            love.setImageResource(R.drawable.love4);
        }
        if (i==5){
            love.setImageResource(R.drawable.love5);
        }
        if (i==6){
            love.setImageResource(R.drawable.love6);
        }
    }
    class RunAble implements Runnable{
        int seconds;
        public RunAble(int seconds) {
            this.seconds = seconds;
        }
        @Override
        public void run() {
            for (int i=0;i<=60;i++){
                Handler handler=new Handler(Looper.getMainLooper());
                final int intI=i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        txtgiay.setText(intI+"");

                        if (intI==60){
                            txtphut.setText(Integer.parseInt(txtphut.getText().toString().trim())+1+"");
                            RunAble  runAble1=new RunAble(5);
                            new Thread(runAble1).start();
                        }

                        if (Integer.parseInt(txtphut.getText().toString().trim())==60){
                            txtphut.setText(00+"");
                            txtgio.setText(Integer.parseInt(txtgio.getText().toString().trim())+1+"");
                            RunAble  runAble1=new RunAble(5);
                            new Thread(runAble1).start();
                        }

                        if (Integer.parseInt(txtgio.getText().toString().trim())==24){
                            txtgio.setText(00+"");
                            txtngay.setText(Integer.parseInt(txtngay.getText().toString().trim())+1+"");
                            RunAble  runAble1=new RunAble(5);
                            new Thread(runAble1).start();
                        }


                    }
                });



                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }



        }
    }
    public void getData(){
        Cursor cursor = dataBase.getSoNgayYeu();
        if (cursor.moveToNext()){
            int datelove=cursor.getInt(1);

            double so = datelove;
            double so1 = 365;

            double nam1 = so / so1;
            int nguyen = (int) nam1;
            txtnam.setText(nguyen+"");
            double sodu = so - (nguyen * so1);

            double thang1 = sodu / 30;
            int thangnguyen = (int) thang1;
            txtthang.setText(thangnguyen + "");

            double sodungay = sodu - (thangnguyen * 30);
            int sodungay1 = (int) sodungay;
            txtngay.setText(sodungay1 + "");
        }

    }

    public void tieude(){

        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_name);
        final EditText editText=dialog.findViewById(R.id.txtName);
        Button thaydoi=dialog.findViewById(R.id.thaydoi);
        editText.setText(txtNgayYeu.getText().toString().trim());

        thaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")){
                    editText.setError(getString(R.string.error));
                    return;
                }
                if (editText.getText().toString().trim().length()>20){
                    editText.setError("Tối đa 20 kí tự");
                    return;
                }
                Cursor cursor = dataBase.getSoNgayYeu();
                if (cursor.moveToNext()) {
                    int datelove = cursor.getInt(1);
                    if (datelove == 0) {
                        txtNgayYeu.setText("Start love");
                    } else {
                        txtNgayYeu.setText("\t\t" + String.valueOf(datelove) + "\tngày" + "\n\t" + editText.getText().toString().trim());
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }

    public void ngayyeu(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_age);
        dialog.getActionBar();
        dialog.setCancelable(false);
        dialog.setTitle("Sửa ngày yêu");
        Button chonngay1 = dialog.findViewById(R.id.chonngay1);
        TextView txtLogo=dialog.findViewById(R.id.txtLogo);

        final EditText ngay1;
        Button luu = dialog.findViewById(R.id.luu);
        Button huy = dialog.findViewById(R.id.huybo);
        ngay1 = dialog.findViewById(R.id.ngaysinh1);
        ngay1.setEnabled(false);
        chonngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar3 = Calendar.getInstance();
                int ngay = calendar3.get(Calendar.DATE);
                int thang = calendar3.get(Calendar.MONTH);
                int nam = calendar3.get(Calendar.YEAR);
                DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                calendar3.set(i, i1, i2);
                                ngay1.setText(simpleDateFormat.format(calendar3.getTime()));

                            }
                        }, nam, thang, ngay);

                pickerDialog.show();
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialog.cancel();
            }
        });

        final Calendar calendartr = Calendar.getInstance();
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngay1.getText().toString().equals("") ) {
                    Toast.makeText(getActivity(), "Chưa có gì để thêm", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor=dataBase.getNgayyeu();
                    if (!ngay1.getText().toString().trim().equals("")){
                        int ngayyeu = (int) (calendar3.getTimeInMillis() / (1000 * 60 * 60 * 24));
                        int ngay = (int) ((calendartr.getTimeInMillis() - calendar3.getTimeInMillis()) / (1000 * 60 * 60 * 24));
                        if (ngay<0){
                            ngay1.setText("Chưa yêu mà đã tính rồi");
                        }else {
                            dataBase.updateSoNgayYeu(ngay, 1);
                            dataBase.updateNgayYeu(ngayyeu, 1);
                            getDataDate();
                            getData();
                            dialog.dismiss();
                        }


                    }

                }

            }

        });
   dialog.show();





    }

    public void popNoidung(){
        PopupMenu popupMenu=new PopupMenu(getActivity(),loaddingWave);
        popupMenu.getMenuInflater().inflate(R.menu.menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.noidung:
                        tieude();
                        break;
                    case R.id.ngayyeu:
                        ngayyeu();
                        break;
                }
                return true;
            }
        });
popupMenu.show();

    }

    public void toolbarx(){
        tolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(getActivity(),tolbar);
                popupMenu.getMenuInflater().inflate(R.menu.menu_tym,popupMenu.getMenu());
                editor=sharedPreferences.edit();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.hong:
                                editor.putInt("maulove",1);
                                editor.commit();
                                clickPick();
                                break;
                            case R.id.tim:
                                editor.putInt("maulove",2);
                                editor.commit();
                                clickTim();
                                break;
                            case R.id.vang:
                                editor.putInt("maulove",3);
                                editor.commit();
                                clickVang();
                                break;
                            case R.id.nau:
                                editor.putInt("maulove",4);
                                editor.commit();
                                clickNau();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    void clickPick(){
        imgLove.setImageResource(R.drawable.love_hong);
        imgLove1.setImageResource(R.drawable.love_hong);
        imgLove2.setImageResource(R.drawable.love_hong);
        imgLove3.setImageResource(R.drawable.love_hong);
        imgLove4.setImageResource(R.drawable.love_hong);
        imgLove5.setImageResource(R.drawable.love_hong);
    }

    void clickTim(){
        imgLove.setImageResource(R.drawable.icon_tim);
        imgLove1.setImageResource(R.drawable.icon_tim);
        imgLove2.setImageResource(R.drawable.icon_tim);
        imgLove3.setImageResource(R.drawable.icon_tim);
        imgLove4.setImageResource(R.drawable.icon_tim);
        imgLove5.setImageResource(R.drawable.icon_tim);
    }

    void clickVang(){
        imgLove.setImageResource(R.drawable.love_vang);
        imgLove1.setImageResource(R.drawable.love_vang);
        imgLove2.setImageResource(R.drawable.love_vang);
        imgLove3.setImageResource(R.drawable.love_vang);
        imgLove4.setImageResource(R.drawable.love_vang);
        imgLove5.setImageResource(R.drawable.love_vang);
    }

    void clickNau(){
        imgLove.setImageResource(R.drawable.love_nau);
        imgLove1.setImageResource(R.drawable.love_nau);
        imgLove2.setImageResource(R.drawable.love_nau);
        imgLove3.setImageResource(R.drawable.love_nau);
        imgLove4.setImageResource(R.drawable.love_nau);
        imgLove5.setImageResource(R.drawable.love_nau);
    }

    void laymau(){
        int i=sharedPreferences.getInt("maulove",1);
        if (i==1){
            clickPick();
        }
        if (i==2){
            clickTim();
        }
        if (i==3){
            clickVang();
        }
        if (i==4){
            clickNau();
        }
    }

}
