package org.pro.AndAlarmService;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class menuActivity extends AppCompatActivity {

    EditText returnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        returnText = findViewById(R.id.returnText);

        //화면전환 및 메시지 수신
        Intent intent = getIntent();
        if (intent.getExtras().getString("textMessages") != null) {
            returnText.setText(intent.getExtras().getString("textMessages"));
        }

        //화면전환 및 메시지 전달
        Button button2_1 = findViewById(R.id.button2_1);
        button2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("textMessages", returnText.getText().toString());
                setResult(RESULT_OK, intent);

                finish();
            }
        });


    }
}
