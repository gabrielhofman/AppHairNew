package com.example.apphairnew.Service;

import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("auth/SignIn")
    Call<LoginResponse> Login(@Body LoginModel body);

    @POST("auth/CadProf")
    Call<CadProfResponse> CadProf(@Body ProfModel body);


}
