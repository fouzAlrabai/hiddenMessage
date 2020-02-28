package com.example.hiddenmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenEncryptPage(View view) {
        startActivity(new Intent(MainActivity.this,encryptPage.class));
    }

    public void OpenDecrypttPage(View view) {
        startActivity(new Intent(MainActivity.this,decryptPage.class));
    }
}
