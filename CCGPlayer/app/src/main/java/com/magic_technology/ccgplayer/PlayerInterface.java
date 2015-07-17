package com.magic_technology.ccgplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/13.
 */
//此活动不负责播放音乐，只负责显示播放界面和控制播放，实际播放由PlayService负责
public class PlayerInterface extends Activity{
    public final String MUSIC_NAME ="MusicName";
    public final String SINGER   ="Singer";
    public final String MUSIC_ID ="MusicId";
    public final String ATTRIBUTE ="Attribute";
    public final String MUSIC_AMOUNT="music_amount";
    public final String MUSIC_CODE ="music_code";
    public final String PRIVATE_LIST = "private_list";

    private boolean music_on=false;
    private boolean isPlayer_exist=false;
    private boolean isMusic_On=false;
    private MediaPlayer mPlayer;
    private Binder binder;
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
        setContentView(R.layout.player_interface);


        final TextView playing_text = (TextView)findViewById(R.id.playing_text);
        final Button btn_play = (Button)findViewById(R.id.btn2_play_pause);
        final Button btn_next = (Button)findViewById(R.id.btn2_next);
        final Button btn_previous = (Button)findViewById((R.id.btn2_previous));

        //初始化播放/暂停按钮状态
        if(PlayerService.isMusicOn)
            btn_play.setBackgroundResource(R.drawable.ic_pause);
        else
            btn_play.setBackgroundResource(R.drawable.ic_play);

        //从SharedPreferences文件中读取ID对应的歌曲信息；
        final SharedPreferences music_Name = getSharedPreferences(MUSIC_NAME, 0);
        String name = music_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, MUSIC_NAME);
        final SharedPreferences singer_Name = getSharedPreferences(SINGER, 0);
        final String singer = singer_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, SINGER);
        final SharedPreferences private_list = getSharedPreferences(PRIVATE_LIST, 0);
        final SharedPreferences attribute = getSharedPreferences(ATTRIBUTE,0);
        final int musicAmount = attribute.getInt(MUSIC_AMOUNT,0);


//        if(music_on)
//        {
//            mPlayer.pause();
//            mPlayer.release();
//        }
//        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, current_music_id);
//        if(isPlayer_exist)
//        {
//            mPlayer.release();
//            mPlayer = new MediaPlayer();
//        }
//        mPlayer=new MediaPlayer();
//        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try
//        {
//            mPlayer.setDataSource(getApplicationContext(),contentUri);
//            mPlayer.prepare();
//            playing_text.setText(name + "  " + singer);
//            btn_play.setBackgroundResource(R.drawable.ic_play);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        mPlayer.start();
//        music_on=true;

        //设置播放按钮响应事件
        final Intent intent1 = new Intent();
        intent1.setAction("org.MagicTechnology.service.First_service");

        //
        playing_text.setText(name + "  " + singer);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加代码控制音乐播放和暂停
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
                intent1.putExtra("Controller", 0);//将控制设置为播放/暂停
                Bundle bundle = new Bundle();
                if (isMusic_On) {
                    isMusic_On = false;
                    bundle.putBoolean("isMusic_On", isMusic_On);
                    intent1.putExtras(bundle);
                    btn_play.setBackgroundResource(R.drawable.ic_play);
                    startService(intent1);
                } else {
                    isMusic_On = true;
                    bundle.putBoolean("isMusic_On", isMusic_On);
                    intent1.putExtras(bundle);
                    btn_play.setBackgroundResource(R.drawable.ic_pause);
                    startService(intent1);
                }

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从主界面获取音乐信息
                Intent intent= getIntent();
                intent1.putExtra("Controller",1);
                if(intent.getIntExtra("ListNumber",1)==1)
                {
                    MainInterface.current_music_pos = (MainInterface.current_music_pos+musicAmount+1)%musicAmount;
                    startService(intent1);
                }
                else if(intent.getIntExtra("ListNumber",1)==2)
                {
                    for(int j=MainInterface.current_music_pos;j<musicAmount;j++)
                    {
                        MainInterface.current_music_pos =(MainInterface.current_music_pos+musicAmount+1)%musicAmount;
                        if(private_list.getBoolean(MUSIC_CODE+j,false))
                            break;
                    }
                    startService(intent1);
                }
                playing_text.setText(music_Name.getString(MUSIC_CODE+MainInterface.current_music_pos, MUSIC_CODE)+
                        "  "+singer_Name.getString(MUSIC_CODE+MainInterface.current_music_pos,MUSIC_CODE));
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从主界面获取音乐信息
                Intent intent = getIntent();
                intent1.putExtra("Controller", 1);
                if (intent.getIntExtra("ListNumber", 1) == 1) {
                    MainInterface.current_music_pos=(MainInterface.current_music_pos+musicAmount-1)%musicAmount;
                    startService(intent1);
                }
                else if (intent.getIntExtra("ListNumber", 1) == 2)
                {
                    for (int j = MainInterface.current_music_pos; j < musicAmount; j--) {
                        MainInterface.current_music_pos =(MainInterface.current_music_pos+musicAmount-1)%musicAmount;
                        if (private_list.getBoolean(MUSIC_CODE + j, false))
                            break;
                    }
                    startService(intent1);
                }
                playing_text.setText(music_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, MUSIC_CODE) +
                        "  " + singer_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, MUSIC_CODE));
            }

        });



//        ListView list_2 = (ListView) findViewById(R.id.list_1);
//        list_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mPlayer.pause();
//                mPlayer.release();
//                finish();
//            }
//        });




    }
//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        mPlayer.pause();
//        mPlayer.release();
//    };

}

