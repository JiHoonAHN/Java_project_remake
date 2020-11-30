package com.example.java_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Handler hd = new Handler();
        hd.postDelayed(new LoadingActivityhandler(), 3000); // 1초 뒤 hd handler 실행
    }

    private class LoadingActivityhandler implements Runnable {
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class)); //로딩후, ChoiceFunction 이동
            LoadingActivity.this.finish(); // 로딩페이지 Activity stack에서 제거
        }
    }
    @Override
    public void onBackPressed() {
        //초반 뒤로가기 버튼 못누르게 함
    }
}