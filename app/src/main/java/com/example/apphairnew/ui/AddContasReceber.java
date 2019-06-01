package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.Util.MoneyTextWatcher;
import com.example.apphairnew.model.CtsReceberModel;
import com.example.apphairnew.model.FluxoModel;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.AddFluxoResponse;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetCtsReceberResponse;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.web.ApiControler;

import java.text.NumberFormat;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContasReceber extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoVencimento;
    private EditText campoValor;

    private TextView campoNomeContato;

    private Button botaoCadastroReceb;
    private Button botaoExcluirReceb;
    private Button botaoNomeContato;
    private Button botaoLiquidarReceb;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CtsReceberModel ctsReceberModel;
    private FluxoModel fluxoModel;

    private String recebVencimento;
    private int recebContato;
    private String recebObservacao;
    //private Float recebValor;
    private Double recebValor;

    private ApiService service = ApiControler.CreateController();
    private ApiService serviceCep = ApiControler.CreatecontrollerCep();


    private GetAgendaDetalhe modelDetalhe;
    private GetContatoResponse contatoResponse;


    private boolean alterando;

    private GetCtsReceberResponse resp;

    private int contato =0, servico =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contas_receber);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Adicionar contas a receber");

        campoVencimento = (EditText) findViewById(R.id.campoDataVencimento);
        Locale mLocale = new Locale("pt","BR");
        campoValor = (EditText) findViewById(R.id.campoValores);
        campoValor.addTextChangedListener(new MoneyTextWatcher(campoValor, mLocale));
        campoNomeContato = (TextView) findViewById(R.id.campoNomeContato);


        campoVencimento.addTextChangedListener(MaskEditUtil.mask(campoVencimento, MaskEditUtil.FORMAT_DATE));
      //  campoValor.addTextChangedListener(MaskEditUtil.mask(campoValor, MaskEditUtil.FORMAT_VALOR));

        Button botaoCadastroReceb = (Button)findViewById(R.id.botaoCadastrarReceb);
        this.botaoCadastroReceb = botaoCadastroReceb;
        botaoCadastroReceb.setOnClickListener(this);

        Button botaoExcluirReceb = (Button)findViewById(R.id.botaoExcluirReceb);
        this.botaoExcluirReceb = botaoExcluirReceb;
        botaoExcluirReceb.setOnClickListener(this);

        Button botaoNomeContato = (Button)findViewById(R.id.botaoNomeContato);
        this.botaoNomeContato = botaoNomeContato;
        botaoNomeContato.setOnClickListener(this);


        Button botaoLiquidaReceb = (Button)findViewById(R.id.botaoLiquidarReceb);
        this.botaoLiquidarReceb = botaoLiquidaReceb;
        botaoLiquidaReceb.setOnClickListener(this);




        resp = (GetCtsReceberResponse)getIntent().getSerializableExtra("ctsReceb");
        if(resp == null)
        {
            botaoExcluirReceb.setVisibility(View.GONE);
            botaoLiquidaReceb.setVisibility(View.GONE);
        }

        if(resp != null)
        {

            campoVencimento.setText(resp.recebVencimento);
            NumberFormat formatado = NumberFormat.getInstance();
            formatado.setMinimumFractionDigits(2);
            campoValor.setText(formatado.format(resp.recebValor));
            botaoCadastroReceb.setText("Alterar Contas a Receber");




            this.modelDetalhe = new GetAgendaDetalhe();
            this.modelDetalhe.setContato(resp.getRecebContato(resp.recebContato));
            this.modelDetalhe.setServico(1);
            this.alterando=true;




            service.GetDetalhe(modelDetalhe).enqueue(new Callback<GetDetalheAgendaResponse>() {
                @Override
                public void onResponse(Call<GetDetalheAgendaResponse> call, Response<GetDetalheAgendaResponse> response) {

                    //    Toast.makeText(getApplicationContext(),  "Contato:  " + response.body().contato, Toast.LENGTH_SHORT).show();


                    //nomeContatoFinal.setText(contatoResponse.getNomeContato());
                    //
                    //nomeServicoFinal.setText(servicoResponse2.getNomeServico());

                    campoNomeContato.setText(response.body().contato);


                }

                @Override
                public void onFailure(Call<GetDetalheAgendaResponse> call, Throwable t) {

                }
            });

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

        if(v==botaoNomeContato){
            Intent intent = new Intent(this, PesquisaContato.class);
            startActivityForResult(intent, 1);


        }

        if(v== botaoExcluirReceb){
            service.ExcluirCR(resp.idCr).enqueue(new Callback<AddCtsReceberResponse>() {
                @Override
                public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Sucesso na Exclusão do Contas a Receber";
                    } else {
                        mensagem = "Falhou" + response.body().getMessage();
                    }

                    Intent intent = new Intent(getApplicationContext(), CtsReceberLista.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {

                }
            });
        }

        if(v==botaoLiquidarReceb) {
            fluxoModel = new FluxoModel();
            fluxoModel.setMovFluxo("E");
            fluxoModel.setUsuarioFluxo(1);
            fluxoModel.setDataFluxo(campoVencimento.getText().toString());;
            fluxoModel.setValorFluxo(Double.valueOf(campoValor.getText().toString().replace("R$","").replace(".","").replace(",",".")));


            service.CadFluxo(fluxoModel).enqueue(new Callback<AddFluxoResponse>() {
                @Override
                public void onResponse(Call<AddFluxoResponse> call, Response<AddFluxoResponse> response) {

                }

                @Override
                public void onFailure(Call<AddFluxoResponse> call, Throwable t) {

                }
            });

            service.ExcluirCR(resp.idCr).enqueue(new Callback<AddCtsReceberResponse>() {
                @Override
                public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Contas a Receber Liquidado";
                    } else {
                        mensagem = "Falhou" + response.body().getMessage();
                    }

                    Intent intent = new Intent(getApplicationContext(), CtsReceberLista.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {

                }
            });


        }

        if (v==botaoCadastroReceb){

            recebVencimento = campoVencimento.getText().toString();
            recebValor = Double.valueOf(campoValor.getText().toString().replace("R$","").replace(".","").replace(",","."));
            recebContato = contato;


            if(recebVencimento.isEmpty() ){
                Toast.makeText(AddContasReceber.this, "Complete todos os campos corretamente", Toast.LENGTH_LONG).show();
            } else {
                CtsReceberModel ctsReceberModel = new CtsReceberModel();

                ctsReceberModel.setRecebVencimento(recebVencimento);
                ctsReceberModel.setRecebValor(recebValor);
                ctsReceberModel.setRecebContato(recebContato);
                ctsReceberModel.setRecebObservacao(recebObservacao);

                if(alterando)
                {
                    ctsReceberModel.setIdCr(resp.idCr);


                        if(contato==0)
                        {
                            ctsReceberModel.setRecebContato(resp.recebContato);
                        }else {
                            ctsReceberModel.setRecebContato(recebContato);
                        }


                    service.AltCR(ctsReceberModel).enqueue(new Callback<AddCtsReceberResponse>() {
                        @Override
                        public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                            String mensagem;
                            if (response.body().isSuccess()) {
                                mensagem = "Cadastro efetuado com sucesso";
                                Intent intent = new Intent(getApplicationContext(), CtsReceberLista.class);
                                startActivity(intent);
                            } else {
                                mensagem = "Falha no cadastro:   " + response.body().getMessage();

                            }

                            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }

                    });
                }else {




                service.AddCtsReceb(ctsReceberModel).enqueue(new Callback<AddCtsReceberResponse>() {
                    @Override
                    public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                        String mensagem;


                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                            Intent intent = new Intent(getApplicationContext(), CtsReceberLista.class);
                            startActivity(intent);
                        } else {
                            mensagem = "Falha no cadastro:   " + response.body().getMessage();
                        }
                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

                }


            }
            }




        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK) {

                contatoResponse = (GetContatoResponse) data.getSerializableExtra("contato");

                campoNomeContato.setText(contatoResponse.getNomeContato());
               this.contato = contatoResponse.getId();

            }
            if(resultCode == RESULT_CANCELED)
            {
          //      nomeContatoFinal.setText("Não selecionado");
            }

        }


    }

    }

