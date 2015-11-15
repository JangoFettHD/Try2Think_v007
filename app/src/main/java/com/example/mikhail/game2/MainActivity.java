package com.example.mikhail.game2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int levels_ch1=0;
    public static int levels_ch2=0;
    public static int levels_ch3=0;
    public static int levels_summ=levels_ch1+levels_ch2+levels_ch3;
    public static int levels;
    public static int coins=25;
    private TextView view_levels;
    private TextView view_coins;

    public static final String Podymai_Settings = "podymai";
    public static final String Podymai_Settings_coins = "coins";
    public static final String Podymai_Settings_levels_ch1 = "levels_ch1";
    public static final String Podymai_Settings_levels_ch2 = "levels_ch2";
    public static final String Podymai_Settings_levels_ch3 = "levels_ch3";
    public static SharedPreferences P_Main_settings;


    @Override
    protected void onPause() {
        super.onPause();
        savesettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadsettings();
    }
    public static void savesettings() {
        SharedPreferences.Editor editor = P_Main_settings.edit();
        editor.putInt(Podymai_Settings_coins, coins);
        editor.putInt(Podymai_Settings_levels_ch1, levels_ch1);
        editor.putInt(Podymai_Settings_levels_ch2, levels_ch2);
        editor.putInt(Podymai_Settings_levels_ch3, levels_ch3);
        editor.apply();
    }
    public static void loadsettings() {
        if (P_Main_settings.contains(Podymai_Settings_coins) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch1) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch2) ||
                P_Main_settings.contains(Podymai_Settings_levels_ch3)) {
            coins = P_Main_settings.getInt(Podymai_Settings_coins, 0);
            levels_ch1 = P_Main_settings.getInt(Podymai_Settings_levels_ch1, 0);
            levels_ch2 = P_Main_settings.getInt(Podymai_Settings_levels_ch2, 0);
            levels_ch3 = P_Main_settings.getInt(Podymai_Settings_levels_ch3, 0);
            levels_summ = levels_ch1 + levels_ch2 + levels_ch3;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);

        P_Main_settings = getSharedPreferences(Podymai_Settings, Context.MODE_PRIVATE);
        levels_summ=levels_ch1+levels_ch2+levels_ch3;
        view_coins.setText(Integer.toString(coins));
        view_levels.setText(Integer.toString(levels_summ));
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b_play:
                Intent goPlay = new Intent(MainActivity.this, PlayCompany.class);
                startActivity(goPlay);
                break;
            case R.id.b_select_lvl:
                //if (PlayCompany.i==PlayCompany.base.length) {
                    Intent goSelect = new Intent(MainActivity.this, SelectSection.class);
                    startActivity(goSelect);
                //} else {
                //    Toast.makeText(this, "Для начала вы должны пройти компанию", Toast.LENGTH_SHORT).show();}
                break;
            case R.id.b_exit:
                moveTaskToBack(true);
                super.onDestroy();
                System.runFinalizersOnExit(true);
                System.exit(0);
                break;
            case R.id.version:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.rybinmikhail.esy.es"));
                startActivity(browserIntent);
                break;
        }
    }
}
