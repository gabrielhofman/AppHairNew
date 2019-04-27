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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.CtsPagarModel;
import com.example.apphairnew.response.AddCtsPagarResponse;
import com.example.apphairnew.response.GetCtsPagarResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContasPagar extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private EditText campoVencimento;
    private EditText campoValor;
    private EditText campoNomeContato;
    private EditText campoObservacao;

    private Button botaoCadastroPagar;
    private Button botaoCancelarPagar;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CtsPagarModel ctsPagarModel;

    private String pagarVencimento, pagarNomeContato, pagarObservacao;
    private Double pagarValor;
    private ApiService service = ApiControler.CreateController();


    private boolean alterando;


    private GetCtsPagarResponse resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contas_pagar);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Adicionar contas a pagar");

        campoVencimento = (EditText) findViewById(R.id.campoDataVencimento);
        campoValor = (EditText) findViewById(R.id.campoValores);
        campoNomeContato = (EditText) findViewById(R.id.campoNomeContato);
        campoObservacao = (EditText) findViewById(R.id.campoObservacao);

        Button botaoCadastroPagar = (Button)findViewById(R.id.botaoCadastrarPag);
        this.botaoCadastroPagar = botaoCadastroPagar;
        botaoCadastroPagar.setOnClickListener(this);

        Button botaoCancelarPagar = (Button)findViewById(R.id.botaoCancelarPag);
        this.botaoCancelarPagar = botaoCancelarPagar;
        botaoCancelarPagar.setOnClickListener(this);

        resp = (GetCtsPagarResponse)getIntent().getSerializableExtra("ctsPagar");


        if(resp != null){

            campoVencimento.setText(resp.pagarVencimento);
            campoNomeContato.setText(resp.pagarNomeContato);
          //  campoValor.setText(String.valueOf(campoValor));
            campoValor.setText(resp.pagarValor.toString());
            campoObservacao.setText(resp.pagarObservacao);
            this.alterando = true;

        }else
        {
            this.alterando =false;
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
        return false;
    }

    @Override
    public void onClick(View v) {



        if (v==botaoCadastroPagar){
            pagarVencimento = campoVencimento.getText().toString();
            pagarValor = Double.valueOf(campoValor.getText().toString());
            pagarNomeContato = campoNomeContato.getText().toString();
            pagarObservacao = campoObservacao.getText().toString();

                      if(pagarVencimento.isEmpty() || pagarNomeContato.isEmpty() || pagarObservacao.isEmpty()){
                Toast.makeText(AddContasPagar.this, "Complete todos os campos corretamente", Toast.LENGTH_LONG).show();
            }
            CtsPagarModel  ctsPagarModel = new CtsPagarModel();

            ctsPagarModel.setPagarVencimento(pagarVencimento);
            ctsPagarModel.setPagarValor(pagarValor);
            ctsPagarModel.setPagarNomeContato(pagarNomeContato);
            ctsPagarModel.setPagarObservacao(pagarObservacao);


            if(alterando){
                ctsPagarModel.setIdCp(resp.idCp);

                service.AltCP(ctsPagarModel).enqueue(new Callback<AddCtsPagarResponse>() {
                    @Override
                    public void onResponse(Call<AddCtsPagarResponse> call, Response<AddCtsPagarResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                            Intent intent = new Intent(getApplicationContext(), CtsPagarLista.class);
                            startActivity(intent);
                        } else {
                            mensagem = "Falha no cadastro:   " + response.body().getMessage();

                        }

                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddCtsPagarResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });
            }

            else {



                service.AddCtsPagar(ctsPagarModel).enqueue(new Callback<AddCtsPagarResponse>() {
                    @Override
                    public void onResponse(Call<AddCtsPagarResponse> call, Response<AddCtsPagarResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                            Intent intent = new Intent(getApplicationContext(), CtsPagarLista.class);
                            startActivity(intent);
                        } else {
                            mensagem = "Falha no cadastro:   " + response.body().getMessage();
                        }

                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<AddCtsPagarResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

            }

        }

    }
}
