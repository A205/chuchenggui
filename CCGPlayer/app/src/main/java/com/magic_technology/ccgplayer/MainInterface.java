package com.magic_technology.ccgplayer;


import android.app.TabActivity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

/**
 * Created by Administrator on 2015/7/13.
 */
public class MainInterface extends TabActivity{
    private Button btn_play;
    private Button btn_next;
    private boolean flag=false;
    private MediaPlayer mPlayer = new MediaPlayer();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);
        //获取该Activity里面的TabHost组件
        TabHost tabHost = getTabHost();
        //创建第一个Tab页
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("favor")//设置标题
                .setContent(R.id.tab01);//设置内容
        //添加第一个标签页
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("tab2")
                .setIndicator("well")
                .setContent(R.id.tab02);
        //添加第二个标签页
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3=tabHost.newTabSpec("tab3")
                .setIndicator("lulu")
                .setContent(R.id.tab03);
        //添加第三个标签页
        tabHost.addTab(tab3);





        //添加按钮的事件响应程序

        btn_play = (Button)findViewById(R.id.btn_play_pause);
        btn_next = (Button)findViewById(R.id.btn_next);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加代码控制音乐播放和暂停


                if(!flag)
                {


                    mPlayer.create(MainInterface.this,R.raw.apple);
                    btn_play.setBackgroundResource(R.drawable.ic_play);
                    mPlayer.start();
                    flag=true;
                }
                else
                {
                    mPlayer.stop();

                    btn_play.setBackgroundResource(R.drawable.ic_pause);
                    flag=false;
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加代码，实现选取下一首音乐并播放。

            }
        });




    }

}
