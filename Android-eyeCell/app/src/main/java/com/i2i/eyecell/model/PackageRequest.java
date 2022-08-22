package com.i2i.eyecell.model;

public class PackageRequest {
    String packageName;
    int packageId;

    public PackageRequest(String packageName,int packageId) {
        this.packageName = packageName;
        this.packageId = packageId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
