package com.i2i.eyecell.remote;

import com.i2i.eyecell.model.PackageInfoRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPackageInfo {

    @GET("package/packageInfoInObject")
    Call<PackageInfoRequest> getPackageInfo(@Query("MSISDN") String msisdn);
}
