package com.example.apphairnew.ui.cliente;

import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.cliente.AdapterProfCliente;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.CadastroUsuario;
import com.example.apphairnew.ui.Login;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfListaCliente extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterProfCliente.itemClicadoListener, SearchView.OnQueryTextListener{

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SearchView campoBuscaProfCliente;
    private Context context;
    private GetProfResponse profissional;

    private RecyclerView recyclerView;
    private AdapterProfCliente adapterProfCliente;
    private LinearLayoutManager linearLayoutManager;

    public List<GetProfResponse> teste = new ArrayList<>();

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_lista_cliente);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Profissionais");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_prof_cliente);
        campoBuscaProfCliente = (SearchView) findViewById(R.id.campoBuscaProfCliente);
        campoBuscaProfCliente.setOnQueryTextListener(this);

        CarregarTela();
        this.context = getApplicationContext();
    }

    public void GerarTela(){
        adapterProfCliente = new AdapterProfCliente(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterProfCliente);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterProfCliente.setItemClicado(this);
    }

    public void CarregarTela(){
        //service.getProfCliente().enqueue();

        //profissional = (GetProfResponse) getIntent().getSerializableExtra("profissional");
        //service.getProfCliente().enqueue(Callback<List<GetProfResponse>> callback);

        service.getProfCliente().enqueue(new Callback<List<GetProfResponse>>() {
            @Override
            public void onResponse(Call<List<GetProfResponse>> call, Response<List<GetProfResponse>> response) {
                teste = response.body();
                GerarTela();
            }

            @Override
            public void onFailure(Call<List<GetProfResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        //drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.login_cliente:
                Intent intent = new Intent(this, LoginCliente.class);
                startActivity(intent);
                return true;

            case R.id.salao:
                Intent intent3 = new Intent(this,ProfListaCliente.class);
                startActivity(intent3);
                return true;

            case R.id.meus_horarios:
                Intent intent1 = new Intent(this, HorariosAgendadosCliente.class);
                startActivity(intent1);
                return true;

            case R.id.perfil:
                Intent intent2 = new Intent(this, CadastroCliente.class);
                startActivity(intent2);
                return true;

            case R.id.profissional:
                Intent intent4 = new Intent(this, Login.class);
                startActivity(intent4);
                return true;



        }
        return false;
    }


    @Override
    public void noItemClicado(View view, int position) {
        GetProfResponse profissional;
        profissional = adapterProfCliente.getItem(position);

        Toast.makeText(getApplicationContext(), String.valueOf(profissional.getProf_id()), Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, DetalheProfCliente.class);
        intent.putExtra("profissional", profissional);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        GetProfResponse getProfResponse = new GetProfResponse();

        String userInput = newText.toLowerCase();
        List<GetProfResponse> newList = new ArrayList<>();

        for (GetProfResponse profissional:teste){
           if(profissional.getNomeEstab().contains(userInput) || profissional.getCidade().contains(userInput)){
               newList.add(profissional);
           }
        }
        adapterProfCliente.updateList(newList);
        return true;
    }
}
