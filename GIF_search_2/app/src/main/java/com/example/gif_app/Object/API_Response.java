
package com.example.gif_app.Object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class API_Response {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public API_Response withData(List<Datum> data) {
        this.data = data;
        return this;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public API_Response withPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public API_Response withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

}