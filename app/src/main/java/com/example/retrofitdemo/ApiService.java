package com.example.retrofitdemo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("retrofit/json_object.json")
    Call<EmployeeList> getJSON();
}
