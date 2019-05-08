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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterContato;
import com.example.apphairnew.Adapter.AdapterSerico;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaServico extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, AdapterSerico.itemClicadoListener, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView campoBuscaServico;



    private RecyclerView recyclerView;
    private AdapterSerico adapterServico;
    private LinearLayoutManager linearLayoutManager;




       public List<GetServicoResponse2> teste = new ArrayList<>();
       private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_servico);


        setContentView(R.layout.activity_pesquisa_servico);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Servicos");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_servico);


        campoBuscaServico = (SearchView) findViewById(R.id.campoBuscarServico);
        campoBuscaServico.setOnQueryTextListener(this);


        final int usuario = 1;


        service.getServico(usuario).enqueue(new Callback<List<GetServicoResponse2>>() {
            @Override
            public void onResponse(Call<List<GetServicoResponse2>> call, Response<List<GetServicoResponse2>> response) {
                teste = response.body();

                GerarTela();
            }

            @Override
            public void onFailure(Call<List<GetServicoResponse2>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();

            }
        });



    }

    public void GerarTela()
    {
        adapterServico = new AdapterSerico(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterServico);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterServico.setItemClicado(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void noItemClicado(View view, int position) {

        GetServicoResponse2 servico;
        servico = adapterServico.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(servico.getIdServico()), Toast.LENGTH_SHORT).show();



        Intent resultIntent = new Intent();
        resultIntent.putExtra("servico", servico);
        setResult(RESULT_OK, resultIntent);
        finish();

    }
}
