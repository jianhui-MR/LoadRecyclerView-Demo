package com.example.bobby.loadingrecycler_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class loadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> mList;
    static final int TYPE_ITEM = 0;
    static final int TYPE_FOOTER = 1;
    static final int LOADING_MORE = 1;
    static final int NO_MORE = 2;
    int footer_state = 1;
    private RecyclerView.ViewHolder holder;
    private int position;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.text);
        }
    }
    public class FootHolder extends RecyclerView.ViewHolder
    {
        TextView footTextview;
        public FootHolder(View itemView) {
            super(itemView);
            footTextview=(TextView)itemView.findViewById(R.id.footText);
        }
    }
    public loadingAdapter(Context mcontext,List<String> stringList){
        context=mcontext;
        mList=stringList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount())
            return TYPE_FOOTER;
        else
            return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (context==null)
         context=parent.getContext();
        if (viewType==TYPE_FOOTER)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.foot_item,parent,false);
            return new FootHolder(view);
        }
        else{
            View view= LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        this.holder = holder;
        this.position = position;
        if (holder instanceof ViewHolder)
            ((ViewHolder)holder).textView.setText(mList.get(position));
        else if (holder instanceof FootHolder) {
            FootHolder footHolder = (FootHolder) holder;
            if (position==0)
                footHolder.footTextview.setVisibility(View.GONE);
            else
                footHolder.footTextview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()+1;
    }

}
