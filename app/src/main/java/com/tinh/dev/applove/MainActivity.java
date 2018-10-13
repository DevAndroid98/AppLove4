package com.tinh.dev.applove;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.tinh.dev.applove.PassWord.CreatPass;
import com.tinh.dev.applove.adapter.FragmentAdapter;
import com.tinh.dev.applove.dataBase.DataBase;
import com.tinh.dev.applove.service.Brosd;
import com.tinh.dev.applove.service.ChatHeadService;
import com.tinh.dev.applove.service.DongHoService;
import com.tinh.dev.applove.service.MusicService;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager viewPaer;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private TabLayout tab;
    private int STORAGE_PERMISSION_CODE = 1;
    private NavigationView navigationView;
    private DataBase dataBase;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean t = false;
    private String uribackground, uriGirl, uriBoy, hp;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView=findViewById(R.id.navigation);
        drawerLayout=findViewById(R.id.draw);
        dataBase=new DataBase(this);
        sharedPreferences =getSharedPreferences("Data", Context.MODE_PRIVATE);
        uribackground = sharedPreferences.getString("uribackground", "");
        uriBoy = sharedPreferences.getString("uriBoy", "");
        uriGirl = sharedPreferences.getString("uriGirl", "");
        navigationView.setNavigationItemSelectedListener(this);
        mapped();
        setquyen();



    }

    private void mapped(){
       viewPaer=findViewById(R.id.viewPaer);
       tab=findViewById(R.id.tab);

       fragmentPagerAdapter=new FragmentAdapter(getSupportFragmentManager());
       viewPaer.setAdapter(fragmentPagerAdapter);
       tab.setupWithViewPager(viewPaer);


       }

       private void setquyen(){
           if (ContextCompat.checkSelfPermission(MainActivity.this,
                   Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
              // Toast.makeText(MainActivity.this, "You have already granted this permission!",
                      // Toast.LENGTH_SHORT).show();
           } else {
               requestStoragePermission();
           }
       }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.thongbao:
                    this.sendBroadcast(new Intent(MainActivity.this, Brosd.class));
                    break;
                case R.id.amnhac:
                    Dialog dialog = new Dialog(this);
                    editor = sharedPreferences.edit();
                    dialog.setContentView(R.layout.dialog_nhac);
                    final ImageView nhac = dialog.findViewById(R.id.nhac);
                    int x = sharedPreferences.getInt("nhac", 0);
                    if (x == 1) {
                        nhac.setImageResource(R.drawable.stop);
                        t = true;
                    } else if (x == 2) {
                        nhac.setImageResource(R.drawable.start);
                        t = false;
                    }
                    nhac.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (t == false) {
                                startService(new Intent(MainActivity.this, MusicService.class));
                                nhac.setImageResource(R.drawable.stop);
                                editor.putInt("nhac", 1);
                                editor.commit();
                                t = true;
                            } else {
                                stopService(new Intent(MainActivity.this, MusicService.class));
                                nhac.setImageResource(R.drawable.start);
                                editor.putInt("nhac", 2);
                                editor.commit();
                                t = false;
                            }


                        }
                    });
                    dialog.show();
                    break;
                case R.id.cuasonoi:
                    Intent intent = new Intent(this, ChatHeadService.class);
                    intent.putExtra("a", uribackground);
                    intent.putExtra("b", uriBoy);
                    intent.putExtra("c", uriGirl);
                    startService(intent);

                    break;
                case R.id.dongho:
                    startService(new Intent(this, DongHoService.class));
                    break;
                case R.id.exit:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Thoát");
                    builder.setMessage("Bạn có muốn thoát không?");
                    builder.setIcon(R.drawable.icons8_cancel_48);
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
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
                    editor = sharedPreferences.edit();
                    int a = sharedPreferences.getInt("matkhau", 1);
                    if (a == 1) {
                        editor.putInt("matkhau", 2);
                        editor.commit();
                        startActivity(new Intent(this, CreatPass.class));
                    } else {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                        builder1.setTitle("Thông báo");
                        builder1.setMessage("Bạn có muốn tắt mật khẩu không?");
                        builder1.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataBase.updatetPassWord(2, 1);
                                editor.putInt("matkhau", 1);
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
            drawerLayout.closeDrawer(Gravity.START);
            return true;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thoát");
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setIcon(R.drawable.icons8_cancel_48);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
