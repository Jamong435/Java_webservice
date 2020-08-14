package com.kim9212.ex75webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;

    String textUrl = "http://toki666.dothome.co.kr/index.js";
    String imgUrl = "http://toki666.dothome.co.kr/flag_korea.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);

    }

    public void clickbtn(View view) {
        //서버에서 제공하는 텍스트 파일 읽어오기
        new Thread(){

            @Override
            public void run() {

                try {
                    URL uri= new URL(textUrl);//이거외우기

                    InputStream is= uri.openStream();
                    InputStreamReader isr= new InputStreamReader(is);
                    BufferedReader reader= new BufferedReader(isr);

                    final StringBuffer buffer= new StringBuffer();
                    while (true){
                        String line=reader.readLine();
                        if(line==null)break;

                        buffer.append(line+"\n");
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(buffer.toString());
                        }
                    });


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    public void clickbtn2(View view) {
        Glide.with(this).load(imgUrl).into(iv);

//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url= new URL(imgUrl);
//                    InputStream is= url.openStream();
//
//                    //유일하게 이미지를 저장하는 값
//                    final Bitmap bm= BitmapFactory.decodeStream(is);
//
//
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            iv.setImageBitmap(bm); //여기서 에러나면 Bitmap에 파이널
//                        }
//                    });
//
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }.start();
    }
}