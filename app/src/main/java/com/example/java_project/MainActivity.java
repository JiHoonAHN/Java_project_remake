package com.example.java_project;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity { //MainActivity 는 Activity를 상속한다.

    DatePicker datePicker;  //  datePicker - 날짜를 선택하는 달력
    TextView viewDatePick;  //  viewDatePick - 선택한 날짜를 보여주는 textView
    EditText edt;   //  edtDiary - 선택한 날짜의 내용을 쓰거나 기존에 저장된 내용이 있다면 보여주고 수정가능한 영역
    Button btnSave;   //  btnSave - 선택한 날짜의 일기 저장 및 수정(덮어쓰기) 버튼
    String fileName;   //  fileName - 돌고 도는 선택된 날짜의 파일 이름

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 앱 첫 시작 시 돌아감
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰에 있는 위젯들 리턴 받아두기
        datePicker = (DatePicker) findViewById(R.id.datePicker); //calendar 내용을 가져온다.
        viewDatePick = (TextView) findViewById(R.id.viewDatePick);//날짜를 보여준다.
        edt = (EditText) findViewById(R.id.edt);//내용을 입력 받을 부분
        btnSave = (Button) findViewById(R.id.btnSave);// 버튼 -> save 하는 버튼

        // 오늘 날짜를 받게해주는 Calender
        Calendar c = Calendar.getInstance();
        int c_Year = c.get(Calendar.YEAR);
        int c_Month = c.get(Calendar.MONTH);
        int c_Day = c.get(Calendar.DAY_OF_MONTH);

        // 첫 시작 시에는 오늘 날짜 일기 읽어주기
        checkedDay(c_Year, c_Month, c_Day);

        // datePick 기능 구현
        // datePicker.init(연도,달,일)
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                // 이미 선택한 날짜에 일기가 있는지 없는지 체크한다.
                checkedDay(year, month, day);
            }
        });

        // 저장/수정 버튼 누르면 실행되는 리스너
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fileName을 넣고 저장 시키는 메소드를 호출
                saveDiary(fileName);
            }
        });
    }

    // EditText로 내용 파일 읽기
    private void checkedDay(int year, int month, int day) {

        // 받은 날짜로 날짜 보여주는
        month += 1;
        viewDatePick.setText(year + " - " + month + " - " + day);

        fileName = year + "" + month + "" + day + ".txt";// 파일 이름을 만들어 준다 년+월+일.txt 이런 식
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);

            byte[] fileData = new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            String str = new String(fileData, "EUC-KR");
            // 읽어서 토스트 메시지로 보여준다. -> 경고창이라고 생각하면 편하다.
            edt.setText(str);

            btnSave.setText("수정하기");
        } catch (Exception e) { // UnsupportedEncodingException , FileNotFoundException , IOException
            // 없어서 오류가 나면 내용이 없는 것 -> 내용을 쓰게 한다.
            // Toast.makeText(getApplicationContext(), "작성 X", Toast.LENGTH_SHORT).show();

            edt.setText("");
            btnSave.setText("새로 저장");
            e.printStackTrace();
        }

    }

    // 내용 저장하는 메소드
    @SuppressLint("WrongConstant")//->@SuppressLint 어노테이션을 사용하며. 인자로 검사에서 제외할 항목 ID를 입력
    private void saveDiary(String readDay) {

        FileOutputStream fos = null; //FileOutputStream 파일로 바이트 단위의 출력을 내보내는 클래스

        try {
            fos = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS); //MODE_WORLD_WRITEABLE -> 데이터베이스 열기 플래그: 설정되면 지역화된 콜라이어를 지원하지 않고 데이터베이스를 연다.
            String content = edt.getText().toString();

            // String.getBytes() = 스트링을 배열형으로 변환
            fos.write(content.getBytes());
            //fos.flush();
            fos.close();

            // getApplicationContext() = 현재 클래스.this
            Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();

        } catch (Exception e) { // Exception - 에러 종류 제일 상위 // FileNotFoundException , IOException
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();//error라고 토스트 메세지 보여줌
        }
    }

}
