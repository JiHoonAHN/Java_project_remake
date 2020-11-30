package com.example.java_project;


import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
//1. 주석 사용
//2. 매서드 이해

public class MainActivity extends AppCompatActivity {
    private TextView textView_Date;
    private DatePickerDialog.OnDateSetListener callbackMethod;
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
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                monthOfYear +=1;
                textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");
            }
        };
    }
    // month는 0 부터 시작
    public void OnClickHandler(View view) {

        SimpleDateFormat year_1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat month_1 = new SimpleDateFormat("MM");
        SimpleDateFormat day_1 = new SimpleDateFormat("dd");

        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2020, 11, 29);
        dialog.show();
    }

}