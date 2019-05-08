package com.example.apphairnew.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.model.HorarioModel;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.response.GetServicoResponse2;
import com.example.apphairnew.response.HorarioResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalheAgendamento extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{

    private EditText campoNomeContato;
    private EditText campoHoraInicio;
    private EditText campoHoraFim;
    private EditText campoNomeServico;
    private EditText campoPrecoServico;
    private EditText campoDataAgenda;


    private TextView nomeContatoFinal;
    private TextView nomeServicoFinal;




    private LinearLayoutManager linearLayoutManager;
    public List<GetContatoResponse> teste = new ArrayList<>();


    private Button botaoSalvarHorario;
    private Button botaoConcluirHorario;
    private Button botaoCancelarHorario;
    private Button botaoBuscarContato;
    private Button botaoBuscarServico;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private String  horaInicio, horaFim,  dataAgenda;
    private int contato, servico;
    private double precoServico;


    private GetContatoResponse contatoResponse;
    private GetServicoResponse2 servicoResponse2;

    private ApiService service = ApiControler.CreateController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_agendamento);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Detalhes do horário");

        campoNomeContato = (EditText) findViewById(R.id.campoNomeContato);
        campoHoraInicio = (EditText) findViewById(R.id.campoHoraInicio);
        campoHoraFim = (EditText) findViewById(R.id.campoHoraFim);
        campoPrecoServico = (EditText) findViewById(R.id.campoPrecoServico);
        campoDataAgenda = (EditText) findViewById(R.id.campoDataAgenda);

        campoDataAgenda.addTextChangedListener(MaskEditUtil.mask(campoDataAgenda, MaskEditUtil.FORMAT_DATE));
        campoHoraInicio.addTextChangedListener(MaskEditUtil.mask(campoHoraInicio, MaskEditUtil.FORMAT_HOUR));
        campoHoraFim.addTextChangedListener(MaskEditUtil.mask(campoHoraFim, MaskEditUtil.FORMAT_HOUR));


        Button botaoSalvarHorario = (Button)findViewById(R.id.botaoSalvarHorario);
        this.botaoSalvarHorario = botaoSalvarHorario;
        botaoSalvarHorario.setOnClickListener(this);

        Button botaoConcluirHorario = (Button)findViewById(R.id.botaoConcluirHorario);
        this.botaoConcluirHorario = botaoConcluirHorario;
        botaoConcluirHorario.setOnClickListener(this);

        Button botaoCalcelarHorario = (Button)findViewById(R.id.botaoCancelarHorario);
        this.botaoCancelarHorario = botaoCalcelarHorario;
        botaoCalcelarHorario.setOnClickListener(this);

        this.botaoBuscarContato = (Button)findViewById(R.id.botaoBucarContato) ;
        this.botaoBuscarContato.setOnClickListener(this);


        this.botaoBuscarServico = (Button)findViewById(R.id.botaoBuscarServico);
        this.botaoBuscarServico.setOnClickListener(this);






        nomeContatoFinal = (TextView) findViewById(R.id.nome_contato);
        nomeServicoFinal = (TextView) findViewById(R.id.nome_servico);





    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //drawerLayout.closeDrawers();

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



        }
        return false;
    }
    @Override
    public void onClick(View v) {

        if(v== botaoBuscarContato){
            Intent intent = new Intent(this, PesquisaContato.class);
            startActivityForResult(intent, 1);

        }

        if(v== botaoBuscarServico){

            Intent intent = new Intent(this, PesquisaServico.class);
            startActivityForResult(intent, 2);
        }

        if (v==botaoSalvarHorario){
            dataAgenda = campoDataAgenda.getText().toString();
            contato = Integer.parseInt(campoNomeContato.getText().toString());
            horaInicio = campoHoraInicio.getText().toString();
            horaFim = campoHoraFim.getText().toString();
            servico = Integer.parseInt(campoNomeServico.getText().toString());
            precoServico =  Double.valueOf(campoPrecoServico.getText().toString());

            final HorarioModel horarioModel = new HorarioModel();
            horarioModel.setDataAgenda(dataAgenda);
            horarioModel.setContato(contato);
            horarioModel.setDataAgenda(dataAgenda);
            horarioModel.setHoraInicio(horaInicio);
            horarioModel.setHoraFim(horaFim);
            horarioModel.setServico(servico);
            horarioModel.setPrecoServico(precoServico);



            String inicio = horaInicio.replaceAll(":", "");
            String fim = horaFim.replaceAll(":", "");

            int inicioNum = Integer.parseInt(inicio);
            int fimNum = Integer.parseInt(fim);


           if(inicioNum > fimNum){
               Toast.makeText(getApplicationContext(),  "Horario de incial deve ser maior que horario de final da agenda", Toast.LENGTH_SHORT).show();
           }



            service.MarcarHorario(horarioModel).enqueue(new Callback<HorarioResponse>() {
                @Override
                public void onResponse(Call<HorarioResponse> call, Response<HorarioResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Cadastro efetuado com sucesso";
                    } else {
                        mensagem = "Falha no cadastro:   " + response.body().getMessage();
                    }


                }

                @Override
                public void onFailure(Call<HorarioResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });
        }

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK) {

                contatoResponse = (GetContatoResponse) data.getSerializableExtra("contato");

                nomeContatoFinal.setText(contatoResponse.getNomeContato());

            }
                if(resultCode == RESULT_CANCELED)
                {
                    nomeContatoFinal.setText("aehoo2");
                }

            }
         if(requestCode == 2){
             if(resultCode == RESULT_OK) {

                 servicoResponse2 = (GetServicoResponse2) data.getSerializableExtra("servico");

                 nomeServicoFinal.setText(servicoResponse2.getNomeServico());
                // campoPrecoServico.setText(servicoResponse2.getPrecoServico());

             }
             if(resultCode == RESULT_CANCELED)
             {
                 nomeServicoFinal.setText("aehoo2");

             }

         }

         }
        }
