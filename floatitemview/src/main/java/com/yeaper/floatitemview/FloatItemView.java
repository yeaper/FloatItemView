package com.yeaper.floatitemview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yeaper.floatitemview.callback.OnFloatItemViewCallback;

/**
 * item悬浮控件
 * Created by yeape on 2018/5/8.
 */
public class FloatItemView extends FrameLayout {

    private Context context;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FrameLayout mFloatBar;

    private int mCurrentPosition = 0;
    private int floatBarH;


    public FloatItemView(@NonNull Context context) {
        this(context ,null);
    }

    public FloatItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        initView();
    }

    private void initView(){
        View v = LayoutInflater.from(context).inflate(R.layout.layout_float_item_view, null);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rl_list);
        mFloatBar = (FrameLayout) v.findViewById(R.id.float_bar);

        linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        addView(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * 设置自定义的FloatBar
     * @param floatBar
     * @return
     */
    public FloatItemView setFloatBar(View floatBar){
        mFloatBar.addView(floatBar, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return this;
    }

    /**
     * 适配数据，并回调相关接口
     * @param adapter
     * @param callback
     */
    public void setAdapter(RecyclerView.Adapter adapter, final OnFloatItemViewCallback callback){
        if(mFloatBar != null && callback != null){
            callback.onStart();
        }else{
            return;
        }
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                floatBarH = mFloatBar.getHeight();
            }
            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view != null) {
                    // 将item顶出界面
                    if (view.getTop() <= floatBarH) {
                        mFloatBar.setY(-(floatBarH - view.getTop()));
                    } else {
                        mFloatBar.setY(0);
                    }
                }
                // 当前一个item出界面后，更新悬浮item为当前第一个可见的item
                if (mCurrentPosition != linearLayoutManager.findFirstVisibleItemPosition()) {
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mFloatBar.setY(0);
                    callback.updateFloatBar(mCurrentPosition);
                }

            }
        });
    }

}
