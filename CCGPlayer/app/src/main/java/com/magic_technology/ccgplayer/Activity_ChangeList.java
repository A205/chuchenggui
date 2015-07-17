package com.magic_technology.ccgplayer;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/15.
 */
public class Activity_ChangeList extends Activity{
    public final String MUSIC_NAME ="MusicName";
    public final String SINGER   ="Singer";
    public final String MUSIC_ID ="MusicId";
    public final String ATTRIBUTE ="Attribute";
    public final String MUSIC_AMOUNT="music_amount";
    public final String MUSIC_CODE ="music_code";
    public final String PRIVATE_LIST = "private_list";
    private int[] imageIds;
    private boolean isModified=false;
    private boolean[] isSelected;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelist);


        //设置 所有音乐 ListView显示
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        SharedPreferences attribute = getSharedPreferences(ATTRIBUTE, 0);
        final int musicAmount = attribute.getInt(MUSIC_AMOUNT, 0);//记录个人列表中的乐曲数目

        imageIds = new int[musicAmount];
        SharedPreferences nameList = getSharedPreferences(MUSIC_NAME, 0);
        SharedPreferences singerList = getSharedPreferences(SINGER, 0);

        if (musicAmount != 0) {
            for (int i = 0; i < musicAmount; i++) {

                Map<String, Object> listItem = new HashMap<String, Object>();
//            listItem.put("header",imageIds[i]);
//            listItem.put("singer",singer[i]);
//            listItem.put("music", music_Names[i]);
//            listItems_2.add(listItem);
                String music_Code = MUSIC_CODE + i;

                listItem.put("music", nameList.getString(music_Code, MUSIC_NAME));

                listItem.put("singer", singerList.getString(music_Code, SINGER));
                // SharedPreferences idList = getSharedPreferences(MUSIC_ID,0);
                listItem.put("header", imageIds[i]);
                listItems.add(listItem);
            }


        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.simple_item2,
                new String[]{"music", "header", "singer"},
                new int[]{R.id.music2, R.id.header2, R.id.singer2});
        ListView list_1 = (ListView) findViewById(R.id.list_for_change);
        list_1.setAdapter(simpleAdapter);


        //打开SharedPreference资源
        final SharedPreferences private_list = getSharedPreferences(PRIVATE_LIST, 0);

        //初始化列表，默认为所有的歌曲都未被选取
        isSelected = new boolean[musicAmount];
        for (int i = 0; i < musicAmount; i++)
            isSelected[i] = private_list.getBoolean(MUSIC_CODE + i, false);

        //添加ListView列表项单击事件响应
        list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //添加事件响应,如果歌曲不在私人列表内，便将其加入列表，否则，从列表中剔除

                if (!isSelected[position]) {
                    view.setBackgroundResource(R.color.primary_dark_material_dark);
                    isSelected[position] = true;
                } else {
                    view.setBackgroundResource(R.color.primary_dark_material_light);
                    isSelected[position] = false;
                }
                isModified = true;


            }
        });


        //添加“确定”按钮事件响应，改变个人列表内容，返回主界面
        Button btn_yes = (Button) findViewById(R.id.save_change);
        final SharedPreferences.Editor editor = private_list.edit();
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isModified)
                    for (int i = 0; i < musicAmount; i++) {
                        editor.putBoolean(MUSIC_CODE + i, isSelected[i]);
                    }

                editor.apply();
                //Intent intent = new Intent(Activity_ChangeList.this,MainInterface.class);
                //Activity_ChangeList.this.startActivity(intent);
                Activity_ChangeList.this.setResult(0);
                Activity_ChangeList.this.finish();
            }
        });


        //添加“取消”按钮事件响应，不改变列表内容，返回主界面
        Button btn_no = (Button) findViewById(R.id.give_up);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Activity_ChangeList.this,MainInterface.class);
                //Activity_ChangeList.this.startActivity(intent);
                Activity_ChangeList.this.finish();
            }
        });


    }
}






