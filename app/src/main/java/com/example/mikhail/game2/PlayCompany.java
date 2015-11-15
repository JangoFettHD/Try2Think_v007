package com.example.mikhail.game2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class PlayCompany extends AppCompatActivity {

    private TextView view_levels;
    private TextView view_coins;
    private ImageView imageView;

    public static int i=0;

    public static final String T2T_mbd = "mbd";
    public static final String T2T_mbd_lvl = "lvl";
    public static final String T2T_mbd_tryc = "tryc";
    public static SharedPreferences mbd;

/*
    public static String base[][] = new String[][]{
            {"подумай", "1"}, {"михаил", "1"},{"пирожок", "1"},{"4+5=9", "2"}
    };
    public static int baseDRAW[]=new int[]
            {R.drawable.lvl1 , R.drawable.lvl2 , R.drawable.lvl3, R.drawable.ch2_lvl1};
*/
    private Button b_back;
    private static EditText vvod;
    private Button b_next;

    private static int tryC=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_company);

        mbd = getSharedPreferences(T2T_mbd, Context.MODE_PRIVATE);

        imageView = (ImageView)findViewById(R.id.imageView);
        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));

        b_back = (Button)findViewById(R.id.b_back);
        vvod = (EditText)findViewById(R.id.vvod);
        b_next = (Button)findViewById(R.id.b_next);

        if (i==DataBase.templvl.length) {
            Toast.makeText(this, "Вы прошли всю компанию", Toast.LENGTH_SHORT).show();
            Intent goMenu = new Intent(PlayCompany.this, MainActivity.class);
            startActivity(goMenu);
        }
        }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mbd.edit();
        editor.putInt(T2T_mbd_lvl, i);
        editor.putInt(T2T_mbd_tryc, tryC);
        editor.apply();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mbd.contains(T2T_mbd_lvl) ||
                mbd.contains(T2T_mbd_tryc) ) {
            i = mbd.getInt(T2T_mbd_lvl, 0);
            tryC = mbd.getInt(T2T_mbd_tryc, 0);
        }
        if (i==DataBase.templvl.length) {
            Toast.makeText(this, "Вы прошли всю компанию", Toast.LENGTH_SHORT).show();
            Intent goMenu = new Intent(PlayCompany.this, MainActivity.class);
            startActivity(goMenu);
        }
        if (i!=DataBase.templvl.length) {imageView.setImageResource((DataBase.templvl[i]).image);}
    }

    public void win(){
        if (((DataBase.templvl[i]).ch)==1){MainActivity.levels_ch1 +=1;}
        if (((DataBase.templvl[i]).ch)==2){MainActivity.levels_ch2 +=1;}
        if (((DataBase.templvl[i]).ch)==2){MainActivity.levels_ch3 +=1;}
        if (tryC==0){
            MainActivity.coins += 50;
            Toast.makeText(this, "Правильно! +50 монет!", Toast.LENGTH_SHORT).show();}
        if (tryC==1){
            MainActivity.coins += 25;
            Toast.makeText(this, "Правильно! +25 монет!", Toast.LENGTH_SHORT).show();}
        if (tryC>1){
            MainActivity.coins += 10;
            Toast.makeText(this, "Правильно! +10 монет!", Toast.LENGTH_SHORT).show();}
        MainActivity.savesettings();
        MainActivity.loadsettings();
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_summ));
        tryC=0;
            i++;
        if (i==DataBase.templvl.length) {
            Toast.makeText(this, "Поздравляем! Вы прошли всю компанию!", Toast.LENGTH_SHORT).show();
            Intent goMenu = new Intent(PlayCompany.this, MainActivity.class);
            startActivity(goMenu);
        } else{imageView.setImageResource((DataBase.templvl[i]).image);}
        vvod.setText("");
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.b_next:

                    if (Arrays.asList((DataBase.templvl[i]).answer).contains(vvod.getText().toString().replaceAll(" ", "").toLowerCase())) {
                        win();
                    }else if (vvod.getText().length() == 0) {
                        Toast.makeText(this, "Введи ответ!", Toast.LENGTH_SHORT).show();
                    } else {
                        tryC+=1;
                        Toast.makeText(this, "Неправильно! Подумай еще!", Toast.LENGTH_SHORT).show();
                    }

                break;
            case R.id.b_menu:
                Intent goMenu = new Intent(PlayCompany.this, MainActivity.class);
                startActivity(goMenu);
                break;
            case R.id.b_back:
                Intent goPrewLvl = new Intent(PlayCompany.this, MainActivity.class);
                startActivity(goPrewLvl);
                break;
            case R.id.b_help:
                Toast.makeText(this, "супер пупер текст подсказки", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
