package com.example.hiddenmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class decryptPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_page);
    }

    public void OpenMainPage(View view) {
        startActivity(new Intent(decryptPage.this,MainActivity.class));
    }
}
