package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterFluxoCaixa;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetFluxoCaixaResponse;
import com.example.apphairnew.response.GetTotalFluxoResponse;
import com.example.apphairnew.ui.cliente.LoginCliente;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FluxoCaixa extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterFluxoCaixa.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private Button botaoFluxo1dia;
    private Button botaoFluxo7dias;
    private Button botaoFluxo30dias;
    private Button botaoFluxoTodosDias;

    private TextView labelValorTotal;


    private RecyclerView recyclerView;
    private AdapterFluxoCaixa adapterFluxoCaixa;
    private LinearLayoutManager linearLayoutManager;



    public List<GetFluxoCaixaResponse> teste = new ArrayList<>();
    public GetTotalFluxoResponse getTotalFluxoResponse = new GetTotalFluxoResponse();

    private ApiService service = ApiControler.CreateController();

    private String cifrao = "R$ ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluxo_caixa);

        navigationView = (NavigationView) findViewById(R.id.navigation_view) ;
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Fluxo de Caixa");//

        botaoFluxo1dia = (Button) findViewById(R.id.botaoFluxo1dia);
        botaoFluxo1dia.setOnClickListener(this);

        this.botaoFluxo7dias = (Button)findViewById(R.id.botaoFluxo7dias);
        this.botaoFluxo7dias.setOnClickListener(this);

        this.botaoFluxo30dias = (Button)findViewById(R.id.botaoFluxo30dias);
        this.botaoFluxo30dias.setOnClickListener(this);

        this.botaoFluxoTodosDias = (Button)findViewById(R.id.botaoFluxoTodosDias);
        this.botaoFluxoTodosDias.setOnClickListener(this);


        this.labelValorTotal = (TextView)findViewById(R.id.label_valor_total) ;



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);




    }

    public void GerarTela()
    {
        if(teste == null)
        {
            Toast.makeText(getApplicationContext(), "nulo ", Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(getApplicationContext(), "nao nulo ", Toast.LENGTH_LONG).show();
        }

        adapterFluxoCaixa = new AdapterFluxoCaixa(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterFluxoCaixa);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterFluxoCaixa.setItemClicado(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
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

            case R.id.agenda:
                Intent intent10 = new Intent(this, Agenda.class);
                startActivity(intent10);
                return true;

            case R.id.listar_contatos:
                Intent intent2 = new Intent(this, ContatoLista.class);
                startActivity(intent2);
                return true;

            case R.id.novo_contato:
                Intent intent4 = new Intent(this, CadastroContato.class);
                startActivity(intent4);
                return true;

            case R.id.listar_servicos:
                Intent intent6 = new Intent(this, ServicoLista.class);
                startActivity(intent6);
                return true;

            case R.id.lista_contas_receber:
                Intent intent7 = new Intent(this, CtsReceberLista.class);
                startActivity(intent7);
                return true;

            case R.id.lista_contas_pagar:
                Intent intent8 = new Intent(this, CtsPagarLista.class);
                startActivity(intent8);
                return true;


            case R.id.fluxo_caixa:
                Intent intent9 = new Intent(this, FluxoCaixa.class);
                startActivity(intent9);
                return true;

            case R.id.log:
                Intent intent11 = new Intent(this, LogLista.class);
                startActivity(intent11);
                return true;

            case R.id.app_cliente:
                Intent intent12 = new Intent(this, LoginCliente.class);
                startActivity(intent12);
                return true;
        }

        return false;//
    }

    @Override
    public void onClick(View view) {

        if(view==botaoFluxo1dia)
        {final int usuario = 1;
            int modelo = 1;
            service.getFluxoCaixa(usuario,modelo).enqueue(new Callback<List<GetFluxoCaixaResponse>>() {
                @Override
                public void onResponse(Call<List<GetFluxoCaixaResponse>> call, Response<List<GetFluxoCaixaResponse>> response) {
                    teste = response.body();
                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetFluxoCaixaResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

            service.getTotalFluxo(usuario,modelo).enqueue(new Callback<GetTotalFluxoResponse>() {
                @Override
                public void onResponse(Call<GetTotalFluxoResponse> call, Response<GetTotalFluxoResponse> response) {
                    getTotalFluxoResponse = response.body();
                    labelValorTotal.setText(cifrao + String.valueOf(getTotalFluxoResponse.getTotalFluxo()));
                }

                @Override
                public void onFailure(Call<GetTotalFluxoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();

                }
            });
        }

        if(view==botaoFluxo7dias)
        {final int usuario = 1;
            int modelo = 2;
            service.getFluxoCaixa(usuario,modelo).enqueue(new Callback<List<GetFluxoCaixaResponse>>() {
                @Override
                public void onResponse(Call<List<GetFluxoCaixaResponse>> call, Response<List<GetFluxoCaixaResponse>> response) {
                    teste = response.body();
                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetFluxoCaixaResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

            service.getTotalFluxo(usuario,modelo).enqueue(new Callback<GetTotalFluxoResponse>() {
                @Override
                public void onResponse(Call<GetTotalFluxoResponse> call, Response<GetTotalFluxoResponse> response) {
                    getTotalFluxoResponse = response.body();
                    labelValorTotal.setText(cifrao + String.valueOf(getTotalFluxoResponse.getTotalFluxo()));
                }

                @Override
                public void onFailure(Call<GetTotalFluxoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();

                }
            });
        }

        if(view==botaoFluxo30dias)
        {final int usuario = 1;
            int modelo = 3;
            service.getFluxoCaixa(usuario,modelo).enqueue(new Callback<List<GetFluxoCaixaResponse>>() {
                @Override
                public void onResponse(Call<List<GetFluxoCaixaResponse>> call, Response<List<GetFluxoCaixaResponse>> response) {
                    teste = response.body();
                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetFluxoCaixaResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

            service.getTotalFluxo(usuario,modelo).enqueue(new Callback<GetTotalFluxoResponse>() {
                @Override
                public void onResponse(Call<GetTotalFluxoResponse> call, Response<GetTotalFluxoResponse> response) {
                    getTotalFluxoResponse = response.body();
                    labelValorTotal.setText(cifrao + String.valueOf(getTotalFluxoResponse.getTotalFluxo()));
                }

                @Override
                public void onFailure(Call<GetTotalFluxoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();

                }
            });
        }





        if(view==botaoFluxoTodosDias)
        {

            final int usuario = 1;
            int modelo = 4;
            service.getFluxoCaixa(usuario,modelo).enqueue(new Callback<List<GetFluxoCaixaResponse>>() {
                @Override
                public void onResponse(Call<List<GetFluxoCaixaResponse>> call, Response<List<GetFluxoCaixaResponse>> response) {
                    teste = response.body();
                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetFluxoCaixaResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });



            service.getTotalFluxo(usuario,modelo).enqueue(new Callback<GetTotalFluxoResponse>() {
                @Override
                public void onResponse(Call<GetTotalFluxoResponse> call, Response<GetTotalFluxoResponse> response) {
                    getTotalFluxoResponse = response.body();
                    labelValorTotal.setText( cifrao + String.valueOf(getTotalFluxoResponse.getTotalFluxo()));
                }

                @Override
                public void onFailure(Call<GetTotalFluxoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro: "+t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();

                }
            });


        }


    }

    @Override
    public void noItemClicado(View view, int position) {

    }
}
