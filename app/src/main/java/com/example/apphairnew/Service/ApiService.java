package com.example.apphairnew.Service;

import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.model.CtsPagarModel;
import com.example.apphairnew.model.CtsReceberModel;
import com.example.apphairnew.model.FluxoModel;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.model.LiqPagarModel;
import com.example.apphairnew.model.LiqRecebModel;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.AddCtsPagarResponse;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.AddFluxoResponse;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.CepResponse;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetCtsPagarResponse;
import com.example.apphairnew.response.GetCtsReceberResponse;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.response.GetHorarioResponse;
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

    @GET("auth/GetAgenda/{usuario}/{data}")
    Call <List<GetHorarioResponse>> getAgenda(@Path("usuario") int usuario, @Path("data") String data);

    @GET("auth/GetServico/{usuario}")
    Call <List<GetServicoResponse2>> getServico(@Path("usuario") int usuario);

    @GET("auth/GetContato/{usuario}")
    Call <List<GetContatoResponse>> getContato(@Path("usuario") int usuario);

    @GET("auth/GetCtsReceber/{usuario}")
    Call <List<GetCtsReceberResponse>> getCtsReceber(@Path("usuario") int usuario);

    @GET("auth/GetCtsPagar/{usuario}")
    Call <List<GetCtsPagarResponse>> getCtsPagar(@Path("usuario") int usuario);

    @POST("auth/MarcarHorario")
    Call<HorarioResponse> MarcarHorario(@Body HorarioModel body);

    @POST("auth/AlterarHorario")
    Call<HorarioResponse> AltHorario(@Body HorarioModel body);

    @POST("auth/LimparHorario")
    Call<HorarioResponse> LimpHorario(@Body HorarioModel body);

    @POST("auth/AlterarServico")
    Call<CadServicoResponse> AltServico(@Body ServicoModel body);

    @POST("auth/AlterarCP")
    Call<AddCtsPagarResponse> AltCP(@Body CtsPagarModel body);

    @POST("auth/AlterarCR")
    Call<AddCtsReceberResponse> AltCR(@Body CtsReceberModel body);

    @POST("auth/AlterarContato")
    Call<CadContatoResponse> AltContato(@Body ContatoModel body);

    @POST("auth/GetAgendaDetalhe")
    Call<GetDetalheAgendaResponse> GetDetalhe(@Body GetAgendaDetalhe body);

    @GET("auth/ExcluirCP/{cpId}")
    Call<AddCtsPagarResponse> ExcluirContato(@Path("cpId") int cpId);


    @GET("auth/ExcluirCR/{crId}")
    Call<AddCtsReceberResponse> ExcluirCR(@Path("crId") int crId);

    @POST("auth/AdicionarFluxo")
    Call<AddFluxoResponse> CadFluxo(@Body FluxoModel body);

    @POST("auth/AddCtsReceberAgenda")
    Call<AddCtsReceberResponse> AddCtsRecebAgenda(@Body CtsReceberModel body);

    @GET("auth/GetAgendaOfertada/{usuario}")
    Call <List<GetHorarioResponse>> getAgendaOfertada(@Path("usuario") int usuario);






}
