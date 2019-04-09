package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.response.HorarioResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheAgendamento extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeContato;
    private EditText campoHoraInicio;
    private EditText campoHoraFim;
    private EditText campoNomeServico;
    private EditText campoPrecoServico;

    private Button botaoSalvarHorario;
    private Button botaoConcluirHorario;
    private Button botaoCancelarHorario;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private String nomeContato, horaInicio, horaFim, nomeServico;
    private Float precoServico;
    HorarioModel horarioModel = new HorarioModel();

    private ApiService service = ApiControler.CreateController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_agendamento);

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
        campoNomeServico = (EditText) findViewById(R.id.campoTipoServico);
        campoPrecoServico = (EditText) findViewById(R.id.campoPrecoServico);

        Button botaoSalvarHorario = (Button)findViewById(R.id.botaoSalvarHorario);
        this.botaoSalvarHorario = botaoSalvarHorario;
        botaoSalvarHorario.setOnClickListener(this);

        Button botaoConcluirHorario = (Button)findViewById(R.id.botaoConcluirHorario);
        this.botaoConcluirHorario = botaoConcluirHorario;
        botaoConcluirHorario.setOnClickListener(this);

        Button botaoCalcelarHorario = (Button)findViewById(R.id.botaoCancelarHorario);
        this.botaoCancelarHorario = botaoCalcelarHorario;
        botaoCalcelarHorario.setOnClickListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();

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

            case R.id.contatos:
                Intent intent4 = new Intent(this, Contatos.class);
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

        if (v==botaoSalvarHorario){
            nomeContato = campoNomeContato.getText().toString();
            horaInicio = campoHoraInicio.getText().toString();
            horaFim = campoHoraFim.getText().toString();
            nomeServico = campoNomeServico.getText().toString();
            precoServico = Float.valueOf(campoPrecoServico.getText().toString());

            service.MarcarHorario(horarioModel).enqueue(new Callback<HorarioResponse>() {
                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Cadastro efetuado com sucesso";
                    } else {
                        mensagem = "Falha no cadastro:   " + response.body().getMessage();
                    }

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
}
