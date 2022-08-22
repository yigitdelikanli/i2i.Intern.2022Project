package com.i2i.eyecell.remote;

import com.i2i.eyecell.model.RegisterRequest;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IRegisterApi {
    @FormUrlEncoded
    @POST("subs/register")
    Call<RegisterRequest> register(@FieldMap Map<String, Object> fields);
}