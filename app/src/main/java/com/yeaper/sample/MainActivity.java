package com.yeaper.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeaper.floatitemview.FloatItemView;
import com.yeaper.floatitemview.callback.OnFloatItemViewCallback;
import com.yeaper.sample.bean.Friend;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FriendAdapter adapter;
    private List<Friend> dataList;

    private FloatItemView floatItemView;

    private View floatBar;
    private ImageView avatar;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();

    }

    private void initView(){
        floatItemView = (FloatItemView) findViewById(R.id.float_item_view);

        floatBar = LayoutInflater.from(this).inflate(R.layout.layout_float_bar, null, false);
        avatar = (ImageView) floatBar.findViewById(R.id.avatar);
        name = (TextView) floatBar.findViewById(R.id.name);

        adapter = new FriendAdapter(this);
    }

    private void loadData(){
        dataList = new ArrayList<>();
        for(int i=0;i<20;i++){
            if(i%4 == 0){
                dataList.add(new Friend("Yeo"+i, 1, "我是Yeo"+i+",来自西安"));
            }else if(i%4 == 1){
                dataList.add(new Friend("Dilly"+i, 0, "我是Dilly"+i+",来自兰州"));
            }else if(i%4 == 2){
                dataList.add(new Friend("Jone"+i, 1, "我是Jone"+i+",来自杭州"));
            }else{
                dataList.add(new Friend("Timi"+i, 0, "我是Timi"+i+",来自三亚"));
            }
        }
        adapter.setDataList(dataList);
        floatItemView.setFloatBar(floatBar)
                .setAdapter(adapter, new OnFloatItemViewCallback() {

                    @Override
                    public void onStart() {
                        if(dataList.get(0).getGender() == 1){
                            avatar.setImageResource(R.drawable.boy);
                        }else{
                            avatar.setImageResource(R.drawable.girl);
                        }
                        name.setText(dataList.get(0).getName());
                    }

                    @Override
                    public void updateFloatBar(int position) {
                        if(dataList.get(position).getGender() == 1){
                            avatar.setImageResource(R.drawable.boy);
                        }else{
                            avatar.setImageResource(R.drawable.girl);
                        }
                        name.setText(dataList.get(position).getName());
                    }
                });
    }
}
