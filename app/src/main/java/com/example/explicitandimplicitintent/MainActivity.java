package com.example.explicitandimplicitintent;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvContacts;
    Button btnCreate;
    ImageView ivMood, ivCall, ivWeb, ivLocation;
    final int ACTIVITY2 = 1;

    String name = "", number = "", web = "", map = "", mood = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContacts = findViewById(R.id.tvContacts);
        btnCreate = findViewById(R.id.btnCreate);

        ivMood = findViewById(R.id.ivMood);
        ivCall = findViewById(R.id.ivCall);
        ivWeb = findViewById(R.id.ivWeb);
        ivLocation = findViewById(R.id.ivLocation);

        ivLocation.setVisibility(View.GONE);
        ivMood.setVisibility(View.GONE);
        ivCall.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, com.example.explicitandimplicitintent.Activity2.class);
                startActivityForResult(intent, ACTIVITY2);

            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);


            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);

            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map));
                startActivity(intent);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY2)
        {
            if (resultCode == RESULT_OK)
            {
                ivLocation.setVisibility(View.VISIBLE);
                ivMood.setVisibility(View.VISIBLE);
                ivCall.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                web = data.getStringExtra("web");
                map = data.getStringExtra("map");
                mood = data.getStringExtra("mood");

                if (mood.equals("happy"))
                {
                    ivMood.setImageResource(R.drawable.happy);
                }
                else if (mood.equals("ok"))
                {
                    ivMood.setImageResource(R.drawable.base);
                }
                else
                {
                    ivMood.setImageResource(R.drawable.sad);
                }
            }
            else
            {
                Toast.makeText(this, "No data passed through!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}