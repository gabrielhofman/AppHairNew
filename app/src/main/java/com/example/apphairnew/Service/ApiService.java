package com.example.apphairnew.Service;

import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.CepResponse;
import com.example.apphairnew.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("auth/SignIn")
    Call<LoginResponse> Login(@Body LoginModel body);

    @POST("auth/CadastroProf")
    Call<CadProfResponse> CadProf(@Body ProfModel body);

    @POST("auth/CadastroServico")
    Call<CadServicoResponse> CadServico(@Body ServicoModel body);

    @GET("{CEP}/json")
    Call<CepResponse> getCEP(@Path("CEP") String CEP);



}
