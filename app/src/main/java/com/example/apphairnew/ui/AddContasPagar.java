package com.example.apphairnew.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.Util.MoneyTextWatcher;
import com.example.apphairnew.model.CtsPagarModel;
import com.example.apphairnew.model.FluxoModel;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.response.AddCtsPagarResponse;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.AddFluxoResponse;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetCtsPagarResponse;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.web.ApiControler;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContasPagar extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    private EditText campoVencimento;
    private EditText campoValor;
    private TextView campoNomeContato;


    private GetAgendaDetalhe modelDetalhe;
    private GetContatoResponse contatoResponse;


    private Button botaoCadastroPagar;
    private Button botaoCancelarPagar;
    private Button botaoLiquidarPagar;
    private Button botaoNomeContato;
    private ImageView calendario;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CtsPagarModel ctsPagarModel;
    private FluxoModel fluxoModel;

    private String pagarVencimento;
    private int pagarContato;
    private Double pagarValor;
    private ApiService service = ApiControler.CreateController();

    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private boolean alterando;

    private int contato =0, servico =0;


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
      //  campoVencimento.addTextChangedListener(MaskEditUtil.mask(campoVencimento, MaskEditUtil.FORMAT_DATE));

        campoValor = (EditText) findViewById(R.id.campoValores);
        Locale mLocale = new Locale("pt","BR");
        campoValor.addTextChangedListener(new MoneyTextWatcher(campoValor, mLocale));


        campoNomeContato = (TextView) findViewById(R.id.campoNomeContato);


        Button botaoCadastroPagar = (Button)findViewById(R.id.botaoCadastrarPag);
        this.botaoCadastroPagar = botaoCadastroPagar;
        botaoCadastroPagar.setOnClickListener(this);

        Button botaoCancelarPagar = (Button)findViewById(R.id.botaoCancelarPag);
        this.botaoCancelarPagar = botaoCancelarPagar;
        botaoCancelarPagar.setOnClickListener(this);

        Button botaoNomeContato = (Button)findViewById(R.id.botaoNomeContato);
        this.botaoNomeContato = botaoNomeContato;
        botaoNomeContato.setOnClickListener(this);

        Button botaoLiquidarPagar = (Button)findViewById(R.id.botaoLiquidarPagar) ;
        this.botaoLiquidarPagar = botaoLiquidarPagar;
        botaoLiquidarPagar.setOnClickListener(this);

        final ImageView calendario = (ImageView)findViewById(R.id.calendario);
        this.calendario = calendario;
   //     calendario.setOnClickListener(this);



        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(AddContasPagar.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Toast.makeText(getApplicationContext(),"data" + dayOfMonth + month + year,Toast.LENGTH_SHORT).show();

                String dia;
                String mes;
                String ano;

                if(dayOfMonth <10)
                {
                    dia = "0" + dayOfMonth;
                }else
                {
                    dia = String.valueOf(dayOfMonth);
                }

                if(month <10)
                {
                    mes = "0" + month;
                }else
                {
                    mes = String.valueOf(month);
                }




                String data = (dia + "/" + mes + "/" + year);

                campoVencimento.setText(data);

            }
        };



        resp = (GetCtsPagarResponse)getIntent().getSerializableExtra("ctsPagar");


        if(resp == null)
        {
            botaoLiquidarPagar.setVisibility(View.GONE);
            botaoCancelarPagar.setVisibility(View.GONE);
        }


        if(resp != null){

            botaoCadastroPagar.setText("Alterar Contas à Pagar"); //l
            campoVencimento.setText(resp.pagarVencimento);

          //  campoValor.setText(String.valueOf(campoValor));

            NumberFormat formatado = NumberFormat.getInstance();
            formatado.setMinimumFractionDigits(2);
            campoValor.setText(formatado.format(resp.pagarValor));

            this.modelDetalhe = new GetAgendaDetalhe();
            this.modelDetalhe.setContato(resp.getPagarContato());
            this.modelDetalhe.setServico(1);

            this.alterando = true;

            service.GetDetalhe(modelDetalhe).enqueue(new Callback<GetDetalheAgendaResponse>() {
                @Override
                public void onResponse(Call<GetDetalheAgendaResponse> call, Response<GetDetalheAgendaResponse> response) {
                    Toast.makeText(AddContasPagar.this, "Contato: " + response.body().contato, Toast.LENGTH_LONG).show();
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

        if(v==botaoLiquidarPagar)
        {
            fluxoModel = new FluxoModel();
            fluxoModel.setMovFluxo("S");
            fluxoModel.setUsuarioFluxo(1);
            fluxoModel.setDataFluxo(campoVencimento.getText().toString());
            fluxoModel.setValorFluxo(Double.valueOf(campoValor.getText().toString().replace("R$","").replace(".","").replace(",",".").replace(" ","")));

            service.CadFluxo(fluxoModel).enqueue(new Callback<AddFluxoResponse>() {
                @Override
                public void onResponse(Call<AddFluxoResponse> call, Response<AddFluxoResponse> response) {

                }

                @Override
                public void onFailure(Call<AddFluxoResponse> call, Throwable t) {

                }
            });

            service.ExcluirCP(resp.idCp).enqueue(new Callback<AddCtsReceberResponse>() {
                @Override
                public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Registro pago com sucesso.";
                    } else {
                        mensagem = "Falhou" + response.body().getMessage();
                    }

                    Intent intent = new Intent(getApplicationContext(), CtsPagarLista.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {

                }
            });



        }

        if(v== botaoCancelarPagar)
        {
            service.ExcluirCP(resp.idCp).enqueue(new Callback<AddCtsReceberResponse>() {
                @Override
                public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                    String mensagem;
                                        if (response.body().isSuccess()) {
                                            mensagem = "Sucesso na Exclusão do Contas a Pagar";
                                        } else {
                                            mensagem = "Falhou" + response.body().getMessage();
                                        }

                    Intent intent = new Intent(getApplicationContext(), CtsPagarLista.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AddCtsReceberResponse> call, Throwable t) {

                }
            });

        }



        if (v==botaoCadastroPagar){
            pagarVencimento = campoVencimento.getText().toString();
            pagarValor = Double.valueOf(campoValor.getText().toString().replace("R$","").replace(".","").replace(",",".").replace(" ",""));

            pagarContato = contato;

                      if(pagarVencimento.isEmpty()){
                Toast.makeText(AddContasPagar.this, "Complete todos os campos corretamente", Toast.LENGTH_LONG).show();
            }
            CtsPagarModel  ctsPagarModel = new CtsPagarModel();

            ctsPagarModel.setPagarVencimento(pagarVencimento);
            ctsPagarModel.setPagarValor(pagarValor);
            ctsPagarModel.setPagarContato(pagarContato);


            if(alterando){
                ctsPagarModel.setIdCp(resp.idCp);


                if(contato==0)
                {
                    ctsPagarModel.setPagarContato(resp.pagarContato);

                }else
                {
                    ctsPagarModel.setPagarContato(pagarContato);
                }


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
