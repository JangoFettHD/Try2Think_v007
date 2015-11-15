package com.example.mikhail.game2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SelectSection extends AppCompatActivity {



    private TextView view_levels;
    private TextView view_coins;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ch1);



        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_ch1+MainActivity.levels_ch2+MainActivity.levels_ch3));


    }



    public void onClick_menu(View v){
        Intent intent = new Intent(SelectSection.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClick_ch1_lvl1(View view) {
        Intent intent = new Intent(SelectSection.this, ch1_lvl1.class);
        startActivity(intent);
    }
    public void onClick_ch1_lvl2(View view) {
        Intent intent = new Intent(SelectSection.this, ch1_lvl2.class);
        startActivity(intent);
    }
    public void onClick_ch1_lvl3(View view) {
        //Intent intent = new Intent(SelectLVL.this, ch1_lvl3.class);
        //startActivity(intent);
    }
    public void onclick_Torch(View view) {
        Intent intent = new Intent(SelectSection.this, SelectLVL_ch2.class);
        startActivity(intent);
    }

    public void onclick_Rebus(View view) {
        Intent intent = new Intent(SelectSection.this, SelectLVL.class);
        startActivity(intent);
    }

    public void onclick_Bonus(View view) {
        if ((MainActivity.levels_ch1+MainActivity.levels_ch2)>3) {
            Intent intent = new Intent(SelectSection.this, SelectLVL_ch3.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Вы должны пройти минимум 4 уровня, чтобы открыть этот раздел!", Toast.LENGTH_SHORT).show();
        }
    }

}
