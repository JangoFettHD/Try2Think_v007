package com.example.mikhail.game2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectLVL extends AppCompatActivity {

    private Button b_lvl1;
    private Button b_lvl2;
    private Button b_lvl3;
    private Button b_lvl4;
    private Button b_lvl5;
    private Button b_lvl6;

    private TextView view_levels;
    private TextView view_coins;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lvl);

        b_lvl1 = (Button)findViewById(R.id.b_lvl1);
        b_lvl2 = (Button)findViewById(R.id.b_lvl2);
        b_lvl3 = (Button)findViewById(R.id.b_lvl3);
        b_lvl4 = (Button)findViewById(R.id.b_lvl4);
        b_lvl5 = (Button)findViewById(R.id.b_lvl5);
        b_lvl6 = (Button)findViewById(R.id.b_lvl6);

        view_levels = (TextView)findViewById(R.id.levels);
        view_coins = (TextView)findViewById(R.id.coins);
        view_coins.setText(Integer.toString(MainActivity.coins));
        view_levels.setText(Integer.toString(MainActivity.levels_ch1+MainActivity.levels_ch2+MainActivity.levels_ch3));

        CheckNew();
    }

    private void CheckNew(){
        if (MainActivity.levels_ch1>=0){
            b_lvl1.setVisibility(View.VISIBLE);
        }
        if (MainActivity.levels_ch1>=1){
            b_lvl2.setVisibility(View.VISIBLE);
        }
        if (MainActivity.levels_ch1>=2){
            b_lvl3.setVisibility(View.VISIBLE);
        }
        if (MainActivity.levels_ch1>=3){
            b_lvl4.setVisibility(View.VISIBLE);
        }
        if (MainActivity.levels_ch1>=4){
            b_lvl5.setVisibility(View.VISIBLE);
        }
        if (MainActivity.levels_ch1>=5){
            b_lvl6.setVisibility(View.VISIBLE);
        }
    }

    public void onClick_menu(View v){
        Intent intent = new Intent(SelectLVL.this, SelectSection.class);
        startActivity(intent);
    }

    public void onClick_ch1_lvl1(View view) {
        Intent intent = new Intent(SelectLVL.this, ch1_lvl1.class);
        startActivity(intent);
    }
    public void onClick_ch1_lvl2(View view) {
        Intent intent = new Intent(SelectLVL.this, ch1_lvl2.class);
        startActivity(intent);
    }
    public void onClick_ch1_lvl3(View view) {
        //Intent intent = new Intent(SelectLVL.this, ch1_lvl3.class);
        //startActivity(intent);
    }

}
