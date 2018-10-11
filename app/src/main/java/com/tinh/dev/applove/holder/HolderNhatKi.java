package com.tinh.dev.applove.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinh.dev.applove.R;

public class HolderNhatKi extends RecyclerView.ViewHolder {
    public TextView  ngay1;
    public ImageView anh;
    public TextView nhatki;
    public HolderNhatKi(View itemView) {
        super(itemView);
        ngay1=itemView.findViewById(R.id.txtNhatKiDate);
        nhatki=itemView.findViewById(R.id.txtNhatKi);
        anh=itemView.findViewById(R.id.img_anh);

    }
}
