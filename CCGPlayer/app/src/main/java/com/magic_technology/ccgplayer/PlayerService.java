package com.magic_technology.ccgplayer;


import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * Created by Administrator on 2015/7/16.
 */
public class PlayerService extends Service{
    public static boolean isMusicOn = false;

    public final String MUSIC_NAME ="MusicName";
    public final String SINGER   ="Singer";
    public final String MUSIC_ID ="MusicId";
    public final String ATTRIBUTE ="Attribute";
    public final String MUSIC_AMOUNT="music_amount";
    public final String MUSIC_CODE ="music_code";



    private boolean isPlayer_Exist=false;
    private Binder binder=new Binder();

    private MediaPlayer mPlayer;




    @Override
    public IBinder onBind(Intent arg0)
    {
        return binder;
    }
    @Override
    public void onCreate()
    {
        super.onCreate();
//        SharedPreferences music_Id = getSharedPreferences(MUSIC_ID, 0);
//        long current_music_id = music_Id.getLong(MUSIC_CODE + MainInterface.current_music_pos, 0);
//
//
//        Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, current_music_id);
//
//        mPlayer = new MediaPlayer();
//        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
//            mPlayer.setDataSource(getApplicationContext(), contentUri);
//            mPlayer.prepare();

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mPlayer.start();
//        isMusicOn=true;


    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        //获取当前需要播放的音乐信息
        if(intent.getIntExtra("Controller",1)==1||intent.getIntExtra("Controller",1)==2) {
            SharedPreferences music_Name = getSharedPreferences(MUSIC_NAME, 0);
            String name = music_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, MUSIC_NAME);
            SharedPreferences singer_Name = getSharedPreferences(SINGER, 0);
            String singer = singer_Name.getString(MUSIC_CODE + MainInterface.current_music_pos, SINGER);
            SharedPreferences music_Id = getSharedPreferences(MUSIC_ID, 0);
            long current_music_id = music_Id.getLong(MUSIC_CODE + MainInterface.current_music_pos, 0);


            Uri contentUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, current_music_id);
            if(isPlayer_Exist)
                mPlayer.release();
            mPlayer = new MediaPlayer();
            isPlayer_Exist=true;
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mPlayer.setDataSource(getApplicationContext(), contentUri);
                mPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mPlayer.start();;
        }
        else if(intent.getIntExtra("Controller",1)==0)
        {
            if (!intent.getBooleanExtra("isMusic_On", false)) {
                mPlayer.start();
                isMusicOn=true;
            } else {
                mPlayer.pause();
                isMusicOn=false;
            }
        }

        return  START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(isPlayer_Exist)
            mPlayer.release();
    }




}
