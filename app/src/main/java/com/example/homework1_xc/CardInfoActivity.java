package com.example.homework1_xc;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//这个activity是用来展示对应card信息的activity
public class CardInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_info);
        //获取传递来的信息
        ImageView card_info_image=(ImageView) findViewById(R.id.card_info_image);
        TextView card_info_title=(TextView) findViewById(R.id.card_info_title);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        card_info_image.setImageResource(bundle.getInt("cardImageId"));
        card_info_title.setText(bundle.getString("cardTitle"));
        //修改图片的高度
        ViewGroup.LayoutParams params = card_info_image.getLayoutParams();
        //TODO 显然，这高度是由这个参数决定的，如果我们知道了宽的大小width，那么我们就能知道实际缩放比
        //获取屏幕的宽度
        int screenWidth = ScreenUtil.getScreenWidth(this);
        //Log.d("height",String.valueOf(screenWidth));
        //调整放入图片的大小，保证宽一定是屏幕的一半，高度随着缩放而改变
        float scale = (float)bundle.getInt("height") / (float)bundle.getInt("width");
        params.height =  (int) (screenWidth * scale)+200;
        card_info_image.setLayoutParams(params);
    }
}
