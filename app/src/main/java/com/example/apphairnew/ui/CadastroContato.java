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
import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroContato extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeContato;
    private EditText campoTelContato;
    private EditText campoDataNascContado;
      private Spinner spinnerSexoContato;
    private Spinner spinnerExpecFreqContato;

    private Button botaoTirarFoto;
    private Button botaoCarregarFoto;
    private Button botaoCadastroContato;
    private Button botaoCancelarContato;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ContatoModel contatoModel;

    private String nomeContato, telContato, nascContato,  sexoContato, freqContato;

    private ApiService service = ApiControler.CreateController();

    private GetContatoResponse contato;
    private boolean alterando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar contato");

        campoNomeContato = (EditText) findViewById(R.id.campoNomeContato);
        campoTelContato = (EditText) findViewById(R.id.campoTelefoneContato);


        campoDataNascContado = (EditText)findViewById(R.id.campoDataNascContato);


        spinnerSexoContato = (Spinner) findViewById(R.id.spinnerSexoContato);
        spinnerExpecFreqContato = (Spinner) findViewById(R.id.spinnerExpecFreqContato);

        ArrayAdapter<CharSequence> adapterSexo = ArrayAdapter.createFromResource(this,
                R.array.sexoContato, R.layout.support_simple_spinner_dropdown_item);
        spinnerSexoContato.setAdapter(adapterSexo);

        ArrayAdapter<CharSequence> adapterFreq = ArrayAdapter.createFromResource(this,
                R.array.freqContato, R.layout.support_simple_spinner_dropdown_item);
        spinnerExpecFreqContato.setAdapter(adapterFreq);

        Button botaoTirarFoto = (Button)findViewById(R.id.botaoTirarFoto);
        this.botaoTirarFoto = botaoTirarFoto;
        botaoTirarFoto.setOnClickListener(this);

        Button botaoCarregarFoto = (Button)findViewById(R.id.botaoCarregarFoto);
        this.botaoCarregarFoto = botaoCarregarFoto;
        botaoCarregarFoto.setOnClickListener(this);

        Button botaoCadastroContato = (Button)findViewById(R.id.botaoCadastrarContato);
        this.botaoCadastroContato = botaoCadastroContato;
        botaoCadastroContato.setOnClickListener(this);

        Button botaoCancelarContato = (Button)findViewById(R.id.botaoCancelarContato);
        this.botaoCancelarContato = botaoCancelarContato;
        botaoCancelarContato.setOnClickListener(this);


        //serv = (GetServicoResponse2)getIntent().getSerializableExtra("serv");
        contato = (GetContatoResponse)getIntent().getSerializableExtra("contato");

        if(contato != null)
        {
            campoNomeContato.setText(contato.getNomeContato());
            campoTelContato.setText(contato.getTelContato());
            campoDataNascContado.setText(contato.getDataNascCont());
            this.alterando = true;



        }else{
            this.alterando = false;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();

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

        return false;//
    }

    @Override
    public void onClick(View v) {




        if (v==botaoCadastroContato){

            nomeContato = campoNomeContato.getText().toString();
            telContato = campoTelContato.getText().toString();
            nascContato = campoDataNascContado.getText().toString();
             sexoContato = spinnerSexoContato.getSelectedItem().toString();
            freqContato = spinnerExpecFreqContato.getSelectedItem().toString();

            if(nomeContato.isEmpty() || telContato.isEmpty() || nascContato.isEmpty() || sexoContato.isEmpty() || freqContato.isEmpty()){
                Toast.makeText(CadastroContato.this, "Complete todos os campos", Toast.LENGTH_LONG).show();

            }else {
                ContatoModel contatoModel = new ContatoModel();

                contatoModel.setNomeContato(nomeContato);
                contatoModel.setTelContato(telContato);
                contatoModel.setDataNascCont(nascContato);
                contatoModel.setSexoContato(sexoContato);
                contatoModel.setExpFreqContato(freqContato);



                if(alterando){

                    contatoModel.setId(this.contato.getId());
                    //service.AltContato(ContatoModel).enqueue(Call<CadContatoResponse>);
                    service.AltContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
                        @Override
                        public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                            String mensagem;
                            if (response.body().isSuccess()){
                                mensagem = "Alteração concluida com sucesso";
                                Intent intent = new Intent(getApplicationContext(), ContatoLista.class);
                                startActivity(intent);



                            }else{
                                mensagem = "Falha no cadastro"+ response.body().getMessage();
                            }
                            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<CadContatoResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });



                }else{

                service.CadContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
                    @Override
                    public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                        String mensagem;
                        if (response.body().isSuccess()){
                            mensagem = "Cadastro efetuado com sucesso";
                            Intent intent = new Intent(getApplicationContext(), ContatoLista.class);
                            startActivity(intent);
                        }else{
                            mensagem = "Falha no cadastro"+ response.body().getMessage();
                        }
                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<CadContatoResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

            }









        }
        }

    }
}
