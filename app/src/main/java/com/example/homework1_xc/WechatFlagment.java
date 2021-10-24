package com.example.homework1_xc;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WechatFlagment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private List<Myadapter.Card> datas=new ArrayList<>();
    private Myadapter myadapter;

    public int[] firstStaggeredGridPosition={0,0};
    public int[] lastStaggeredGridPosition={0,0};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.wechat_flagment, container, false);

        List<Myadapter.Card> datas=new ArrayList<>();
        datas.add(new Myadapter.Card("乖乖",R.drawable.q1, 960, 720));
        datas.add(new Myadapter.Card("乖乖",R.drawable.q2, 960, 720));
        datas.add(new Myadapter.Card("秋",R.drawable.q4, 720, 960));
        datas.add(new Myadapter.Card("家乡",R.drawable.q3, 960, 720));
        datas.add(new Myadapter.Card("余晖",R.drawable.q5, 960, 960));
        datas.add(new Myadapter.Card("乡间小道",R.drawable.q6, 960, 720));
        datas.add(new Myadapter.Card("相见",R.drawable.q7, 960, 720));
        datas.add(new Myadapter.Card("朦胧感",R.drawable.q8, 960, 720));
        datas.add(new Myadapter.Card("艳丽",R.drawable.q9, 960, 720));
        datas.add(new Myadapter.Card("奇观",R.drawable.q10, 1080, 1080));
        datas.add(new Myadapter.Card("烟花",R.drawable.q11, 960, 720));
        datas.add(new Myadapter.Card("烟花",R.drawable.q12, 960, 720));


        recyclerView=view.findViewById(R.id.recyclerview);

        //getActivity是获取当前的Context对象
        //myadapter=new Myadapter(getActivity(),datas);
        myadapter = new Myadapter(getActivity(),datas);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myadapter);

        //设置Myadapter的Item监听
        myadapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, List<Myadapter.Card> cards) {
                /* 跳转至另一个activity */
                Intent intent=new Intent(getActivity(),CardInfoActivity.class);
                //传递相应的参数
                //我们需要把构成一个图片的信息传递过去
                Bundle bundle=new Bundle();
                bundle.putInt("cardImageId",cards.get(Position).getImageId());
                bundle.putString("cardTitle",cards.get(Position).getTitle());
                bundle.putInt("height",cards.get(Position).getHeight());
                bundle.putInt("width",cards.get(Position).getWidth());
                intent.putExtras(bundle);

                //启用共享组件的activity过渡
                //所选择的共享元件，这个元件是当前页面的元件
                //获取item的ViewHolder
                Log.d("myposition-firstPo", Arrays.toString(firstStaggeredGridPosition));
                Log.d("myposition-actPo", String.valueOf(Position));
                Log.d("myposition-lastPo", Arrays.toString(lastStaggeredGridPosition));

                //因为我使用的是StaggeredGridLayoutManager
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //由于瀑布流是两列，这里是为了获得处在页面的最小值
                int realFirstPosition=Math.min(firstStaggeredGridPosition[0],firstStaggeredGridPosition[1]);
                Myadapter.MyViewHolder viewHolder=(Myadapter.MyViewHolder)recyclerView.getChildViewHolder(recyclerView.getChildAt(Position-realFirstPosition));

                ImageView card_info_image= viewHolder.inserimage;
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                        Pair.create(card_info_image, "card_info_image"));
                startActivity(intent,options.toBundle());
            }
        });
        //为recycleView设置adapter
        recyclerView.setAdapter(myadapter);

        //设置recycleView的滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE || newState == SCROLL_STATE_DRAGGING) {
                    // DES: 找出当前可视Item位置
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof StaggeredGridLayoutManager) {
                        Log.d("myposition-ac","scrool");
                        StaggeredGridLayoutManager linearManager = (StaggeredGridLayoutManager) layoutManager;
                        linearManager.findFirstVisibleItemPositions(firstStaggeredGridPosition);
                        linearManager.findLastVisibleItemPositions(lastStaggeredGridPosition);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


        return view;
    }
}
