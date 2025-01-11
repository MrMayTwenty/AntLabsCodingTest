package com.example.antlabs;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AntLabsHttpService {
    public Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://demo-asp-api.antlabs.com:8443/v1/site/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
//

    public IApiClass GetApi() {
        return retrofit.create(IApiClass.class);
    }
}
