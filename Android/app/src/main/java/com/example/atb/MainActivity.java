package com.example.atb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atb.network.account.AccountService;
import com.example.atb.network.account.dto.AccountResponseDTO;
import com.example.atb.network.account.dto.RegisterDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
//        String text = editTextData.getText().toString();
//        tvInfo.setText(text);
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("ss@gmail.com");

        AccountService.getInstance()
                .jsonApi()
                .register(registerDTO)
                .enqueue(new Callback<AccountResponseDTO>() {
                    @Override
                    public void onResponse(Call<AccountResponseDTO> call, Response<AccountResponseDTO> response) {
                        if(response.isSuccessful()) {
                            AccountResponseDTO data = response.body();
                            tvInfo.setText("response is good");
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDTO> call, Throwable t) {
                        String str = t.toString();
                        int a =12;
                    }
                });


    }
}