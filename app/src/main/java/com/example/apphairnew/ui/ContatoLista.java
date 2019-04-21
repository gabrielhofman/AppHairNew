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

import com.example.apphairnew.Adapter.AdapterContato;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatoLista extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterContato.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoCadastrarContato;


    private RecyclerView recyclerView;
    private  AdapterContato adapterContato;
    private LinearLayoutManager linearLayoutManager;

    public List<GetContatoResponse> teste = new ArrayList<>();

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato_lista);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Contatos");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contato);

        botaoCadastrarContato  = (Button)findViewById(R.id.botaoCadastrarContato);

        final int usuario = 1;//oi

        service.getContato(usuario).enqueue(new Callback<List<GetContatoResponse>>() {
            @Override
            public void onResponse(Call<List<GetContatoResponse>> call, Response<List<GetContatoResponse>> response) {
                teste = response.body();
                    GerarTela();

            }

            @Override
            public void onFailure(Call<List<GetContatoResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });


    }


    public void GerarTela()
    {
        adapterContato = new AdapterContato(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterContato);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterContato.setItemClicado(this);
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

        }

        return false;//
    }

    @Override
    public void onClick(View v) {

        Intent intent2 = new Intent(this, CadastroContato.class);
        startActivity(intent2);
    }

    @Override
    public void noItemClicado(View view, int position) {

        GetContatoResponse contato;
        contato = adapterContato.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(contato.getId()), Toast.LENGTH_SHORT).show();

        Intent intent2 = new Intent(this,CadastroContato.class);

        intent2.putExtra("contato", contato);
        startActivity(intent2);



    }
}
