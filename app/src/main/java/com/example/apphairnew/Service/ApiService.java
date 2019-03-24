package com.example.apphairnew.Service;

import com.example.apphairnew.model.EnderecoModel;
import com.example.apphairnew.model.EstabModel;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.response.CadEnderecoResponse;
import com.example.apphairnew.response.CadEstabResponse;
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

    @POST("auth/CadEstab")
    Call<CadEstabResponse> CadEstab(@Body EstabModel body);

    @POST("auth/CadEndereco")
    Call<CadEnderecoResponse> CadEndereco(@Body EnderecoModel body);


}
