package com.example.atb.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atb.BaseActivity;
import com.example.atb.MainActivity;
import com.example.atb.R;
import com.example.atb.account.userscard.UserDTO;
import com.example.atb.account.userscard.UsersAdapter;
import com.example.atb.network.account.AccountService;
import com.example.atb.network.account.dto.AccountResponseDTO;
import com.example.atb.utils.CommonUtils;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersActivity extends BaseActivity {

    private UsersAdapter adapter;
    private RecyclerView rcvUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        rcvUsers = findViewById(R.id.rcvUsers);
        rcvUsers.setHasFixedSize(true);
        rcvUsers.setLayoutManager(new GridLayoutManager(this, 2,
                LinearLayoutManager.VERTICAL, false));
        CommonUtils.showLoading(this);
        AccountService.getInstance()
                .jsonApi()
                .users()
                .enqueue(new Callback<List<UserDTO>>() {
                    @Override
                    public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                        if(response.isSuccessful())
                        {
                            adapter=new UsersAdapter(response.body());
                            rcvUsers.setAdapter(adapter);
                        }
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });

//                .enqueue(new Callback<>() {
//                    @Override
//                    public void onResponse(Call<AccountResponseDTO> call, Response<AccountResponseDTO> response) {
//                        if (response.isSuccessful()) {
//                            AccountResponseDTO data = response.body();
//                            //tvInfo.setText("response is good");
//                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                            startActivity(intent);
//                        } else {
//                            try {
//                                showErrorsServer(response.errorBody().string());
//                            } catch (Exception e) {
//                                System.out.println("------Error response parse body-----");
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AccountResponseDTO> call, Throwable t) {
//                        String str = t.toString();
//                        int a = 12;
//                    }
//                });
//        List<UserDTO> userDTOS = new ArrayList<>();
//        UserDTO userDTO = new UserDTO();
//        userDTO.setEmail("ss@gg.dd");
//        userDTO.setImage("/images/dmxnsy1u.1ah.jpeg");
//        userDTOS.add(userDTO);
//
//        UserDTO userDTO2 = new UserDTO();
//        userDTO2.setEmail("dd@vv.dd");
//        userDTO2.setImage("/images/gfat5osf.lgr.jpeg");
//        userDTOS.add(userDTO2);
//        adapter=new UsersAdapter(userDTOS);
//        rcvUsers.setAdapter(adapter);

    }
}