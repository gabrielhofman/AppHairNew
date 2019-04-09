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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterSerico;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.GetServicoResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicoListaTeste extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterSerico.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private RecyclerView recyclerView;
    private AdapterSerico adapterSerico;
    private LinearLayoutManager linearLayoutManager;








    private ServicoModel modelo = new ServicoModel();

    private ArrayList<ServicoModel> modelos = new ArrayList<>();

    private ArrayList<ServicoModel> modelos2 = new ArrayList<>();


    private ApiService service = ApiControler.CreateController();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico_lista_teste);

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

//


        ServicoModel serv1 = new ServicoModel();

        serv1.setNomeServico("adobe");

        ServicoModel serv2 = new ServicoModel();

        serv2.setNomeServico("DELTAAAAAAAAAAAAAAAAA");

        this.modelos.add(serv1);
        this.modelos.add(serv2);

        //  adapterProduto = new AdapterProduto(listaProduto, this);
        //        linearLayoutManager = new LinearLayoutManager(this);
        //        recyclerView.setAdapter(adapterProduto);
        //        recyclerView.setLayoutManager(linearLayoutManager);
        //
        //        adapterProduto.setItemClicado(this);






        int usuario = 1;
       // service.getServico(usuario).enqueue(new Callback<List<GetServicoResponse2>>);




        service.getServico(usuario).enqueue(new Callback<GetServicoResponse>() {
            @Override
            public void onResponse(Call<GetServicoResponse> call, Response<GetServicoResponse> response) {
                String teste;
         //      modelos2.addAll(response.body().getServicos());



                Toast.makeText(getApplicationContext(), "AQUI", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetServicoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

        adapterSerico = new AdapterSerico(this.modelos2,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterSerico);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterSerico.setItemClicado(this);

    //    Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

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

    }

    @Override
    public void noItemClicado(View view, int position) {

    }
}
