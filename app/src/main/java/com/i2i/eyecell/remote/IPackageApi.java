package com.i2i.eyecell.remote;

import com.i2i.eyecell.model.PackageRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPackageApi {
    @GET("package/packageIdName")
    Call<List<PackageRequest>> getPackageId();
}
