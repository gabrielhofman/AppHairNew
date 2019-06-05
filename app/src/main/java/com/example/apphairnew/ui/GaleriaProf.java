package com.example.apphairnew.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apphairnew.Adapter.AdapterGaleria;
import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.GaleriaModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.response.AddGaleriaProfResponse;
import com.example.apphairnew.response.GetGaleriaProfResponse;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.cliente.LoginCliente;
import com.example.apphairnew.web.ApiControler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GaleriaProf extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Context context;

    private ImageView fotoGaleria;
    private String bmFotoGaleria;
    private static final int PICK_IMAGE = 100;
    Uri fotoUri;

    //private Button botaoCadastrar;
    private Button botaoAddFoto;

    private RecyclerView recyclerView;
    private AdapterGaleria adapterGaleria;
    private LinearLayoutManager linearLayoutManager;

    public List<GetGaleriaProfResponse> teste = new ArrayList<>();

    //botaoCadastrar = (Button)findViewById(R.id.cadastrar_servico);
    // botaoCadastrar.setOnClickListener(this);
    private ApiService service = ApiControler.CreateController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_prof);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar Lista teste");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


            botaoAddFoto = (Button) findViewById(R.id.btn_add_foto);
            botaoAddFoto.setOnClickListener(this);
            Toast.makeText(getApplicationContext(), "Entrou no loop id login :" + LoginResponse.getIdProf() , Toast.LENGTH_LONG).show();
            if (LoginResponse.getIdProf() == 0) {

                botaoAddFoto.setVisibility(View.INVISIBLE);

        }
        //botaoCadastrar = (Button)findViewById(R.id.cadastrar_servico);
        // botaoCadastrar.setOnClickListener(this);

        CarregarTela();
        this.context = getApplicationContext();
    }

    public void GerarTela()
    {
        adapterGaleria = new AdapterGaleria(teste,this);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterGaleria);
        recyclerView.setLayoutManager(linearLayoutManager);

       // adapterGaleria.setItemClicado(this);
    }

    public void CarregarTela() {

        if(LoginResponse.getIdProf() >= 1) {

            //service.getServico(usuario).enqueue(new Callback<List<GetServicoResponse2>>() {
            service.getGaleriaProf(LoginResponse.getIdProf()).enqueue(new Callback<List<GetGaleriaProfResponse>>() {
                @Override
                public void onResponse(Call<List<GetGaleriaProfResponse>> call, Response<List<GetGaleriaProfResponse>> response) {
                    teste = response.body();


                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetGaleriaProfResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        }else{
            service.getGaleriaProf(1).enqueue(new Callback<List<GetGaleriaProfResponse>>() {
                @Override
                public void onResponse(Call<List<GetGaleriaProfResponse>> call, Response<List<GetGaleriaProfResponse>> response) {
                    teste = response.body();

                    GerarTela();
                }

                @Override
                public void onFailure(Call<List<GetGaleriaProfResponse>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });

        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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

        return false;
    }

    @Override
    public void onClick(View v) {
        if (v==botaoAddFoto){
            abrirGaleria();


            //GaleriaModel galeriaModel = new GaleriaModel();
           // galeriaModel.setBmFotoGaleria(bmFotoGaleria);
            if (bmFotoGaleria == null){
                Toast.makeText(getApplicationContext(), "FOTO NA GALERIA NULO", Toast.LENGTH_SHORT).show();
            }else{

            //service.CadContato(contatoModel).enqueue(new Callback<CadContatoResponse>()
                Toast.makeText(getApplicationContext(), "FOTO NA GALERIA selecionada", Toast.LENGTH_SHORT).show();
        }}
    }

    public void abrirGaleria(){
        Intent galeriam = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeriam, PICK_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            fotoUri = data.getData();
            //fotoGaleria.to;
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fotoUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,0,bos);
            byte[] bb = bos.toByteArray();
            bmFotoGaleria = Base64.encodeToString(bb,1);

            if(fotoUri == null) {
                Toast.makeText(getApplicationContext(), "Nulaço", Toast.LENGTH_SHORT).show();
            }else{

                GaleriaModel galeriaModel = new GaleriaModel();
                galeriaModel.setBmFotoGaleria(bmFotoGaleria);
                galeriaModel.setProf_id(LoginResponse.getIdProf());

                Toast.makeText(getApplicationContext(),"n nulo", Toast.LENGTH_SHORT).show();
                service.AddGaleriaProf(galeriaModel).enqueue(new Callback<AddGaleriaProfResponse>() {
                    @Override
                    public void onResponse(Call<AddGaleriaProfResponse> call, Response<AddGaleriaProfResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()){
                            mensagem = "Cadastro efetuado com sucesso";
                            Intent intent = new Intent(getApplicationContext(), GaleriaProf.class);
                            startActivity(intent);
                        }else{
                            mensagem = "Falha no cadastro" + response.body().getMessage();
                        }
                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddGaleriaProfResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }
        }
    }
}
