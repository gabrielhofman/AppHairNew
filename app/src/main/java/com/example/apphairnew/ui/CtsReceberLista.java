package com.example.apphairnew.ui;

import android.content.Context;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterCtsReceber;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.GetCtsReceberResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CtsReceberLista extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AdapterCtsReceber.itemClicadoListener, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Button botaoCadCtsReceb;
    private SearchView campoBuscaCtsReceber;
    private Context context;

    private RecyclerView recyclerView;
    private  AdapterCtsReceber adapterCtsReceber;
    private LinearLayoutManager linearLayoutManager;

//aaaa

    public List<GetCtsReceberResponse> teste = new ArrayList<>();


    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cts_receber_lista);

        navigationView = (NavigationView) findViewById(R.id.navigation_view) ;
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Lista contas a receber");//


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        botaoCadCtsReceb = (Button)findViewById(R.id.botaoCadastrarNovoCtsReceb);
        botaoCadCtsReceb.setOnClickListener(this);
        campoBuscaCtsReceber = (SearchView) findViewById(R.id.campoBuscaLstReceber);
        campoBuscaCtsReceber.setOnQueryTextListener(this);

        CarregarTela();
        this.context = getApplicationContext();




    }

    public void ExcluirItem(int crId){
        service.ExcluirCR(crId).enqueue(new Callback<AddCtsReceberResponse>() {
            @Override
            public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
               Toast.makeText(context, "Apagado com sucesso" , Toast.LENGTH_LONG).show();
                CarregarTela();
            }

            @Override
            public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    public void GerarTela()
    {
        adapterCtsReceber = new AdapterCtsReceber(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterCtsReceber);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapterCtsReceber.setItemClicado(this);
    }

    public void CarregarTela(){
        final int usuario = 1;

        service.getCtsReceber(usuario).enqueue(new Callback<List<GetCtsReceberResponse>>() {
            @Override
            public void onResponse(Call<List<GetCtsReceberResponse>> call, Response<List<GetCtsReceberResponse>> response) {
                teste = response.body();
                GerarTela();
            }

            @Override
            public void onFailure(Call<List<GetCtsReceberResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
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

            case R.id.listar_contatos:
                Intent intent2 = new Intent(this, ContatoLista.class);
                startActivity(intent2);
                return true;

            case R.id.novo_contato:
                Intent intent4 = new Intent(this, CadastroContato.class);
                startActivity(intent4);
                return true;

            case R.id.cadastrar_servico:
                Intent intent5 = new Intent(this, CadastroServico.class);
                startActivity(intent5);
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
                Intent intent9 = new Intent(this, CtsReceberLista.class);
                startActivity(intent9);
                return true;

        }

        return false;//
    }

    @Override
    public void onClick(View v) {

        Intent intent7 = new Intent(this, AddContasReceber.class);
        startActivity(intent7);

    }

    @Override
    public void noItemClicado(View view, int position) {

        GetCtsReceberResponse ctsReceb;
        ctsReceb = adapterCtsReceber.getItem(position);
        Toast.makeText(getApplicationContext(), String.valueOf(ctsReceb.getIdCr()), Toast.LENGTH_SHORT).show();

        Intent intent7 = new Intent(this, AddContasReceber.class);

        intent7.putExtra("ctsReceb", ctsReceb);
        startActivity(intent7);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        GetCtsReceberResponse getCtsReceberResponse = new GetCtsReceberResponse();

        String userInput = newText.toLowerCase();
        List<GetCtsReceberResponse> newList = new ArrayList<>();

        for (GetCtsReceberResponse ctsReceber:teste){
            if (ctsReceber.getRecebVencimento().contains(userInput)){
                newList.add(ctsReceber);
            }
        }
        adapterCtsReceber.updateList(newList);
        return true;
    }
}
