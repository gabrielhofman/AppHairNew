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
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaContato extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterContato.itemClicadoListener, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView campoBuscaContato;



    private RecyclerView recyclerView;
    private AdapterContato adapterContato;
    private LinearLayoutManager linearLayoutManager;


    public List<GetContatoResponse> teste = new ArrayList<>();

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa_contato);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Contatos");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_contato);


        campoBuscaContato = (SearchView) findViewById(R.id.campoBuscaContato);
        campoBuscaContato.setOnQueryTextListener(this);




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
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        GetContatoResponse getContatoResponse = new GetContatoResponse();

        String userInput = newText.toLowerCase();
        List<GetContatoResponse> newList = new ArrayList<>();

        for (GetContatoResponse contato:teste){

            if(contato.getNomeContato().contains(userInput) || contato.getTelContato().contains(userInput))
            {
                newList.add(contato);
            }
        }

        adapterContato.updateList(newList);

        return true;
    }

    @Override
    public void noItemClicado(View view, int position) {

        GetContatoResponse contato;
        contato = adapterContato.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(contato.getId()), Toast.LENGTH_SHORT).show();


        Intent resultIntent = new Intent();
        resultIntent.putExtra("contato", contato);
        setResult(RESULT_OK, resultIntent);
        finish();






//        Intent intent2 = new Intent(this,CadastroContato.class);

  //      intent2.putExtra("contato", contato);
 //       startActivity(intent2);


    }
}
