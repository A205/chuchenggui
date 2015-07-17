package com.magic_technology.ccgplayer;


import android.app.LauncherActivity;
import android.app.Service;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/13.
 */
//此活动负责获取手机内音乐列表、显示并调整播放列表、选取要播放的音乐并跳到PlayerService界面
public class MainInterface extends TabActivity{
    public static long current_music_id;//记录当前播放音乐的ID，这是和其他activity以及service沟通的接口一；另一个是SharedPreferences文件
    public static int current_music_pos=0;
    public final String MUSIC_NAME ="MusicName";
    public final String SINGER   ="Singer";
    public final String MUSIC_ID ="MusicId";
    public final String ATTRIBUTE ="Attribute";
    public final String MUSIC_AMOUNT="music_amount";
    public final String MUSIC_CODE ="music_code";
    public final String PRIVATE_LIST = "private_list";
    private Button btn_play;//播放/暂停 按钮
    private Button btn_next;//下一首 按钮
    private TextView playing_text;//现实当前音乐名称的文本框
    private MediaPlayer mPlayer;//
    private String[] music_Names;//用于记录歌曲名称
    private String[] singer;//用于记录歌手名称
    private int[] imageIds;//用于记录音乐对应图标
    private long[] music_id;//用于记录音乐的ID
    private boolean music_on=false;//用于记录音乐是否正在播放，true表示正在播放
    private boolean isPlayer_exist=true;//用于记录MediaPlayer实例是否存在
    private Button btn_addtolist;
    private boolean isPlayerActivityOn_List1=false;
    private boolean isPlayerActivityOn_List2=false;
    Binder binder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };




    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);//设置此活动对应的界面

        //以下一段是标签页设置
        //获取该Activity里面的TabHost组件
        TabHost tabHost = getTabHost();
        //创建第一个Tab页
        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1")
                .setIndicator("我的音乐")//设置标题
                .setContent(R.id.tab01);//设置内容
        //添加第一个标签页
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2=tabHost.newTabSpec("tab2")
                .setIndicator("收藏夹")
                .setContent(R.id.tab02);
        //添加第二个标签页
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3=tabHost.newTabSpec("tab3")
                .setIndicator("在线音乐")
                .setContent(R.id.tab03);
        //添加第三个标签页
        tabHost.addTab(tab3);


//        playing_text = (TextView)findViewById(R.id.name_of_music);//将文本框引用和播放条文本框实例链接
//        btn_play = (Button)findViewById(R.id.btn_play_pause);
//        btn_next = (Button)findViewById(R.id.btn_next);
//


        //使用Content Resolver调取手机内部的音乐资源，并生成资源名称数组和ID数组
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri,null,null,null,null);
        if(cursor == null)
        {

            //playing_text =(TextView)findViewById(R.id.name_of_music);
//            playing_text.setText("bad requst");

        }
        else if(!cursor.moveToFirst())
        {
            //playing_text =(TextView)findViewById(R.id.name_of_music);
//            playing_text.setText("bad requst 2");


        }
        else
        {

            int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int idColumn = cursor.getColumnIndex((android.provider.MediaStore.Audio.Media._ID));
            int singerColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//            playing_text = (TextView)findViewById(R.id.name_of_music);
            int musicAmount=0;//用于记录歌曲数目

            SharedPreferences nameList =getSharedPreferences(MUSIC_NAME, 0);
            SharedPreferences.Editor editor1 = nameList.edit();

            SharedPreferences singerList = getSharedPreferences(SINGER, 0);
            SharedPreferences.Editor editor2 = singerList.edit();

            SharedPreferences idList=getSharedPreferences(MUSIC_ID, 0);
            SharedPreferences.Editor editor3 = idList.edit();

            for(int i=0;cursor.moveToNext();i++)
            {

                musicAmount+=1;
                String music_code =MUSIC_CODE+i;
                long thisId = cursor.getLong(idColumn);//获取音乐资源ID
                String  thisTitle = cursor.getString(titleColumn);//获取资源名称
                String singer_tempt = cursor.getString(singerColumn);//获取歌手信息

                editor1.putString(music_code,thisTitle);
                editor2.putString(music_code, singer_tempt);
                editor3.putLong(music_code, thisId);
            }
            editor1.apply();
            editor2.apply();
            editor3.apply();
            music_Names = new String[musicAmount];
            singer = new String[musicAmount];
            music_id = new long[musicAmount];
            imageIds = new int[musicAmount];
            for(int i=0; i<musicAmount;i++)
            {
                singer[i]=singerList.getString(MUSIC_CODE+i,SINGER);//将歌手名导入歌手名数组
                music_id[i]=idList.getLong(MUSIC_CODE+i,0);//将资源ID导入音乐ID数组
                music_Names[i]=nameList.getString(MUSIC_CODE+i,MUSIC_NAME);//将资源名称导入音乐数组

            }

            SharedPreferences attribute = getSharedPreferences(ATTRIBUTE,0);
            SharedPreferences.Editor editor= attribute.edit();
            editor.putInt(MUSIC_AMOUNT,musicAmount);
            editor.commit();
        }




        //初始化当前播放歌曲
//        current_music_id=music_id[0];
//        playing_text.setText(music_Names[0]+"  "+singer[0]);
//        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,current_music_id);
//        mPlayer=new MediaPlayer();
//        isPlayer_exist=true;
//        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try
//        {
//            mPlayer.setDataSource(getApplicationContext(),contentUri);
//            mPlayer.prepare();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }



        //设置ListView

        //创建一个List集合，List集合的元素是Map
        //设

        //
        List<Map<String,Object>> listItems =
                new ArrayList<Map<String,Object>>();
        for(int i=0;i < music_Names.length; i++)
        {
            Map<String,Object> listItem = new HashMap<String, Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("singer",singer[i]);
            listItem.put("music", music_Names[i]);
            listItems.add(listItem);

        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[]{"music","header","singer"},
                new int[] {R.id.music,R.id.header,R.id.singer});
        ListView list_1 = (ListView)findViewById(R.id.list_1);
        list_1.setAdapter(simpleAdapter);

       // mPlayer=new MediaPlayer();//创建一个MediaPlayer实例

        //添加列表项  单击 事件响应
        Intent serviceIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("Controller",1);
        serviceIntent.putExtras(bundle);
        serviceIntent.setAction("org.MagicTechnology.service.First_service");
        bindService(serviceIntent,connection, Service.BIND_AUTO_CREATE);
        list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                current_music_id=music_id[position];
                current_music_pos=position;
                //修改部分，应该通过单击列表项，启动PlayerService和PlayerInterface，并将音乐的ID传给PlayerService，
                //启动service
                Intent serviceIntent = new Intent();
                Bundle bundle = new Bundle();

                bundle.putInt("Controller",2);
                serviceIntent.putExtras(bundle);
                serviceIntent.setAction("org.MagicTechnology.service.First_service");
                startService(serviceIntent);

                //启动PlayerInterface activity
                if(isPlayerActivityOn_List1)
                    finishActivity(1);
                isPlayerActivityOn_List1=true;
                bundle = new Bundle();
                bundle.putLong("current_music_id", current_music_id);
                bundle.putInt("position", position);
                bundle.putInt("ListNumber",1);
                Intent intent = new Intent(MainInterface.this,PlayerInterface.class);
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
                //将本activity与service绑定



                //.......

//                Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,current_music_id);
//                if(isPalyer_exist)
//                {
//                    mPlayer.release();
//                    mPlayer = new MediaPlayer();
//                }
//                //mPlayer=new MediaPlayer
//                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try
//                {
//                    mPlayer.setDataSource(getApplicationContext(),contentUri);
//                    mPlayer.prepare();
//                    playing_text.setText(music_Names[position] + "  " + singer[position]);
//                    btn_play.setBackgroundResource(R.drawable.ic_play);
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                mPlayer.start();
//                music_on=true;

            }
        });


        //设置第二个标签页的列表
        List<Map<String,Object>> listItems_2 =
                new ArrayList<Map<String,Object>>();
        SharedPreferences attribute=getSharedPreferences(ATTRIBUTE, 0);
        SharedPreferences privateList =getSharedPreferences(PRIVATE_LIST,0);
        final int musicAmount = attribute.getInt(MUSIC_AMOUNT,0);//记录个人列表中的乐曲数目
        if(musicAmount!=0)
        {
            int privateList_musicAmount=0;

            for(int i=0;i < musicAmount; i++) {
                if(privateList.getBoolean(MUSIC_CODE+i,false))
                {
                    Map<String, Object> listItem = new HashMap<String, Object>();
//            listItem.put("header",imageIds[i]);
//            listItem.put("singer",singer[i]);
//            listItem.put("music", music_Names[i]);
//            listItems_2.add(listItem);
                    String music_Code = MUSIC_CODE + i;
                    SharedPreferences nameList = getSharedPreferences(MUSIC_NAME, 0);
                    listItem.put("music", nameList.getString(music_Code, MUSIC_NAME));
                    SharedPreferences singerList = getSharedPreferences(SINGER, 0);
                    listItem.put("singer", singerList.getString(music_Code, SINGER));
                    // SharedPreferences idList = getSharedPreferences(MUSIC_ID,0);
                    listItem.put("header", imageIds[i]);
                    listItems_2.add(listItem);
                    privateList_musicAmount +=1;
                }
            }
            SharedPreferences.Editor editor=attribute.edit();
            editor.putInt("privateList_musicAmount",privateList_musicAmount);
            editor.commit();



        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter_2 = new SimpleAdapter(this,listItems_2,
                R.layout.simple_item2,
                new String[]{"music","header","singer"},
                new int[] {R.id.music2,R.id.header2,R.id.singer2});
        ListView list_2 = (ListView)findViewById(R.id.list_2);
        list_2.setAdapter(simpleAdapter_2);
        //添加第二个列表的列表项的响应事件
        list_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SharedPreferences privateList = getSharedPreferences(PRIVATE_LIST,0);

                //通过position计算歌曲在总表中的位置：
                current_music_pos =0;
                int flag=0;
                for(int i=0;i<musicAmount;i++)
                {

                    if(flag== position)
                    {
                        current_music_pos = i;
                        break;
                    }
                    if(privateList.getBoolean(MUSIC_CODE + i, false))
                        flag +=1;


                }

                //启动PlayerService
                Intent serviceIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("Controller",2);
                serviceIntent.putExtras(bundle);
                serviceIntent.setAction("org.MagicTechnology.service.First_service");
                startService(serviceIntent);

                //启动PlayerInterface activity
                if(isPlayerActivityOn_List1)
                    finishActivity(1);
                isPlayerActivityOn_List1=true;
//                bundle = new Bundle();
//                bundle.putLong("current_music_id", current_music_id);
//                bundle.putInt("position",position);
                Intent intent = new Intent(MainInterface.this,PlayerInterface.class);
                intent.putExtra("ListNumber",2);
                startActivityForResult(intent,1);
//                int i=0;
//                int tempt=0;
//                for(i = 0; i < musicAmount; i++)
//                {
//                    if(privateList.getBoolean(MUSIC_CODE+i,false))
//                        tempt +=1;
//                    if(tempt ==position)
//                        break;
//                }
//
//                current_music_id=music_id[tempt];
//                //setContentView(R.layout.player_interface);
//                //修改部分
//                if(isPlayerActivityOn_List2)
//                    finishActivity(2);
//                isPlayerActivityOn_List2=true;
//                Bundle bundle = new Bundle();
//                bundle.putLong("current_music_id",current_music_id);
//                bundle.putInt("position",position);
//                Intent intent = new Intent(MainInterface.this,PlayerInterface.class);
//                intent.putExtras(bundle);
//                startActivityForResult(intent,2);

//                Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,current_music_id);
//                if(isPlayer_exist)
//                {
//                    mPlayer.release();
//                    mPlayer = new MediaPlayer();
//                }
//                //mPlayer=new MediaPlayer
//                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try
//                {
//                    mPlayer.setDataSource(getApplicationContext(),contentUri);
//                    mPlayer.prepare();
//                    playing_text.setText(music_Names[position] + "  " + singer[position]);
//                    btn_play.setBackgroundResource(R.drawable.ic_play);
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//                mPlayer.start();
//                music_on=true;

            }
        });



        //添加按钮的事件响应程序



       // mPlayer =MediaPlayer.create(this,R.raw.until);
//        btn_play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //添加代码控制音乐播放和暂停
//                if (!music_on) {
//                    btn_play.setBackgroundResource(R.drawable.ic_play);
//                    mPlayer.start();
//                    music_on = true;
//                } else {
//                    mPlayer.pause();
//                    btn_play.setBackgroundResource(R.drawable.ic_pause);
//                    music_on = false;
//
//                }
//            }
//        });
//
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                current_music_pos = (current_music_pos + 1) % 50;
//                current_music_id = music_id[current_music_pos];
//                if (isPlayer_exist) {
//                    mPlayer.release();
//                    mPlayer = new MediaPlayer();
//                } else {
//                    mPlayer = new MediaPlayer();
//                }
//                Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, current_music_id);
//                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                try {
//                    mPlayer.setDataSource(getApplicationContext(), contentUri);
//                    mPlayer.prepare();
//                    playing_text.setText(music_Names[current_music_pos] + "  " + singer[current_music_pos]);
//                    btn_play.setBackgroundResource(R.drawable.ic_play);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                mPlayer.start();
//                //添加代码，实现选取下一首音乐并播放。
//
//            }
//        });
        btn_addtolist = (Button)findViewById(R.id.btn_addtolist);
        btn_addtolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInterface.this,Activity_ChangeList.class);
                startActivityForResult(intent, 0);
            }
        });




    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent intent)
    {
        if(requestCode == 0 && resultCode == 0)
        {
            List<Map<String,Object>> listItems_2 =
                    new ArrayList<Map<String,Object>>();
            SharedPreferences attribute=getSharedPreferences(ATTRIBUTE, 0);
            SharedPreferences privateList =getSharedPreferences(PRIVATE_LIST, 0);
            int musicAmount = attribute.getInt(MUSIC_AMOUNT, 0);//记录个人列表中的乐曲数目

            if(musicAmount!=0) {
                int privateList_musicAmount = 0;

                for (int i = 0; i < musicAmount; i++) {
                    if (privateList.getBoolean(MUSIC_CODE + i, false)) {
                        Map<String, Object> listItem = new HashMap<String, Object>();
//            listItem.put("header",imageIds[i]);
//            listItem.put("singer",singer[i]);
//            listItem.put("music", music_Names[i]);
//            listItems_2.add(listItem);
                        String music_Code = MUSIC_CODE + i;
                        SharedPreferences nameList = getSharedPreferences(MUSIC_NAME, 0);
                        listItem.put("music", nameList.getString(music_Code, MUSIC_NAME));
                        SharedPreferences singerList = getSharedPreferences(SINGER, 0);
                        listItem.put("singer", singerList.getString(music_Code, SINGER));
                        // SharedPreferences idList = getSharedPreferences(MUSIC_ID,0);
                        listItem.put("header", imageIds[i]);
                        listItems_2.add(listItem);
                        privateList_musicAmount += 1;
                    }
                }

                SimpleAdapter simpleAdapter_2 = new SimpleAdapter(this, listItems_2,
                        R.layout.simple_item2,
                        new String[]{"music", "header", "singer"},
                        new int[]{R.id.music2, R.id.header2, R.id.singer2});
                ListView list_2 = (ListView) findViewById(R.id.list_2);
                list_2.setAdapter(simpleAdapter_2);
            }

        }
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Intent intent = new Intent();
        intent.setAction("org.MagicTechnology.service.First_service");
        stopService(intent);
        finish();
    }

}
