package com.example.myapplication.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.bean.LoginBean;

import java.util.ArrayList;


public class Loig_adaler extends RecyclerView.Adapter<Loig_adaler.ViewHodler> {
    private ArrayList<LoginBean.DataBean> nei;
    private Context context;

    public Loig_adaler(ArrayList<LoginBean.DataBean> nei, Context context) {
        this.nei = nei;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.itme, null);
        ViewHodler hodler = new ViewHodler(inflate);
        return hodler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler viewHodler, int i) {
        /*viewHodler.tv_name.setText(nei.get(i).getChapterName());
        viewHodler.tv_id.setText(nei.get(i).getId()+"");*/
    }

    @Override
    public int getItemCount() {
        return nei.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_id;

        public ViewHodler(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_id=itemView.findViewById(R.id.tv_id);
        }
    }
}
