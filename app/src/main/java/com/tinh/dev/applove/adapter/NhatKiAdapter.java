package com.tinh.dev.applove.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tinh.dev.applove.Diary_Fragment;
import com.tinh.dev.applove.HomeFragment;
import com.tinh.dev.applove.R;
import com.tinh.dev.applove.holder.HolderNhatKi;
import com.tinh.dev.applove.model.NhatKi;

import java.util.ArrayList;

public class NhatKiAdapter extends RecyclerView.Adapter<HolderNhatKi> {
    private ArrayList<NhatKi> nhatKis;
    private Context context;
    private Diary_Fragment homeFragment;

    public NhatKiAdapter(ArrayList<NhatKi> nhatKis, Context context, Diary_Fragment homeFragment) {
        this.nhatKis = nhatKis;
        this.context = context;
        this.homeFragment = homeFragment;
    }

    @NonNull
    @Override
    public HolderNhatKi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cardview_nhatki,parent,false);
        Animation animation= AnimationUtils.loadAnimation(context,R.anim.anim4);
        view.setAnimation(animation);
        return new HolderNhatKi(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderNhatKi holder, final int position) {
        final NhatKi nhatKi=nhatKis.get(position);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(),"font_holidays.ttf");
        holder.nhatki.setTypeface(typeface);
        holder.ngay1.setTypeface(typeface);
        holder.ngay1.setText("❤-"+nhatKi.getDate());
        holder.nhatki.setText(nhatKi.getNhatki());
        holder.anh.setImageURI(Uri.parse(Uri.decode(nhatKi.getUri())));
        holder.anh.setBackgroundResource(R.drawable.style8);


    }

    @Override
    public int getItemCount() {
        return nhatKis.size();
    }
}
