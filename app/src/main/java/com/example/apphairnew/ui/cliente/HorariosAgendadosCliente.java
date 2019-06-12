package com.example.apphairnew.ui.cliente;

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
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterAgenda;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.GetHorarioResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.Agenda;
import com.example.apphairnew.ui.CadastroContato;
import com.example.apphairnew.ui.CadastroServico;
import com.example.apphairnew.ui.CadastroUsuario;
import com.example.apphairnew.ui.ContatoLista;
import com.example.apphairnew.ui.DashBoard;
import com.example.apphairnew.ui.Login;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorariosAgendadosCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterAgenda.itemClicadoListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    private RecyclerView recyclerView;
    private AdapterAgenda adapterAgenda;
    private LinearLayoutManager linearLayoutManager;

    public List<GetHorarioResponse> teste = new ArrayList<>();

    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_agendados_cliente);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Horarios Agendados");


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_agenda);

        BuscarAgendasAgendadas();


    }
    public void BuscarAgendasAgendadas()
    {


        service.getAgendaCliente(LoginResponse.getIdCliente()).enqueue(new Callback<List<GetHorarioResponse>>() {
            @Override
            public void onResponse(Call<List<GetHorarioResponse>> call, Response<List<GetHorarioResponse>> response) {
                teste = response.body();
                GerarTela();
            }

            @Override
            public void onFailure(Call<List<GetHorarioResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });

    }

    public void GerarTela()
    {


        adapterAgenda = new AdapterAgenda(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterAgenda);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterAgenda.setItemClicado(this);
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

        GetHorarioResponse horario;
        horario = adapterAgenda.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(horario.getIdAgenda()), Toast.LENGTH_SHORT).show();


        Intent intent9 = new Intent(this, HorarioDetalheCliente.class);

        intent9.putExtra("horario", horario);
        startActivity(intent9);

    }
}

