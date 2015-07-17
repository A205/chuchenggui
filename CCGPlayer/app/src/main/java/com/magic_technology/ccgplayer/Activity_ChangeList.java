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


        //���� �������� ListView��ʾ
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        SharedPreferences attribute = getSharedPreferences(ATTRIBUTE, 0);
        final int musicAmount = attribute.getInt(MUSIC_AMOUNT, 0);//��¼�����б��е�������Ŀ

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
        //����һ��SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.simple_item2,
                new String[]{"music", "header", "singer"},
                new int[]{R.id.music2, R.id.header2, R.id.singer2});
        ListView list_1 = (ListView) findViewById(R.id.list_for_change);
        list_1.setAdapter(simpleAdapter);


        //��SharedPreference��Դ
        final SharedPreferences private_list = getSharedPreferences(PRIVATE_LIST, 0);

        //��ʼ���б�Ĭ��Ϊ���еĸ�����δ��ѡȡ
        isSelected = new boolean[musicAmount];
        for (int i = 0; i < musicAmount; i++)
            isSelected[i] = private_list.getBoolean(MUSIC_CODE + i, false);

        //���ListView�б�����¼���Ӧ
        list_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //����¼���Ӧ,�����������˽���б��ڣ��㽫������б����򣬴��б����޳�

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


        //��ӡ�ȷ������ť�¼���Ӧ���ı�����б����ݣ�����������
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


        //��ӡ�ȡ������ť�¼���Ӧ�����ı��б����ݣ�����������
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






