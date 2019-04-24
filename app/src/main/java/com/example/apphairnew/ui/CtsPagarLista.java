package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.Toast;


import com.example.apphairnew.Adapter.AdapterCtsPagar;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetCtsPagarResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CtsPagarLista extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterCtsPagar.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoCadastrarNovoCtsPagar;


    private RecyclerView recyclerView;
    private  AdapterCtsPagar adapterCtsPagar;
    private LinearLayoutManager linearLayoutManager;

    public List<GetCtsPagarResponse> teste = new ArrayList<>();

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cts_pagar_lista);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Contas a pagar");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        botaoCadastrarNovoCtsPagar  = (Button)findViewById(R.id.botaoCadastrarNovoCtsPagar);
        botaoCadastrarNovoCtsPagar.setOnClickListener(this);

        final int usuario = 1;//oi

        service.getCtsPagar(usuario).enqueue(new Callback<List<GetCtsPagarResponse>>() {
            @Override
            public void onResponse(Call<List<GetCtsPagarResponse>> call, Response<List<GetCtsPagarResponse>> response) {
                teste = response.body();
                GerarTela();
                if(teste != null)
                {
                    Toast.makeText(getApplicationContext(), "NÃO TA NULO", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Ta nulo", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<GetCtsPagarResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void GerarTela()
    {
        adapterCtsPagar = new AdapterCtsPagar(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterCtsPagar);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterCtsPagar.setItemClicado(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();

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

            case R.id.add_contas_receber:
                Intent intent7 = new Intent(this,AddContasReceber.class);
                startActivity(intent7);
                return true;


        }

        return false;//maoe
    }

    @Override
    public void onClick(View v) {

        Intent intent8 = new Intent(this, AddContasPagar.class);
        startActivity(intent8);

    }

    @Override
    public void noItemClicado(View view, int position) {

        GetCtsPagarResponse ctsPagar;
        ctsPagar = adapterCtsPagar.getItem(position);
        Toast.makeText(getApplicationContext(),String.valueOf(ctsPagar.getIdCp()), Toast.LENGTH_SHORT).show();

        Intent intent8 = new Intent(this, AddContasPagar.class);
        intent8.putExtra("ctsPagar", ctsPagar);
        startActivity(intent8);

    }
}
