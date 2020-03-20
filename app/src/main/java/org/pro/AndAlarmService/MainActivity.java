package org.pro.AndAlarmService;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;
    int imageIndex = 1;
    TextView textView;
    EditText sendText;

    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView2.setVisibility(View.INVISIBLE);
        textView = findViewById(R.id.textView2);
        sendText = findViewById(R.id.sendText);

        //클릭 위치 표현
        View view = findViewById(R.id.view);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public  boolean onTouch(View v, MotionEvent event){
                int action = event.getAction();
                float curX = event.getX();
                float curY = event.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    textView.setText(null);
                    println("    PRESS : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_MOVE) {
                    println("    MOVE : " + curX + ", " + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("    UP : " + curX + ", " + curY);
                }

                return true;
            }
        });

        //뷰 화면에 대한 여러 이벤트
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                println("    on down 호출");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("    onfling 호출 : "+velocityX+", "+velocityY);
                return true;
            }
        });

        View view2 = findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public  boolean onTouch(View v, MotionEvent event){
                detector.onTouchEvent(event);
                return true;
            }
        });

        //화면전환 및 메시지 전달
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menuActivity.class);
                intent.putExtra("textMessages", sendText.getText().toString());
                startActivityForResult(intent, 101);
            }
        });

        //데이터 전달 화면으로 이동
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), datasendActivity.class);
                intent.putExtra("textMessages", sendText.getText().toString());
                startActivityForResult(intent, 102);
            }
        });

    }

    public  void println(String data){
        textView.append(data + "\n");
    }

    public void onButton1Clicked(View v) {
        Toast.makeText(this, "확인1 버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

    //사이트 연동
    public void onButton2Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }

    //전화 걸기
    public void onButton3Clicked(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-2213-7527"));
        startActivity(intent);
    }

    //토스트 위치 변경
    public void onButton4Clicked(View v) {
        Toast myToast = Toast.makeText(this.getApplicationContext(),"Hi 버튼이 눌렸어요.", Toast.LENGTH_SHORT);

        myToast.setGravity(Gravity.CENTER, 0,300 );
        //레이아웃 통으로 띄우기
        //View toastLayout = getLayoutInflater().inflate(R.layout.activity_main, null);
        //myToast.setView(toastLayout);
        myToast.show();
    }

    public void onButton5Clicked(View v) {
        changeImage();
    }

    //이미지 전환하기
    public  void changeImage() {
        if (imageIndex == 0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageIndex = 1;
        } else if (imageIndex == 1){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageIndex = 0;
        }
    }

    //화면전환 및 메시지 전달
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (data != null) {
                String textMessages = data.getStringExtra("textMessages");
                if (textMessages != null) {
                    sendText.setText(textMessages);
                }
            }
        }
    }
}
