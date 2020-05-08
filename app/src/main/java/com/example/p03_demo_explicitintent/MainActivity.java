package com.example.p03_demo_explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvSM, tvBM;
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmanStats = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSM = (TextView) findViewById(R.id.tvSuperman);
        tvBM = (TextView) findViewById(R.id.tvBatman);


        tvSM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                i.putExtra("hero", superman);
                // Start activity with requestCodeForSupermanStats to
                // identify it was started by clicking on Superman
                startActivityForResult(i,requestCodeForSupermanStats);
            }
        });

        tvBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                i.putExtra("hero", batman);
                // Start activity with requestCodeForBatmanStats to
                // identify started by clicking Batman
                startActivityForResult(i, requestCodeForBatmanStats);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Only handle when 2nd activity closed normally
        //  and data contains something
        if(resultCode == RESULT_OK){
            if (data != null) {
                // Get data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                // If it is activity started by clicking
                //  Superman, create corresponding String
                if(requestCode == requestCodeForSupermanStats){
                    statement = "You " + like + " Superman";
                }
                // If 2nd activity started by clicking
                //  Batman, create a corresponding String
                if(requestCode == requestCodeForBatmanStats){
                    statement = "You " + like + " Batman";
                }
                Toast.makeText(MainActivity.this, statement,
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}
