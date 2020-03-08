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

public class decryptPage extends AppCompatActivity {
    EditText mMess,mKey;
    Button decrypt;
    TextView plain1;
    String userText,key;
    int colNum,mod,rowNum;
    int plainText[][];
    int cipherText[][];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_page);
        mMess = findViewById(R.id.editText3);
        mKey = findViewById(R.id.editText2);
        decrypt = findViewById(R.id.button);
        plain1 = findViewById(R.id.editText);

        decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userText =mMess.getText().toString();
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

                dycrypt(userText,key);
            }
        });


    }

    public void OpenMainPage(View view) {
        startActivity(new Intent(decryptPage.this,MainActivity.class));
    }

    public void dycrypt(String userText,String key){
        //first is row then col
        colNum=key.length();
        mod =userText.length()%colNum;

        rowNum=(userText.length()/colNum)+2;
if(mod != 0)
    rowNum++;
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
                  //  System.out.println(rowNum+"row");
                    plain[i][j]=userText.charAt(index);

                   // System.out.println("infd"+index);
                  //  System.out.println(userText.charAt(index)+"c");
                  //  System.out.println(""+plain[i][j]);
                    index++;

                }
                //else if(index == userText.length()) {
                // plain[i][j]='-';
                // }

            }
        }
        //to encrupt
        String plainT="";



        //to decrypt
        // String key1="spyman";

        char[][]encrypted =new char[rowNum][colNum];
        for(int i =0;i<key.length();i++) {
            encrypted[0][i]=key.charAt(i);
        }

        for(int i =0;i<key.length();i++) {
            for (int j=0;j<key.length();j++ ) {
                if( keSort[j]== ke[i] ){
                    String n = j+1+"";
                    encrypted[1][i]=n.charAt(0);
                }
            }

        }

        // now the real decrypt
       // String text=ciphir;

        index =0;
        int f=49;
        int loopsize;
        int mod1=userText.length()%colNum;
       // System.out.println("Mod*************************88"+mod1);
       // System.out.println("Mod*****ROW***************88"+rowNum);

            loopsize =rowNum;

        //	for(int i =2;i<rowNum;i++) {
        //System.out.println("row "+i);
        int q =2;
       // if(colNum -mod1 != 0){

              //  encrypted[5][5]='*';
           // encrypted[5][4]='*';
         //   mod1=mod1-2;

      //  }
        for (int j=0;j<key.length();j++ ) {
            // System.out.println("col "+plain[i][j]);
            if(index < userText.length()) {
                for(int k=0;k<key.length();k++) {
                    int x =encrypted[1][k];
                   // System.out.println("x = "+x);
                  //  System.out.println("real = "+encrypted[1][k]);
                    if( x == f) {
                       // System.out.println("inside if for de ");
                       // if(mod1 != 0 )
                         //   loopsize-=1;
                       /* int where = 0;
                        if(mod1 > 0 &&  k+1 == encrypted[1][where]){
                            encrypted[q+1][k]=userText.charAt(index);
                            index++;
                            mod1 --;
                        }*/

                        for(q =2;q<loopsize;q++) {

                            if(index<userText.length()  ){
                                encrypted[q][k]=userText.charAt(index);
                            index++;


                            }

                            //this to make sure

                        }

                        //text.replace(text.charAt(index), ' ');
                        //if(index+1<text.length() && i+1<rowNum) {
                        //encrypted[i+1][k]=text.charAt(index+1);
                        //text.replace(text.charAt(index+1), ' ');
                        //}
                        // index++;
                        f++;
                    }
                }
            }
        }
        // }
        //******************************
        for (int i = 2; i < encrypted.length; i++) {
            for (int j = 0; (encrypted[i] != null && j < encrypted[i].length); j++)
                plainT =plainT+encrypted[i][j] +"";


        }
        System.out.println(plainT+"");
        plain1.setText(plainT);

    }
}
