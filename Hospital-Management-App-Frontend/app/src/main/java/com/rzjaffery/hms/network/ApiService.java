package com.rzjaffery.hms.network;

import com.rzjaffery.hms.data.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/register")
    Call<String> registerUser(@Body User user);
}
