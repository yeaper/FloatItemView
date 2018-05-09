package com.yeaper.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeaper.sample.bean.Friend;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeape on 2018/5/8.
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendHolder> {

    private List<Friend> dataList = new ArrayList<>();
    private Context context;

    public FriendAdapter(Context context){
        this.context = context;
    }

    public void setDataList(List<Friend> list){
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FriendHolder(LayoutInflater.from(context).inflate(R.layout.item_friend, parent, false));
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {
        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class FriendHolder extends RecyclerView.ViewHolder{

        private ImageView avatar;
        private TextView name;
        private TextView describe;

        public FriendHolder(View itemView) {
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            describe = (TextView) itemView.findViewById(R.id.describe);
        }

        public void bindData(Friend friend){
            if(friend.getGender() == 1){
                avatar.setImageResource(R.drawable.boy);
            }else{
                avatar.setImageResource(R.drawable.girl);
            }
            name.setText(friend.getName());
            describe.setText(friend.getDescribe());
        }
    }
}
