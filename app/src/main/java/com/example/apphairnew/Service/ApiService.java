package com.example.apphairnew.Service;

import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.model.CtsPagarModel;
import com.example.apphairnew.model.CtsReceberModel;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.model.LiqPagarModel;
import com.example.apphairnew.model.LiqRecebModel;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.AddCtsPagarResponse;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.CepResponse;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetCtsReceberResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.response.HorarioResponse;
import com.example.apphairnew.response.LiqCtsPagarResponse;
import com.example.apphairnew.response.LiqCtsRecebResponse;
import com.example.apphairnew.response.LoginResponse;

import java.util.List;

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

    @POST("auth/CadastroServico")
    Call<CadServicoResponse> CadServico(@Body ServicoModel body);

    @GET("{CEP}/json")
    Call<CepResponse> getCEP(@Path("CEP") String CEP);

    @POST("auth/AddCtsReceber")
    Call<AddCtsReceberResponse> AddCtsReceb(@Body CtsReceberModel body);

    @POST("auth/AddCtsPagar")
    Call<AddCtsPagarResponse> AddCtsPagar(@Body CtsPagarModel body);

    @POST("auth/LiqCtsReceber")
    Call<LiqCtsRecebResponse> LiqCtsReceb(@Body LiqRecebModel body);

    @POST("auth/LiqCtsPagar")
    Call<LiqCtsPagarResponse> LiqCtsPagar(@Body LiqPagarModel body);

    @GET("auth/GetServico/{usuario}")
    Call <List<GetServicoResponse2>> getServico(@Path("usuario") int usuario);

    @GET("auth/GetContato/{usuario}")
    Call <List<GetContatoResponse>> getContato(@Path("usuario") int usuario);

    @GET("auth/GetCtsReceber/{usuario}")
    Call <List<GetCtsReceberResponse>> getCtsReceber(@Path("usuario") int usuario);

    @POST("auth/MarcarHorario")
    Call<HorarioResponse> MarcarHorario(@Body HorarioModel body);

    @POST("auth/AlterarServico")
    Call<CadServicoResponse> AltServico(@Body ServicoModel body);

    @POST("auth/AlterarContato")
    Call<CadContatoResponse> AltContato(@Body ContatoModel body);







}
