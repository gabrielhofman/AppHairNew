package com.example.apphairnew.Service;

import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.CepResponse;
import com.example.apphairnew.response.HorarioResponse;
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

    @POST("auth/CadastroContato")
    Call<CadContatoResponse> CadContato(@Body ContatoModel body);

    //@POST("auth/ListaContato")
   // Call<ListaContatoResponse> ListaContato(@Body ContatoModel body);

    @POST("auth/CadastroServico")
    Call<CadServicoResponse> CadServico(@Body ServicoModel body);

    @POST("auth/MarcarHorario")
    Call<HorarioResponse> MarcarHorario(@Body HorarioModel body);



    @GET("{CEP}/json")
    Call<CepResponse> getCEP(@Path("CEP") String CEP);



}
