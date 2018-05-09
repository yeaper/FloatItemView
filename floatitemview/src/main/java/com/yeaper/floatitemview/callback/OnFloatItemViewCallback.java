package com.yeaper.floatitemview.callback;

/**
 * 悬浮item接口回调
 * Created by yeape on 2018/5/9.
 */
public interface OnFloatItemViewCallback {

    /**
     * 展示数据之前，进行一些初始化操作
     */
    void onStart();

    /**
     * 更新悬浮item的内容
     * @param position
     */
    void updateFloatBar(int position);
}
