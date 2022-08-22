package com.i2i.eyecell.remote;

import com.i2i.eyecell.model.LoginRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ILoginApi {
    @GET("a/{MSISDN}/{password}")
    Call<LoginRequest> login(@Path("MSISDN") String msisdn, @Path("password") String password);
}
