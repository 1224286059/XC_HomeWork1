package com.example.homework1_xc;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class SetFlagment extends Fragment {
    private RecyclerView recyclerView;
    private Context context;
    private List<Myadapter.Card> datas=new ArrayList<>();
    private Myadapter myadapter;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.set_flagment, container, false);

        return view;
    }
}
