package com.example.java_project;


import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
//1. 주석 사용
//2. 매서드 이해

public class MainActivity extends AppCompatActivity {//메인 클래스
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.View();
        this.Listener();


    }

    public void View()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void Listener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                month +=1; //month는 0부터 시작하기 때문에 1을 더한다.
                textView_Date.setText(year + "년" + month + "월" + day + "일");
            }

        };
    }

    // month는 0 부터 시작
    public void OnClickHandler(View view) {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 11, 29);
        dialog.show();
    }
}