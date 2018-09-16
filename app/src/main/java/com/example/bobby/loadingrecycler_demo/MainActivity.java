package com.example.bobby.loadingrecycler_demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list;
    private loadingAdapter adapter;
    private RecyclerView recyclerView;
    int lastVisibleItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        recyclerView=(RecyclerView)findViewById(R.id.loadingRecyclerview);
        list=new ArrayList<>();
        adapter=new loadingAdapter(this,list);
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        getData();
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 ==adapter.getItemCount())
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getData();
                        }
                    }, 2000);
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                lastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }
    private void getData()
    {
        for (int i=0;i<15;i++)
        {
            list.add("我是第"+i);
        }
        adapter.notifyDataSetChanged();
    }
}
