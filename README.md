# FloatItemView

RecyclerView 上下滑动时，item可以实现悬浮在顶部，很多通讯录的名字分类也是类似


运行效果如下图：

![效果演示](https://github.com/yeaper/FloatItemView/blob/master/showApp.gif)


1. 使用

先在布局文件下,加入控件

```xml
<com.yeaper.floatitemview.FloatItemView
        android:id="@+id/float_item_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

然后自定义一个悬浮布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="center_vertical"
    android:background="#fff">

    <ImageView
        android:id="@+id/avatar"
        android:layout_width="32dp"
        android:layout_height="32dp"
        android:src="@color/colorPrimary"
        android:layout_margin="15dp"/>

    <TextView
        android:id="@+id/name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="15sp"
        android:textColor="#000000"/>

</LinearLayout>
```

再获取悬浮布局

```java
floatBar = LayoutInflater.from(this).inflate(R.layout.layout_float_bar, null, false);
avatar = (ImageView) floatBar.findViewById(R.id.avatar);
name = (TextView) floatBar.findViewById(R.id.name);
```

现在就可以给FloatItemView设置悬浮的布局，并且设置adapter

OnFloatItemViewCallback接口中有两个方法
 - onStart()：适配数据前的初始化工作
 - updateFloatBar(int position)：悬浮item被顶出界面后，需要进行更新操作，也就是显示下一个item
 
```java
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
```
