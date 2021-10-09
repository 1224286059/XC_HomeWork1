package com.example.homework1_xc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearWx,linearMF,linearBM,linearS;
    private ImageView imgWx,imgTxl,imgBM,imgW,imgCur;
    private TextView textWx,textTxl,textFx,textW,textCur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initFun();
        addFragment(new SetFlagment());
        imgWx.setSelected(true);
        textWx.setSelected(true);
    }
    private void initFun(){
        linearWx = findViewById(R.id.wx_linear);
        linearWx.setOnClickListener(this);
        linearMF = findViewById(R.id.txl_linear);
        linearMF.setOnClickListener(this);
        linearBM = findViewById(R.id.fx_linear);
        linearBM.setOnClickListener(this);
        linearS = findViewById(R.id.w_linear);
        linearS.setOnClickListener(this);

        imgWx = findViewById(R.id.imageView);
        imgTxl = findViewById(R.id.imageView2);
        imgBM = findViewById(R.id.imageView3);
        imgW = findViewById(R.id.imageView4);
        imgCur = findViewById(R.id.imageView);

        textWx = findViewById(R.id.textView);
        textTxl = findViewById(R.id.textView2);
        textFx = findViewById(R.id.textView3);
        textW = findViewById(R.id.textView4);
        textCur = findViewById(R.id.textView);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.wx_linear:
                changeFragment(new BookmarkFragment());
                colorChange(1);
                break;
            case R.id.txl_linear:
                changeFragment(new MessageFlagment());
                colorChange(2);
                break;
            case R.id.fx_linear:
                changeFragment(new WechatFlagment());
                colorChange(3);
                break;
            case R.id.w_linear:
                changeFragment(new SetFlagment());
                colorChange(4);
                break;
            default:
                break;
        }

    }

    private void colorChange(int id){
        textCur.setSelected(false);
        imgCur.setSelected(false);
        switch (id){
            case 1:
                imgWx.setSelected(true);
                imgCur=imgWx;
                textWx.setSelected(true);
                textCur=textWx;
                break;
            case 2:
                imgTxl.setSelected(true);
                imgCur=imgTxl;
                textTxl.setSelected(true);
                textCur=textTxl;
                break;
            case 3:
                imgBM.setSelected(true);
                imgCur=imgBM;
                textFx.setSelected(true);
                textCur=textFx;
                break;
            case 4:
                imgW.setSelected(true);
                imgCur=imgW;
                textW.setSelected(true);
                textCur=textW;
                break;
            default:
                break;

        }
    }

    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.body, fragment);
        transaction.commit();
    }

    private void addFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.body, fragment);
        transaction.commit();
    }
}