package com.example.atb.network.account;

import com.example.atb.network.account.dto.AccountResponseDTO;
import com.example.atb.network.account.dto.RegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AccountApi {
    @POST("/api/account/register")
    public Call<AccountResponseDTO> register(@Body RegisterDTO model);
}
