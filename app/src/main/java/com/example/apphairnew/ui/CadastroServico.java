package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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
import com.example.apphairnew.Util.MoneyTextWatcher;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.response.CadServicoResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.ui.cliente.LoginCliente;
import com.example.apphairnew.web.ApiControler;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

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




        Locale mLocale = new Locale("pt","BR");
        campoPrecoServico.addTextChangedListener(new MoneyTextWatcher(campoPrecoServico, mLocale));


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
            Double valor = serv.precoServico;


            NumberFormat formatado = NumberFormat.getInstance();
            formatado.setMinimumFractionDigits(2);

            campoPrecoServico.setText(String.valueOf(formatado.format(serv.getPrecoServico())));

            botaoCadastro.setText("Alterar Serviço");
            this.alterando = true;

        }else
        {
            botaoCancelar.setVisibility(View.GONE);
            this.alterando = false;
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

        if(v==botaoCancelar)
        {

            service.ExcluirServico(serv.idServico).enqueue(new Callback<CadServicoResponse>() {
                @Override
                public void onResponse(Call<CadServicoResponse> call, Response<CadServicoResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Exclusão realizada com sucesso";
                        Intent intent = new Intent(getApplicationContext(), ServicoLista.class);
                        startActivity(intent);
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

        if (v == botaoCadastro) {

            nomeServico = campoNomeServico.getText().toString();
            descServico = campoDescServico.getText().toString();

            precoServico = Double.valueOf(campoPrecoServico.getText().toString().replace("R$","").replace(".","").replace(",","."));




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
                            mensagem = "Alteração realizada com sucesso";
                            Intent intent = new Intent(getApplicationContext(), ServicoLista.class);
                            startActivity(intent);


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
                                Intent intent = new Intent(getApplicationContext(), ServicoLista.class);
                                startActivity(intent);
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