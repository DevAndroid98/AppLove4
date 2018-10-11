package com.tinh.dev.applove;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tinh.dev.applove.adapter.NhatKiAdapter;
import com.tinh.dev.applove.dataBase.DataBaseNhatKi;
import com.tinh.dev.applove.model.NhatKi;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Diary_Fragment extends Fragment {
    private RecyclerView recyclerView;
    private NhatKiAdapter nhatKiAdapter;
    private ArrayList<NhatKi> nhatKis;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton floatingActionButton;
    private DataBaseNhatKi dataBaseNhatKi;
    private int REQUEST_CODE_FOLDER = 123;
    private  Button addImg;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.diary_fragment,container,false);
        recyclerView=view.findViewById(R.id.recyclerviewNhatKi);
        floatingActionButton=view.findViewById(R.id.floatbutton);
        dataBaseNhatKi=new DataBaseNhatKi(getActivity());
        floatingActionButton.setImageResource(R.drawable.add);
        nhatKis=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        anhXa();
        addNhatKi();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
        return view;
    }

    private void anhXa(){

        nhatKis.clear();
        Cursor cursor=dataBaseNhatKi.getData();
        if (cursor!=null&& cursor.moveToFirst()){
            do {
                NhatKi nhatKi = new NhatKi();
                String date=cursor.getString(1);
                String text=cursor.getString(2);
                String uri=cursor.getString(3);
                nhatKi.setDate(date);
                nhatKi.setNhatki(text);
                nhatKi.setUri(uri);
                nhatKis.add(nhatKi);

            }while (cursor.moveToNext());
            nhatKiAdapter=new NhatKiAdapter(nhatKis,getActivity(),Diary_Fragment.this);
            nhatKiAdapter.notifyDataSetChanged();
        }

    }

    private void insert(){

        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_nhatki);
        final EditText ngaykiniem;
        final EditText textkiniem;

        Button luu;
        Button huybo;
        ngaykiniem =  dialog.findViewById(R.id.ngaykiniem);
        textkiniem =  dialog.findViewById(R.id.textkiniem);
        luu =  dialog.findViewById(R.id.luu);
        addImg =  dialog.findViewById(R.id.addImg);
        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonhanh();
            }
        });
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ngaykiniem.getText().toString().trim().equals("")){
                    ngaykiniem.setError(getString(R.string.error));
                    return;
                }
                if (textkiniem.getText().toString().trim().equals("")){
                    textkiniem.setError(getString(R.string.error));
                    return;
                }

                dataBaseNhatKi.insert(ngaykiniem.getText().toString().trim(),textkiniem.getText().toString().trim(),addImg.getText().toString().trim());
                //nhatKis.add(0,new NhatKi(ngaykiniem.getText().toString().trim(),1,addImg.getText().toString().trim(),textkiniem.getText().toString().trim()));
                anhXa();
                addNhatKi();
                dialog.dismiss();


            }
        });

        huybo =  dialog.findViewById(R.id.huybo);
        huybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    private void addNhatKi(){

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(nhatKiAdapter);

    }

    public void chonhanh(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            addImg.setText(uri+"");
            }
    }

    public void delete(final int i, String date){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa kỉ niệm ngày\t"+date+"\tkhông?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nhatKis.remove(i);
                dataBaseNhatKi.delete(i);
                addNhatKi();
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

