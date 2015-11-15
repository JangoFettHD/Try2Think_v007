package com.example.mikhail.game2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ch1_lvl2 extends AppCompatActivity {

    private TextView view_levels;
    private TextView view_coins;

    public static final String Podymai_Settings_ch1_lvl2 = "podymai_ch1_lvl2";
    public static final String Podymai_Settings_lock_ch1_lvl2 = "lock_ch1_lvl2";
    public static final String Podymai_Settings_tryC_ch1_lvl2 = "tryC_ch1_lvl2";
    public static SharedPreferences Psettings_ch1_lvl2;

    private Button b_back;
    private EditText vvod;
    private Button b_next;

    private static int tryC=1;
    private static int lock=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch1_lvl2);

        Psettings_ch1_lvl2 = getSharedPreferences(Podymai_Settings_ch1_lvl2, Context.MODE_PRIVATE);

        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_ch1+MainActivity.levels_ch2+MainActivity.levels_ch3));

        b_back = (Button)findViewById(R.id.b_back);
        vvod = (EditText)findViewById(R.id.vvod);
        b_next = (Button)findViewById(R.id.b_next);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Запоминаем данные
        SharedPreferences.Editor editor = Psettings_ch1_lvl2.edit();
        editor.putInt(Podymai_Settings_lock_ch1_lvl2, lock);
        editor.apply();
        editor.putInt(Podymai_Settings_tryC_ch1_lvl2, tryC);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Psettings_ch1_lvl2.contains(Podymai_Settings_lock_ch1_lvl2)) {
            // Получаем число из настроек
            lock = Psettings_ch1_lvl2.getInt(Podymai_Settings_lock_ch1_lvl2, 0);
        }
        if (Psettings_ch1_lvl2.contains(Podymai_Settings_tryC_ch1_lvl2)) {
            // Получаем число из настроек
            tryC = Psettings_ch1_lvl2.getInt(Podymai_Settings_tryC_ch1_lvl2, 0);
        }
    }

    public void onClick_b_next(View v){
        if (lock==0) {

            if (vvod.getText().toString().replaceAll(" ", "").equals("михаил")||vvod.getText().toString().replaceAll(" ", "").equals("Михаил")) {
                lock=1;
                MainActivity.levels_ch1 +=1;
                if (tryC==1){
                    MainActivity.coins += 50;
                    Toast.makeText(this, "Правильно! +50 монет!", Toast.LENGTH_SHORT).show();}
                if (tryC==2){
                    MainActivity.coins += 25;
                    Toast.makeText(this, "Правильно! +25 монет!", Toast.LENGTH_SHORT).show();}
                if (tryC>2){
                    MainActivity.coins += 10;
                    Toast.makeText(this, "Правильно! +10 монет!", Toast.LENGTH_SHORT).show();}
                SharedPreferences.Editor editor = MainActivity.P_Main_settings.edit();
                editor.putInt(MainActivity.Podymai_Settings_coins, MainActivity.coins);
                editor.apply();
                editor.putInt(MainActivity.Podymai_Settings_levels_ch1, MainActivity.levels_ch1);
                editor.apply();
                view_coins.setText(Integer.toString(MainActivity.coins));
                view_levels.setText(Integer.toString(MainActivity.levels_ch1+MainActivity.levels_ch2+MainActivity.levels_ch3));
                Intent intent = new Intent(ch1_lvl2.this, MainActivity.class);
                startActivity(intent);
                onPause();
            }else if (vvod.getText().length() == 0) {
                Toast.makeText(this, "Введи ответ!", Toast.LENGTH_SHORT).show();
            } else if (vvod.getText().toString().replaceAll(" ", "") !="михаил" || vvod.getText().toString().replaceAll(" ","") !="Михаил"){
                tryC+=1;
                Toast.makeText(this, "Неправильно! Подумай еще!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(ch1_lvl2.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void onClick_menu(View view) {
        Intent intent = new Intent(ch1_lvl2.this, MainActivity.class);
        startActivity(intent);
    }
    public void onClick_b_back(View view) {
        Intent intent = new Intent(ch1_lvl2.this, ch1_lvl1.class);
        startActivity(intent);
    }

    public void onClick_help(View view) {
        Toast.makeText(this, "Если возле рисунка изображена цифра, знак равенства и буква, это означает, что букву с указанным порядковым номером необходимо заменить на указанную в равенстве.", Toast.LENGTH_SHORT).show();
    }
}
