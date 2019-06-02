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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.CtsReceberModel;
import com.example.apphairnew.model.FluxoModel;
import com.example.apphairnew.model.GetAgendaDetalhe;
import com.example.apphairnew.response.AddCtsReceberResponse;
import com.example.apphairnew.response.AddFluxoResponse;
import com.example.apphairnew.response.GetDetalheAgendaResponse;
import com.example.apphairnew.response.GetHorarioResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConcluirServico extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private TextView cliente;
    private TextView serivico;
    private RadioGroup grupoParcela;
    private RadioGroup grupoForma;
    private RadioButton radioBtnForma;
    private RadioButton radioBtnParcela;

    private Button concluir;
    private Button cancelar;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ApiService service = ApiControler.CreateController();

    private FluxoModel fluxoModel;
    private CtsReceberModel receberModel;
    private AddFluxoResponse fluxoResponse;
    private GetHorarioResponse horarioResponse;


    private GetAgendaDetalhe modelDetalhe;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concluir_servico);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Concluir Servico");




        // private TextView cliente;
        //    private TextView serivico;
        //    private RadioButton vista;
        //    private RadioButton prazo;
        //    private Button concluir;
        //    private Button cancelar;

        grupoParcela = (RadioGroup) findViewById(R.id.rbParcelaPagamento);
        grupoForma = (RadioGroup) findViewById(R.id.rbFormaPagamento) ;
        cliente = (TextView) findViewById(R.id.labelNomeContato);
        serivico = (TextView) findViewById(R.id.labelNomeServico);


        concluir = (Button) findViewById(R.id.botaoConcluirHorario);
        concluir.setOnClickListener(this);
        cancelar = (Button) findViewById(R.id.botaoCancelarHorario);
        cancelar.setOnClickListener(this);



        this.horarioResponse = (GetHorarioResponse)getIntent().getSerializableExtra("detalhe");


        this.modelDetalhe = new GetAgendaDetalhe();

        this.modelDetalhe.contato = horarioResponse.getContato();
        this.modelDetalhe.servico = horarioResponse.getServico();

        service.GetDetalhe(modelDetalhe).enqueue(new Callback<GetDetalheAgendaResponse>() {
            @Override
            public void onResponse(Call<GetDetalheAgendaResponse> call, Response<GetDetalheAgendaResponse> response) {

                //    Toast.makeText(getApplicationContext(),  "Contato:  " + response.body().contato, Toast.LENGTH_SHORT).show();


                //nomeContatoFinal.setText(contatoResponse.getNomeContato());
                //
                //nomeServicoFinal.setText(servicoResponse2.getNomeServico());

                cliente.setText(response.body().contato);
                serivico.setText(response.body().servico);



            }

            @Override
            public void onFailure(Call<GetDetalheAgendaResponse> call, Throwable t) {

            }
        });


        grupoForma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioBtnParcela = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = radioBtnParcela.isChecked();

                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "CHUP:" , Toast.LENGTH_SHORT).show();
                    RadioButton radioBtn30ddias = (RadioButton)group.findViewById(R.id.dias_30);
                    radioBtn30ddias.setChecked(true);

                }



            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.login:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;

            case R.id.dashboard:
                Intent intent3 = new Intent(this, DashBoard.class);
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

        if(v == concluir)
        {


            fluxoModel = new FluxoModel();

            fluxoModel.setDataFluxo(horarioResponse.getDataAgenda());
            fluxoModel.setValorFluxo(horarioResponse.getPrecoServico());
            fluxoModel.setUsuarioFluxo(1);
            fluxoModel.setAgendaFluxo(horarioResponse.getIdAgenda());
            fluxoModel.setMovFluxo("E");
            fluxoModel.setIdAgenda(horarioResponse.getIdAgenda());


            receberModel = new CtsReceberModel();
            receberModel.setRecebVencimento(horarioResponse.getDataAgenda());
            receberModel.setRecebValor(horarioResponse.getPrecoServico());
            receberModel.setRecebContato(horarioResponse.contato);
            receberModel.setIdAgenda(horarioResponse.getIdAgenda());



            int idParcela = grupoParcela.getCheckedRadioButtonId();
            radioBtnParcela = (RadioButton) findViewById(idParcela) ;

            int idForma = grupoForma.getCheckedRadioButtonId();
            radioBtnForma = (RadioButton) findViewById(idForma);

            String escolha = (String) radioBtnForma.getText();
            String escolha2 = (String) radioBtnParcela.getText();


            receberModel.setTipoPgto(escolha);
            receberModel.setCondPgto(escolha2);


           // Toast.makeText(getApplicationContext(), escolha + escolha2, Toast.LENGTH_SHORT).show();








            if((escolha.equals( "Carteira")  && escolha2.equals("A vista")) ||escolha.equals( "Cartão Débito") )
            {


                service.CadFluxo(fluxoModel).enqueue(new Callback<AddFluxoResponse>() {
                    @Override
                    public void onResponse(Call<AddFluxoResponse> call, Response<AddFluxoResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                        } else {
                            mensagem = "Falha no cadastro" + response.body().getMessage();
                        }


                        Intent intent = new Intent(getApplicationContext(), Agenda.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<AddFluxoResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();

                    }
                });
            } else

            {

                service.AddCtsRecebAgenda(receberModel).enqueue(new Callback<AddCtsReceberResponse>() {
                    @Override
                    public void onResponse(Call<AddCtsReceberResponse> call, Response<AddCtsReceberResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Cadastro efetuado com sucesso";
                        } else {
                            mensagem = "Falha no cadastro" + response.body().getMessage();
                        }

                        Intent intent = new Intent(getApplicationContext(), Agenda.class);
                        startActivity(intent);
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
