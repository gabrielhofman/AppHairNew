package com.example.apphairnew.ui.cliente;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterGaleria;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.UsuarioModel;
import com.example.apphairnew.response.GetGaleriaProfResponse;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.GaleriaProf;
import com.example.apphairnew.ui.Login;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheProfCliente extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{


    private TextView labelNomeEstabCliente;
    private TextView labelDescEstabCliente;
    private TextView labelCEPCliente;
    private TextView labelCidadeCliente;
    private TextView labelUFCliente;
    private TextView labelBairroCliente;
    private TextView labelLogradouroCliente;
    private TextView labelNumeroCliente;
    private TextView labelComplementoCliente;
    private Context context;

    private RecyclerView recyclerView;
    private AdapterGaleria adapterGaleria;
    private LinearLayoutManager linearLayoutManager;

    public List<GetGaleriaProfResponse> teste2 = new ArrayList<>();

    private ImageView fotoProfissional;
    private String bmFotoProfissional;
    private static final int PICK_IMAGE = 100;
    Uri fotoUri;

    private Button botaoFotosProfCliente;
    private Button botaoMarcarHorarioCliente;


    public List<GetProfResponse> teste = new ArrayList<>();



    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProfModel profModel;

    private GetProfResponse profissional;
    private ApiService service = ApiControler.CreateController();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_prof_cliente);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Detalhes do profissional");

        labelNomeEstabCliente = (TextView) findViewById(R.id.labelNomeEstabDetCliente);
        labelDescEstabCliente = (TextView) findViewById(R.id.labelDescEstabProfDetCliente);
        labelCEPCliente = (TextView) findViewById(R.id.labelCEPProfDetCliente);
        labelCidadeCliente = (TextView) findViewById(R.id.labelCidadeProfDetCliente);
        labelUFCliente = (TextView) findViewById(R.id.labelUFProfDetCliente);
        labelBairroCliente = (TextView) findViewById(R.id.labelBairroProfDetCliente);
        labelLogradouroCliente = (TextView) findViewById(R.id.labelRuaProfDetCliente);
        labelNumeroCliente = (TextView) findViewById(R.id.labelNumeroProfDetCliente);
        labelComplementoCliente = (TextView) findViewById(R.id.labelComplementoProfDetCliente);
        fotoProfissional = (ImageView) findViewById(R.id.fotoProfDetCliente);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);




        Button botaoMarcarHorarioCliente = (Button) findViewById(R.id.botaoMarcarHrProfDetCliente);
        this.botaoMarcarHorarioCliente = botaoMarcarHorarioCliente;
        this.botaoMarcarHorarioCliente.setOnClickListener(this);

        this.profModel = new ProfModel();
        this.profissional = (GetProfResponse) getIntent().getSerializableExtra("profissional") ;






       // this.modelDetalhe = new GetAgendaDetalhe();

       // this.modelDetalhe.contato = detalhe.getContato();

        //campoHoraInicio.setText(detalhe.horaInicio);

        labelNomeEstabCliente.setText(profissional.nomeEstab);
        labelDescEstabCliente.setText(profissional.descEstab);
        labelCEPCliente.setText(profissional.cep);
        labelCidadeCliente.setText(profissional.cidade);
        labelUFCliente.setText(profissional.uf);
        labelBairroCliente.setText(profissional.bairro);
        labelLogradouroCliente.setText(profissional.logradouro);
        labelNumeroCliente.setText(profissional.numero);
        labelComplementoCliente.setText(profissional.complemento);

        byte[] decodedString = Base64.decode(profissional.getBmFotoProfissional(), Base64.DEFAULT);
        // System.out.println(contatoModel.getFotoContato());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        fotoProfissional.setImageBitmap(decodedByte);

        CarregarTela();
        this.context = getApplicationContext();


    }

    public void GerarTela()
    {

        adapterGaleria = new AdapterGaleria(teste2,this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(adapterGaleria);
        recyclerView.setLayoutManager(linearLayoutManager);

        // adapterGaleria.setItemClicado(this);
    }

    public void CarregarTela() {

        service.getGaleriaProf(1).enqueue(new Callback<List<GetGaleriaProfResponse>>() {
                @Override
                public void onResponse(Call<List<GetGaleriaProfResponse>> call, Response<List<GetGaleriaProfResponse>> response) {
                    teste2 = response.body();

                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetGaleriaProfResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

        }


    @Override
    public void onClick(View v) {



        if(v == botaoMarcarHorarioCliente)
        {

            Intent intent = new Intent(this, HorariosSalaoCliente.class);
            intent.putExtra("profissional", profissional);
            startActivity(intent);

        }

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
}
