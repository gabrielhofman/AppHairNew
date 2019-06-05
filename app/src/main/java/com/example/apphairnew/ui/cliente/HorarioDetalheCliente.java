package com.example.apphairnew.ui.cliente;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.Util.MoneyTextWatcher;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.model.cliente.LogModel;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.response.GetHorarioResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.response.HorarioResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.Agenda;
import com.example.apphairnew.ui.CadastroContato;
import com.example.apphairnew.ui.CadastroServico;
import com.example.apphairnew.ui.CadastroUsuario;
import com.example.apphairnew.ui.ContatoLista;
import com.example.apphairnew.ui.DashBoard;
import com.example.apphairnew.ui.Login;
import com.example.apphairnew.ui.PesquisaServico;
import com.example.apphairnew.web.ApiControler;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorarioDetalheCliente extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeContato;
    private EditText campoHoraInicio;
    private EditText campoHoraFim;
    private EditText campoNomeServico;
    private EditText campoPrecoServico;
    private EditText campoDataAgenda;


    private TextView nomeContatoFinal;
    private TextView nomeServicoFinal;


    private LinearLayoutManager linearLayoutManager;
    public List<GetContatoResponse> teste = new ArrayList<>();


    private Button botaoSalvarHorario;
    private Button botaoConcluirHorario;
    private Button botaoCancelarHorario;
    private Button botaoBuscarContato;
    private Button botaoBuscarServico;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private String  horaInicio, horaFim,  dataAgenda;
    private int contato =0, servico =0;
    private double precoServico;


    private GetContatoResponse contatoResponse;
    private GetServicoResponse2 servicoResponse2;
    private GetAgendaDetalhe modelDetalhe;


    private GetHorarioResponse detalhe;

    private boolean alterando;

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_detalhe_cliente);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Detalhes do horário");

        campoNomeContato = (EditText) findViewById(R.id.campoNomeContato);
        campoHoraInicio = (EditText) findViewById(R.id.campoHoraInicio);
        campoHoraFim = (EditText) findViewById(R.id.campoHoraFim);
        campoPrecoServico = (EditText) findViewById(R.id.campoPrecoServico);
        Locale mLocale = new Locale("pt","BR");
        campoPrecoServico.addTextChangedListener(new MoneyTextWatcher(campoPrecoServico, mLocale));
        campoDataAgenda = (EditText) findViewById(R.id.campoDataAgenda);



        campoDataAgenda.addTextChangedListener(MaskEditUtil.mask(campoDataAgenda, MaskEditUtil.FORMAT_DATE));
        campoHoraInicio.addTextChangedListener(MaskEditUtil.mask(campoHoraInicio, MaskEditUtil.FORMAT_HOUR));
        campoHoraFim.addTextChangedListener(MaskEditUtil.mask(campoHoraFim, MaskEditUtil.FORMAT_HOUR));


        Button botaoSalvarHorario = (Button)findViewById(R.id.botaoSalvarHorario);
        this.botaoSalvarHorario = botaoSalvarHorario;
        botaoSalvarHorario.setOnClickListener(this);

        Button botaoCalcelarHorario = (Button)findViewById(R.id.botaoCancelarHorario);
        this.botaoCancelarHorario = botaoCalcelarHorario;
        botaoCalcelarHorario.setOnClickListener(this);




        this.botaoBuscarServico = (Button)findViewById(R.id.botaoBuscarServico);
        this.botaoBuscarServico.setOnClickListener(this);




        //  contato = (GetContatoResponse)getIntent().getSerializableExtra("contato");

        detalhe = (GetHorarioResponse)getIntent().getSerializableExtra("horario") ;




        this.modelDetalhe = new GetAgendaDetalhe();

        this.modelDetalhe.servico = detalhe.getServico();
        this.modelDetalhe.contato = detalhe.getContato();
        if(detalhe != null)
        {



            campoHoraInicio.setText(detalhe.horaInicio);
            campoHoraFim.setText(detalhe.horaFim);
            campoDataAgenda.setText(detalhe.dataAgenda);

            NumberFormat formatado = NumberFormat.getInstance();
            formatado.setMinimumFractionDigits(2);
            campoPrecoServico.setText(String.valueOf(formatado.format(detalhe.precoServico)));


            //      Toast.makeText(getApplicationContext(),  "Contato:  " + detalhe.dataAgenda, Toast.LENGTH_SHORT).show();

            service.GetDetalhe(modelDetalhe).enqueue(new Callback<GetDetalheAgendaResponse>() {
                @Override
                public void onResponse(Call<GetDetalheAgendaResponse> call, Response<GetDetalheAgendaResponse> response) {




                    nomeServicoFinal.setText(response.body().servico);



                }

                @Override
                public void onFailure(Call<GetDetalheAgendaResponse> call, Throwable t) {

                }
            });




            alterando = true;
        }else
        {
            alterando = false;
        }



        nomeContatoFinal = (TextView) findViewById(R.id.nome_contato);
        nomeServicoFinal = (TextView) findViewById(R.id.nome_servico);





    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;


            case R.id.dashboard:
                Intent intent3 = new Intent(this, DashBoard.class);
                startActivity(intent3);
                return true;

            case R.id.cadastrar_usuario:
                Intent intent1 = new Intent(this, CadastroUsuario.class);
                startActivity(intent1);
                return true;

            case R.id.novo_contato:
                Intent intent2 = new Intent(this, CadastroContato.class);
                startActivity(intent2);
                return true;

            case R.id.lista_contato:
                Intent intent4 = new Intent(this, ContatoLista.class);
                startActivity(intent4);
                return true;

            case R.id.cadastro_servico:
                Intent intent5 = new Intent(this, CadastroServico.class);
                startActivity(intent5);
                return true;


        }
            return false;
    }

    @Override
    public void onClick(View v) {

        if(v== botaoBuscarServico){

            Intent intent = new Intent(this, PesquisaServico.class);
            startActivityForResult(intent, 2);
        }

        if (v==botaoSalvarHorario) {




            dataAgenda = campoDataAgenda.getText().toString();

            horaInicio = campoHoraInicio.getText().toString();
            horaFim = campoHoraFim.getText().toString();

            precoServico = Double.valueOf(campoPrecoServico.getText().toString().replace("R$", "").replace(".", "").replace(",", ".").replace(" ", ""));

            HorarioModel horarioModel = new HorarioModel();
            horarioModel.setStatusAgenda("A");

            horarioModel.setIdAgenda(detalhe.getIdAgenda());
            horarioModel.setDataAgenda(dataAgenda);
            horarioModel.setContato(2);

            if(contato == 0)
            {
                horarioModel.setContato(modelDetalhe.getContato());
            } else
            {
                horarioModel.setContato(contato);
            }

            if(servico == 0 ){
                horarioModel.setServico(modelDetalhe.getServico());
            } else {
                horarioModel.setServico(servico);
            }


            horarioModel.setDataAgenda(dataAgenda);
            horarioModel.setHoraInicio(horaInicio);
            horarioModel.setHoraFim(horaFim);

            horarioModel.setPrecoServico(precoServico);
            horarioModel.setIdCliente(LoginResponse.getIdCliente());


            LogModel logModel = new LogModel();
            logModel.setAgendaId(detalhe.idAgenda);
            logModel.setClienteId(LoginResponse.getIdCliente());
            logModel.setLogStatus("A");
//            logModel.setProfId(modelDetalhe.ge.);

            service.AgendamentoLog(logModel).enqueue(new Callback<HorarioResponse>() {
                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {


                    } else {

                    }
                }

                @Override
                public void onFailure(Call<HorarioResponse> call, Throwable t) {

                }
            });




            service.AltHorario(horarioModel).enqueue(new Callback<HorarioResponse>() {
                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Cadastro efetuado com sucesso";

                    } else {
                        mensagem = "Falha no cadastro:   " + response.body().getMessage();
                    }
                    Intent intent5 = new Intent(getApplicationContext(), Agenda.class);
                    startActivity(intent5);
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<HorarioResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });


        }



        if(v == botaoCancelarHorario)
        {

            LogModel logModel = new LogModel();
            logModel.setAgendaId(detalhe.idAgenda);
            logModel.setClienteId(LoginResponse.getIdCliente());
            logModel.setLogStatus("C");

            service.AgendamentoLog(logModel).enqueue(new Callback<HorarioResponse>() {
                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {


                    } else {

                    }
                }

                @Override
                public void onFailure(Call<HorarioResponse> call, Throwable t) {

                }
            });




            final HorarioModel horarioModel = new HorarioModel();
            horarioModel.setIdAgenda(detalhe.getIdAgenda());
            //service.AltHorario(horarioModel).enqueue(new Callback<HorarioResponse>()
            service.LimpHorarioCliente(horarioModel).enqueue(new Callback<HorarioResponse>() {

                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Serviço Cancelado com Sucesso ";

                    } else {
                        mensagem = "Falha no cadastro:   " + response.body().getMessage();
                    }
                    Intent intent5 = new Intent(getApplicationContext(), Agenda.class);
                    startActivity(intent5);
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<HorarioResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 2){
            if(resultCode == RESULT_OK) {

                servicoResponse2 = (GetServicoResponse2) data.getSerializableExtra("servico");

                nomeServicoFinal.setText(servicoResponse2.getNomeServico());
                NumberFormat formatado = NumberFormat.getInstance();
                formatado.setMinimumFractionDigits(2);
                campoPrecoServico.setText(formatado.format(servicoResponse2.getPrecoServico()));
                this.servico = servicoResponse2.getIdServico();

            }
            if(resultCode == RESULT_CANCELED)
            {
                nomeServicoFinal.setText("Não selecionado");


            }//l

        }

    }
}
