package com.i2i.eyecell.connect;

import com.i2i.eyecell.remote.ILoginApi;
import com.i2i.eyecell.remote.IPackageApi;
import com.i2i.eyecell.remote.IPackageBalanceRemaining;
import com.i2i.eyecell.remote.IPackageInfo;
import com.i2i.eyecell.remote.IRegisterApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://34.140.158.254:8082/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }
    public static IRegisterApi getPackageId(){

        IRegisterApi iRegisterApi = getRetrofit().create(IRegisterApi.class);
        return iRegisterApi;
    }
    public static IPackageApi getPackageIdName(){

        IPackageApi iPackageApi = getRetrofit().create(IPackageApi.class);

        return iPackageApi;
    }
    public static ILoginApi getUserService(){

        ILoginApi iLoginApi = getRetrofit().create(ILoginApi.class);

        return iLoginApi;
    }
    public static IPackageInfo getPackageInfo(){

        IPackageInfo iPackageInfo = getRetrofit().create(IPackageInfo.class);

        return  iPackageInfo;
    }
    public static IPackageBalanceRemaining getBalanceRemaining(){
        IPackageBalanceRemaining iPackageBalanceRemaining = getRetrofit().create(IPackageBalanceRemaining.class);

        return iPackageBalanceRemaining;
    }
}
