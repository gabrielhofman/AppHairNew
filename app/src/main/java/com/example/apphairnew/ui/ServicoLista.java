package com.example.apphairnew.ui;

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

import com.example.apphairnew.Adapter.AdapterSerico;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.cliente.LoginCliente;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicoLista extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterSerico.itemClicadoListener, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoCadastrar;
    private SearchView campoBuscaServico;
    private Context context;


    private RecyclerView recyclerView;
    private  AdapterSerico adapterSerico;
    private LinearLayoutManager linearLayoutManager;


//aaa
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
        campoBuscaServico = (SearchView) findViewById(R.id.campoBuscaServico);
        campoBuscaServico.setOnQueryTextListener(this);

        CarregarTela();
        this.context = getApplicationContext();








    }

    public void GerarTela()
    {
        adapterSerico = new AdapterSerico(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterSerico);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterSerico.setItemClicado(this);
    }

    //public void ExcluirItem(int idServico){
      //  service.ExcluirServico(idServico).enqueue(new Callback<CadServicoResponse>() {
        //    @Override
          //  public void onResponse(Call<CadServicoResponse> call, Response<CadServicoResponse> response) {
//
  //          }
//
  ///          @Override
     //       public void onFailure(Call<CadServicoResponse> call, Throwable t) {
       //         Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
         //       t.printStackTrace();
           // }
        //});

    //}

    public void CarregarTela(){
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
        //drawerLayout.closeDrawers();

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
    public void onClick(View v) {


        Intent intent5 = new Intent(this, CadastroServico.class);

        startActivity(intent5);

    }

    @Override
    public void noItemClicado(View view, int position) {

        GetServicoResponse2 servico;
        servico = adapterSerico.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(servico.getIdServico()), Toast.LENGTH_SHORT).show();



        Intent intent5 = new Intent(this, CadastroServico.class); // testeeeekjnkjhhyhhhhggg


        intent5.putExtra("serv",servico);
        startActivity(intent5);


    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        GetServicoResponse2 getServicoResponse2 = new GetServicoResponse2();

        String userInput = newText.toLowerCase();
        List<GetServicoResponse2> newList = new ArrayList<>();

        for (GetServicoResponse2 servico:teste){
            if(servico.getNomeServico().contains(userInput)){
                newList.add(servico);
            }
        }
        adapterSerico.updateList(newList);
        return true;
    }
}
