package com.rzjaffery.hms.network;

import android.telecom.Call;

import com.rzjaffery.hms.data.model.User;

public interface ApiService {
    @POST("api/auth/register")
    Call<String> registerUser(@Body User user);
}
