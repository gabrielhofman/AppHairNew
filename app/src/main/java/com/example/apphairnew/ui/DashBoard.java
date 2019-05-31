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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetAgendaTotalResponse;
import com.example.apphairnew.response.GetCpTotal;
import com.example.apphairnew.response.GetCrTotal;
import com.example.apphairnew.response.GetTotalFluxoResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private LinearLayout contasReceber;
    private TextView valorCr;

    private LinearLayout contasPagar;
    private TextView valorCp;

    private LinearLayout horasOfertadas;
    private TextView totalOfertadas;

    private LinearLayout horasLivres;
    private TextView totalLivres;

    private LinearLayout horasAgendadas;
    private TextView totalAgendado;

    private LinearLayout fluxoCaixa;
    private TextView valorFluxoCaixa;
    private String cifrao = "R$ ";


    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);


        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("DashBoard");


        final int usuario = 1;
        int modelo = 4;


        //CR
        contasReceber = (LinearLayout)findViewById(R.id.contas_receber);
        contasReceber.setOnClickListener(this);
        valorCr = (TextView)findViewById(R.id.valor_cr);


        service.getTotalCr(usuario).enqueue(new Callback<GetCrTotal>() {
            @Override
            public void onResponse(Call<GetCrTotal> call, Response<GetCrTotal> response) {
                String vCr = cifrao + String.valueOf(response.body().recebTotal);
                valorCr.setText(vCr);
            }

            @Override
            public void onFailure(Call<GetCrTotal> call, Throwable t) {

            }
        });



        //CP
        contasPagar = (LinearLayout)findViewById(R.id.contas_pagar);
        contasPagar.setOnClickListener(this);
        valorCp = (TextView)findViewById(R.id.valor_cp);

        service.getTotalCp(usuario).enqueue(new Callback<GetCpTotal>() {
            @Override
            public void onResponse(Call<GetCpTotal> call, Response<GetCpTotal> response) {
                String vCp = cifrao + String.valueOf(response.body().pagarValor);
                valorCp.setText(vCp);
            }

            @Override
            public void onFailure(Call<GetCpTotal> call, Throwable t) {

            }
        });



        //parte do fluxo de cx e serviço

        fluxoCaixa = (LinearLayout)findViewById(R.id.fluxo_caixa);
        fluxoCaixa.setOnClickListener(this);
        valorFluxoCaixa = (TextView)findViewById(R.id.valor_fluxo_caixa);


        service.getTotalFluxo(usuario,modelo).enqueue(new Callback<GetTotalFluxoResponse>() {
            @Override
            public void onResponse(Call<GetTotalFluxoResponse> call, Response<GetTotalFluxoResponse> response) {
                String vFluxo = cifrao + String.valueOf(response.body().totalFluxo);
                valorFluxoCaixa.setText(vFluxo);

            }

            @Override
            public void onFailure(Call<GetTotalFluxoResponse> call, Throwable t) {

            }
        });



        //horas ofertadas
        horasOfertadas = (LinearLayout)findViewById(R.id.horas_ofertadas);
        horasOfertadas.setOnClickListener(this);
        totalOfertadas = (TextView)findViewById(R.id.total_ofertadas);


        service.getTotalAgendaOfertada(usuario).enqueue(new Callback<GetAgendaTotalResponse>() {
            @Override
            public void onResponse(Call<GetAgendaTotalResponse> call, Response<GetAgendaTotalResponse> response) {
                totalOfertadas.setText(String.valueOf(response.body().totalAgenda));
            }

            @Override
            public void onFailure(Call<GetAgendaTotalResponse> call, Throwable t) {

            }
        });



        //horas livres
        horasLivres = (LinearLayout)findViewById(R.id.horas_livres);
        horasLivres.setOnClickListener(this);
        totalLivres = (TextView)findViewById(R.id.total_livres);

        service.getTotalAgendaLivre(usuario).enqueue(new Callback<GetAgendaTotalResponse>() {
            @Override
            public void onResponse(Call<GetAgendaTotalResponse> call, Response<GetAgendaTotalResponse> response) {
                totalLivres.setText(String.valueOf(response.body().totalAgenda));
            }

            @Override
            public void onFailure(Call<GetAgendaTotalResponse> call, Throwable t) {

            }
        });


        //horas agendadas
        horasAgendadas = (LinearLayout)findViewById(R.id.horas_agendadas);
        horasAgendadas.setOnClickListener(this);
        totalAgendado = (TextView)findViewById(R.id.total_agendado);

        service.getTotalAgendaAgendado(usuario).enqueue(new Callback<GetAgendaTotalResponse>() {
            @Override
            public void onResponse(Call<GetAgendaTotalResponse> call, Response<GetAgendaTotalResponse> response) {
                totalAgendado.setText(String.valueOf(response.body().totalAgenda));

            }

            @Override
            public void onFailure(Call<GetAgendaTotalResponse> call, Throwable t) {

            }
        });




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;

            case R.id.dashboard:
                Intent intent3 = new Intent(this,DashBoard.class);
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
    public void onClick(View view) {
        if(view==contasReceber)
        {


        Intent intent = new Intent(this, CtsReceberLista.class);
        startActivity(intent);


        }
        if(view==fluxoCaixa)
        {
            Intent intent = new Intent(this, FluxoCaixa.class);
            startActivity(intent);
        }

        if(view==contasPagar)
        {
            Intent intent = new Intent(this, CtsPagarLista.class);
            startActivity(intent);
        }
        if(view==horasOfertadas)
        {
            Intent intent = new Intent(this, Agenda.class);
            startActivity(intent);
        }
        if(view==horasAgendadas)
        {
            Intent intent = new Intent(this, Agenda.class);
            startActivity(intent);
        }
        if(view==horasLivres)
        {
            Intent intent = new Intent(this, Agenda.class);
            startActivity(intent);
        }

    }
}
