package com.example.myapplication.ui.acivity.pdf;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PdfActivity extends AppCompatActivity {

    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_title)
    ConstraintLayout layoutTitle;
    @BindView(R.id.pdfview)
    PDFView pdfView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pad_info);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        String pdfPath = getIntent().getStringExtra("pdf_path");
        String name = getIntent().getStringExtra("name");
        txtTitle.setText(name);
        if(!TextUtils.isEmpty(pdfPath)){
            File file = new File(pdfPath);
            pdfView.fromFile(file).defaultPage(0)
                    .enableAnnotationRendering(true)
                    .swipeHorizontal(false)
                    .spacing(10)
                    .load();
        }
    }

    @OnClick({R.id.img_close})
    public void OnClick(){
        finish();
    }
}
