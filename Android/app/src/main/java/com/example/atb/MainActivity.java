package com.example.atb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.atb.network.account.AccountService;
import com.example.atb.network.account.dto.AccountResponseDTO;
import com.example.atb.network.account.dto.RegisterDTO;
import com.example.atb.network.account.dto.RegisterErrorDTO;
import com.example.atb.network.account.dto.ValidationRegisterDTO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvInfo;
    private TextInputLayout textFieldEmail;
    private TextInputEditText txtEmail;

    private TextInputLayout textFieldFirstName;
    private TextInputEditText txtFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tvInfo);
        textFieldEmail = findViewById(R.id.textFieldEmail);
        txtEmail = findViewById(R.id.txtEmail);

        textFieldFirstName = findViewById(R.id.textFieldFirstName);
        txtFirstName = findViewById(R.id.txtFirstName);
    }

    public void handleClick(View view) {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail(txtEmail.getText().toString());
        registerDTO.setFirstName(txtFirstName.getText().toString());

        if (!validationFields(registerDTO))
            return;

        AccountService.getInstance()
                .jsonApi()
                .register(registerDTO)
                .enqueue(new Callback<AccountResponseDTO>() {
                    @Override
                    public void onResponse(Call<AccountResponseDTO> call, Response<AccountResponseDTO> response) {
                        if (response.isSuccessful()) {
                            AccountResponseDTO data = response.body();
                            tvInfo.setText("response is good");
                        } else {
                            try {
                                showErrorsServer(response.errorBody().string());
                            } catch (Exception e) {
                                System.out.println("------Error response parse body-----");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponseDTO> call, Throwable t) {
                        String str = t.toString();
                        int a = 12;
                    }
                });
    }

    private boolean validationFields(RegisterDTO registerDTO) {
        textFieldEmail.setError("");
        if (registerDTO.getEmail().equals("")) {
            textFieldEmail.setError("Вкажіть пошту");
            return false;
        }

        textFieldFirstName.setError("");
        if (registerDTO.getFirstName().equals("")) {
            textFieldFirstName.setError("Вкажіть ім'я");
            return false;
        }
        return true;
    }

    private void showErrorsServer(String json) {
        Gson gson = new Gson();
        ValidationRegisterDTO result = gson.fromJson(json, ValidationRegisterDTO.class);
        String str = "";
        if (result.getErrors().getEmail() != null) {
            for (String item : result.getErrors().getEmail()) {
                str += item + "\n";
            }
        }
        textFieldEmail.setError(str);

        str = "";
        if (result.getErrors().getFirstName() != null) {
            for (String item : result.getErrors().getFirstName()) {
                str += item + "\n";
            }
        }
        textFieldFirstName.setError(str);
    }
}