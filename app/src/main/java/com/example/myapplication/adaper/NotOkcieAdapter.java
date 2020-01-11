package com.example.myapplication.adaper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.base.BaseAdapter;
import com.example.myapplication.bean.NotcieRecordBean;

import java.util.List;

public class NotOkcieAdapter extends BaseAdapter {
    public NotokItemClick itemokClick;
    public NottongokItemClick tongokitemClick;
    public NotOkcieAdapter(List<NotcieRecordBean.DataBean.PassDataBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notok_adpater;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {

        ImageView imageNotcie = (ImageView) holder.getView(R.id.image_notcieok);
        TextView txt_xinx = (TextView) holder.getView(R.id.txt_xinxok);
        TextView txt_notname = (TextView) holder.getView(R.id.txt_notnameok);
        TextView txt_fraction = (TextView) holder.getView(R.id.txt_fractionok);
        TextView txt_nottiem = (TextView) holder.getView(R.id.txt_nottiemok);
        TextView txt_tong = (TextView) holder.getView(R.id.txt_tongok);

        NotcieRecordBean.DataBean.PassDataBean data = (NotcieRecordBean.DataBean.PassDataBean) mDatas.get(positon);
        Glide.with(mContext).load(data.getLog()).into(imageNotcie);
        txt_xinx.setText(data.getTitle());
        txt_fraction.setText(String.valueOf(data.getFraction()) + "分");


        LinearLayout linbs = (LinearLayout) holder.getView(R.id.linok);
        linbs.setTag(String.valueOf(data.getCurriculum_id()));
        linbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (itemokClick != null) {
                    itemokClick.clickok(id);
                }
            }
        });
        LinearLayout lin_tongok = (LinearLayout) holder.getView(R.id.lin_tongok);
        lin_tongok.setTag(String.valueOf(data.getEvaluat_id()));
        lin_tongok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (tongokitemClick != null) {
                    tongokitemClick.tongclickok(id);
                }
            }
        });



    }
    public interface NotokItemClick {
        void clickok(String id);
    }

    public interface NottongokItemClick {

        void tongclickok(String id);
    }

}
