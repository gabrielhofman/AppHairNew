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

public class ServicoLista extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterSerico.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoCadastrar;


    private RecyclerView recyclerView;
    private  AdapterSerico adapterSerico;
    private LinearLayoutManager linearLayoutManager;



    public  List<GetServicoResponse2> teste = new ArrayList<>();


    private ApiService service = ApiControler.CreateController();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_lista);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar Lista teste");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        botaoCadastrar = (Button)findViewById(R.id.cadastrar_servico);
        botaoCadastrar.setOnClickListener(this);


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
        adapterSerico = new AdapterSerico(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterSerico);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterSerico.setItemClicado(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();

        switch (menuItem.getItemId()){
            case R.id.login:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;

            case R.id.cadastrar_usuario:
                Intent intent1 = new Intent(this, CadastroUsuario.class);
                startActivity(intent1);
                return true;

            case R.id.novo_contato:
                Intent intent2 = new Intent(this, NovoContato.class);
                startActivity(intent2);
                return true;
        }

        return false;//
    }

    @Override
    public void onClick(View v) {


        Intent intent5 = new Intent(this, CadastroServico.class);

        startActivity(intent5);

    }

    @Override
    public void noItemClicado(View view, int position) {

        GetServicoResponse2 servico;
        servico = adapterSerico.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(servico.getIdServico()), Toast.LENGTH_SHORT).show();



        Intent intent5 = new Intent(this, CadastroServico.class); // testeeeekjnk


        intent5.putExtra("serv",servico);
        startActivity(intent5);


    }
}
