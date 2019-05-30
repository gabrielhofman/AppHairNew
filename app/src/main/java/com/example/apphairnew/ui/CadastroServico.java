package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroServico extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeServico;
    private EditText campoDescServico;
    private EditText campoPrecoServico;



    private Button botaoCadastro;
    private Button botaoCancelar;


    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ServicoModel servicoModel;

    private String nomeServico, descServico;
    private double precoServico;

    private boolean alterando;

    private ApiService service = ApiControler.CreateController();

    private  GetServicoResponse2 serv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar serviço");

        campoNomeServico = (EditText) findViewById(R.id.campoNomeServico);
        campoDescServico = (EditText) findViewById(R.id.campoDescServico);
        campoPrecoServico = (EditText) findViewById(R.id.campoPrecoServico);


       campoPrecoServico.addTextChangedListener(MaskEditUtil.mask(campoPrecoServico, MaskEditUtil.FORMAT_VALOR));




        Button botaoCadastro = (Button)findViewById(R.id.botaoCadastrarServico);
        this.botaoCadastro = botaoCadastro;
        botaoCadastro.setOnClickListener(this);

        Button botaoCancelar = (Button)findViewById(R.id.botaoCancelarServico);
        this.botaoCancelar = botaoCancelar;
        botaoCancelar.setOnClickListener(this);

        serv = (GetServicoResponse2)getIntent().getSerializableExtra("serv");


        if(serv != null) {
            campoNomeServico.setText(serv.getDescServico());
            campoDescServico.setText(serv.getNomeServico());
            campoPrecoServico.setText(String.valueOf(serv.getPrecoServico()));
            this.alterando = true;

        }else
        {
            this.alterando = false;
        }
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

            case R.id.fluxo_caixa:
                Intent intent6 = new Intent(this, ServicoLista.class);
                startActivity(intent6);
                return true;

        }

        return false;//
    }

    @Override
    public void onClick(View v) {

        if (v == botaoCadastro) {

            nomeServico = campoNomeServico.getText().toString();
            descServico = campoDescServico.getText().toString();
            precoServico = Double.valueOf(campoPrecoServico.getText().toString());




            ServicoModel servicoModel = new ServicoModel();
            int usuario = 1;


            servicoModel.setNomeServico(nomeServico);
            servicoModel.setDescServico(descServico);
            servicoModel.setPrecoServico(precoServico);


            if (alterando) {

                servicoModel.setIdServico(serv.idServico);
                Toast.makeText(getApplicationContext(), servicoModel.getNomeServico() + descServico + precoServico , Toast.LENGTH_SHORT).show();

                service.AltServico(servicoModel).enqueue(new Callback<CadServicoResponse>() {
                    @Override
                    public void onResponse(Call<CadServicoResponse> call, Response<CadServicoResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                        } else {
                            mensagem = "Falha no cadastro" + response.body().getMessage();
                        }
                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<CadServicoResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

            } else {
                if (nomeServico.isEmpty() || descServico.isEmpty()) {
                    Toast.makeText(CadastroServico.this, "Complete todos os campos", Toast.LENGTH_LONG).show();
                } else {




                    Toast.makeText(getApplicationContext(), servicoModel.getNomeServico() + descServico + precoServico , Toast.LENGTH_SHORT).show();

                    service.CadServico(servicoModel).enqueue(new Callback<CadServicoResponse>() {
                        @Override
                        public void onResponse(Call<CadServicoResponse> call, Response<CadServicoResponse> response) {
                            String mensagem;
                            if (response.body().isSuccess()) {
                                mensagem = "Cadastro efetuado com sucesso";
                            } else {
                                mensagem = "Falha no cadastro" + response.body().getMessage();
                            }
                            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<CadServicoResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }

                    });
                }


            }
        }
        }
    }