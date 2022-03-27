package com.example.atb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;
    private EditText editTextData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tvInfo);
        editTextData = findViewById(R.id.editTextData);
    }

    public void handleClick(View view) {
        //Button btn = (Button)view;
        String text = editTextData.getText().toString();
        tvInfo.setText(text);
    }
}