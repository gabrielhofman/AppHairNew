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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.LiqRecebModel;
import com.example.apphairnew.response.LiqCtsRecebResponse;
import com.example.apphairnew.ui.cliente.LoginCliente;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiqContasReceb extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private EditText campoVencimento;
    private EditText campoValor;
    private EditText campoObservacao;

    private Button botaoCadastroLiqRec;
    private Button botaoCancelaLiqRec;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private LiqContasReceb liqContasReceb;
    private LiqRecebModel liqRecebModel;

    private String liqRecebVencimento, liqRecebObservacao;
    private Float liqRecebValor;
    private ApiService service = ApiControler.CreateController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liq_contas_receb);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Liquidar contas a receber");

        campoVencimento = (EditText) findViewById(R.id.campoDataVencimento);
        campoValor = (EditText) findViewById(R.id.campoValores);
        campoObservacao = (EditText) findViewById(R.id.campoObservacao);

        Button botaoCadastroReceb = (Button)findViewById(R.id.botaoCadastrarReceb);
        this.botaoCadastroLiqRec = botaoCadastroLiqRec;
        botaoCadastroLiqRec.setOnClickListener(this);

        Button botaoCancelaLiqRec = (Button)findViewById(R.id.botaoCancelarReceb);
        this.botaoCancelaLiqRec = botaoCancelaLiqRec;
        botaoCancelaLiqRec.setOnClickListener(this);

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
        return false;
    }

    @Override
    public void onClick(View v) {

        if(v==botaoCadastroLiqRec){
            liqRecebVencimento = campoVencimento.getText().toString();
            liqRecebValor = Float.valueOf(campoValor.getText().toString());
            liqRecebObservacao = campoObservacao.getText().toString();

            if(liqRecebVencimento.isEmpty() || liqRecebObservacao.isEmpty()){
                Toast.makeText(LiqContasReceb.this, "Complete todos os campos corretamente", Toast.LENGTH_LONG).show();
            } else {

                LiqRecebModel liqRecebModel = new LiqRecebModel();

                liqRecebModel.setLiqRecebVencimento(liqRecebVencimento);
                liqRecebModel.setLiqRecebValor(liqRecebValor);
                liqRecebModel.setLiqRecebObservacao(liqRecebObservacao);

                service.LiqCtsReceb(liqRecebModel).enqueue(new Callback<LiqCtsRecebResponse>() {
                    @Override
                    public void onResponse(Call<LiqCtsRecebResponse> call, Response<LiqCtsRecebResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                        } else {
                            mensagem = "Falha no cadastro:   " + response.body().getMessage();
                        }

                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<LiqCtsRecebResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

            }

        }

    }
}



