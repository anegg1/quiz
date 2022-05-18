package com.example.qize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button btnGame1,btnGame2;
    Intent intent, intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnGame1 = findViewById(R.id.button2);
        btnGame2 = findViewById(R.id.button3);

        intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent2 = new Intent(MainActivity2.this, MainActivity4.class);

    }
}