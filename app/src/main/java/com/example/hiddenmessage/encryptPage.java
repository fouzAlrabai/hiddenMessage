package com.example.hiddenmessage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class encryptPage extends AppCompatActivity {

    EditText mMess,mKey;
    Button encrypt;
    TextView cipher ;
     String userText,key;
     int colNum,mod,rowNum;
     int plainText[][];
     int cipherText[][];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_page);
        mMess = findViewById(R.id.editText3);
        mKey = findViewById(R.id.editText2);
        encrypt = findViewById(R.id.button);
        cipher = findViewById(R.id.editText);



        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userText =mMess.getText().toString();
                System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println(userText);
                key=mKey.getText().toString();

                //check fields

                if (TextUtils.isEmpty(userText)) {
                    mMess.setError("Please Enter Message, It is Required");
                    return;
                }
                //check fields

                if (TextUtils.isEmpty(key)) {
                    mKey.setError("Please Enter Key, It is Required");
                    return;
                }

                userText =mMess.getText().toString();
                key=mKey.getText().toString();

                encrypt(userText,key);
            }
        });

    }

    public void OpenMainPage(View view) {
        startActivity(new Intent(encryptPage.this,MainActivity.class));
    }

    public void encrypt(String userText,String key){
        int stars=0;
        colNum=key.length();
        mod =userText.length()%colNum;
        rowNum=(userText.length()/colNum)+2;
        if(mod != 0 ){
            rowNum++;
            stars=key.length()-mod;
            if(stars != 0){
                for(int i =0;i<stars;i++){
                    userText=userText+" ";
                }
            }
        }


        System.out.println(rowNum);
        char[][]plain=new char[rowNum+2][colNum];
        //to put key at first row
        for(int i =0;i<key.length();i++) {
            plain[0][i]=key.charAt(i);
        }
        //to sort key
        char[] ke = new char[key.length()];
        char[] keSort=new char[key.length()];
        for(int i =0;i<key.length();i++) {
            ke[i]=plain[0][i];
            keSort[i]=plain[0][i];
        }

        Arrays.sort(keSort);
        //insert index
        for(int i =0;i<colNum;i++) {
            for (int j=0;j<colNum;j++ ) {
                if( keSort[j]== ke[i] ){
                    String n = j+1+"";
                    plain[1][i]=n.charAt(0);
                }
            }

        }
        int index =0;
        for(int i =2;i<rowNum;i++) {
            for (int j=0;j<key.length();j++ ) {
                if(index < userText.length()) {
                    System.out.println(rowNum+"row");
                    plain[i][j]=userText.charAt(index);
                    System.out.println("infd"+index);
                    System.out.println(userText.charAt(index)+"c");
                    System.out.println(""+plain[i][j]);
                    index++;

                }
                //else if(index == userText.length()) {
                // plain[i][j]='-';
                // }

            }
        }
        //to encrypt
        String ciphir="";
        index =0;
        int h=49;
        for(int i =2;i<rowNum;i++) {
            System.out.println("row "+i);
            for (int j=0;j<key.length();j++ ) {
                System.out.println("col "+plain[i][j]);
                if(index < userText.length()) {
                    for(int k=0;k<key.length();k++) {
                        int x =plain[1][k];
                        System.out.println("x = "+x);
                        System.out.println("real = "+plain[1][k]);
                        if( x == h) {
                            System.out.println("inside if ");
                            for(int w=2;w<rowNum;w++)
                                ciphir=ciphir+plain[w][k];
                            //ciphir=ciphir+plain[i][k];

                            //ciphir=ciphir+plain[i+1][k];
                            index++;
                            h++;
                        }
                    }
                }
            }
        }

        System.out.println(ciphir+"");
        cipher.setText(ciphir);

    }
}
