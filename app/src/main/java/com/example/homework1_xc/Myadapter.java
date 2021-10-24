package com.example.homework1_xc;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter <Myadapter.MyViewHolder> {
    private View view;
    private Context context;
    private List<Card> datas;

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    //定义监听的接口
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    //定义一个方法可以直接提供调用
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener){
        this.onRecyclerItemClickListener=onRecyclerItemClickListener;
    }

    public Myadapter(Context context, List<Card> datas) {

        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card data;
        data = datas.get(position);
        //System.out.println(data.toString());
        //放入照片
        holder.inserimage.setImageResource(data.getImageId());
        ViewGroup.LayoutParams params = holder.inserimage.getLayoutParams();
        //TODO 显然，这高度是由这个参数决定的，如果我们知道了宽的大小width，那么我们就能知道实际缩放比
        int screenWidth = ScreenUtil.getScreenWidth(context);   //获取屏幕的宽度
        //Log.d("height",String.valueOf(screenWidth));
        // 调整放入图片的大小，保证宽一定是屏幕的一半，高度随着缩放而改变
        float scale = (float) data.getHeight() / (float) data.getWidth();
        params.height = (int) (screenWidth / 2 * scale);//设置图片的参数
        holder.inserimage.setLayoutParams(params);
        holder.inserimage_title.setText(data.getTitle());//放入文字
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView inserimage;
        TextView inserimage_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            inserimage = itemView.findViewById(R.id.card_image);
            inserimage_title = itemView.findViewById(R.id.card_title);

            //对itemView监听
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerItemClickListener!=null){
                        //可以发现，点击后获取的是某个数据的Position，那么自然可以找到对应的cards信息
                        onRecyclerItemClickListener.onItemClick(getAdapterPosition(),datas);
                    }
                }
            });
        }
    }

    public static class Card {
        private String title;
        private int imageId;
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }
        public int getHeight() {
            return height;
        }
        public String getTitle() {
            return title;
        }

        public int getImageId() {
            return imageId;
        }

        public Card(String title, int imageId, int width, int height) {
            this.title = title;
            this.imageId = imageId;
            this.width = width;
            this.height = height;
            //Log.d("imageId",String.valueOf(imageId));
        }

    }
}
//自定义监听接口
interface OnRecyclerItemClickListener {
    //RecyclerView的点击事件，将信息回调给view

    void onItemClick(int Position, List<Myadapter.Card> datas);
}
