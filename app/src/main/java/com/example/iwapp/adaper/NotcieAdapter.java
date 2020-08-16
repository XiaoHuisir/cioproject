package com.example.iwapp.adaper;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.iwapp.R;
import com.example.iwapp.base.BaseAdapter;
import com.example.iwapp.bean.NotcieRecordBean;

import java.util.List;

public class NotcieAdapter extends BaseAdapter {
    public NotcielinnotItemClick itemClick;
    public NottongItemClick tongitemClick;

    public NotcieAdapter(List<NotcieRecordBean.DataBean.NoPassDataBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.notcie_adapter;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ImageView imageNotcie = (ImageView) holder.getView(R.id.image_notcie);
        TextView txt_xinx = (TextView) holder.getView(R.id.txt_xinx);
        TextView txt_notname = (TextView) holder.getView(R.id.txt_notname);
        TextView txt_fraction = (TextView) holder.getView(R.id.txt_fraction);
        TextView txt_nottiem = (TextView) holder.getView(R.id.txt_nottiem);
        TextView txt_tong = (TextView) holder.getView(R.id.txt_tong);

        NotcieRecordBean.DataBean.NoPassDataBean data = (NotcieRecordBean.DataBean.NoPassDataBean) mDatas.get(positon);
        Glide.with(mContext).load(data.getLog()).into(imageNotcie);
        txt_xinx.setText(data.getTitle());
        txt_fraction.setText(String.valueOf(data.getFraction()) + "分");
        int type = data.getType();
        switch (type) {
            case 1:
                txt_notname.setText("课外学习");
                break;
            case 2:
                txt_notname.setText("培训课程");
                break;
            case 3:
                break;
        }

        LinearLayout lin_not = (LinearLayout) holder.getView(R.id.lin_not);
        LinearLayout lin_tong = (LinearLayout) holder.getView(R.id.lin_tong);
//        ConstraintLayout layout = (ConstraintLayout) holder.getView(R.id.layout_item);
        lin_not.setTag(String.valueOf(data.getCurriculum_id()));
        lin_not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (itemClick != null) {
                    itemClick.clicks(id);
                }
            }
        });
        lin_tong.setTag(String.valueOf(data.getEvaluat_id()));

        lin_tong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = (String) v.getTag();
                if (tongitemClick != null) {
                    tongitemClick.tongclick(id);
                }
            }
        });
    }

    public interface NotcielinnotItemClick {
        void clicks(String id);
    }

    public interface NottongItemClick {

        void tongclick(String id);
    }

}
