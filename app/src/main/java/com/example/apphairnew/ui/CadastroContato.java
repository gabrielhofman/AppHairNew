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
import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroContato extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeContato;
    private EditText campoTelContato;
    private EditText campoDataNascContato;
    private EditText campoObsContato;
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

    private String nomeContato, telContato, nascContato, obsContato, sexoContato, freqContato;

    private ApiService service = ApiControler.CreateController();

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
        campoDataNascContato = (EditText)findViewById(R.id.campoDataNascContato);
        campoObsContato = (EditText)findViewById(R.id.campoObsContato) ;
        spinnerSexoContato = (Spinner)findViewById(R.id.spinnerSexoContato) ;
        spinnerExpecFreqContato = (Spinner)findViewById(R.id.spinnerExpecFreqContato);




        campoTelContato.addTextChangedListener(MaskEditUtil.mask(campoTelContato, MaskEditUtil.FORMAT_FONE));
        campoDataNascContato.addTextChangedListener(MaskEditUtil.mask(campoDataNascContato,MaskEditUtil.FORMAT_DATE));



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



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();

        switch (menuItem.getItemId()){
            case R.id.login:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
                return true;

            case R.id.cadastrar_usuario:
                Intent intent1 = new Intent(this, CadastroUsuario.class);
                startActivity(intent1);
                return true;

            case R.id.novo_contato:
                Intent intent2 = new Intent(this, NovoContato.class);
                startActivity(intent2);
                return true;
        }

        return false;//
    }

    @Override
    public void onClick(View v) {

        if (v==botaoCadastroContato){


            nomeContato = campoNomeContato.getText().toString();
            telContato = campoTelContato.getText().toString();
            nascContato = campoDataNascContato.getText().toString();
            obsContato = campoObsContato.getText().toString();
            sexoContato = spinnerSexoContato.getSelectedItem().toString();
            freqContato = spinnerExpecFreqContato.getSelectedItem().toString();

            if(nomeContato.isEmpty() || telContato.isEmpty() || nascContato.isEmpty() || obsContato.isEmpty() || sexoContato.isEmpty() || freqContato.isEmpty()){
                Toast.makeText(CadastroContato.this, "Complete todos os campos", Toast.LENGTH_LONG).show();

            }else{
                ContatoModel contatoModel = new ContatoModel();
                contatoModel.setNomeContato(nomeContato);
                contatoModel.setTelContato(telContato);
                contatoModel.setDataNascCont(nascContato);
                contatoModel.setSexoContato(sexoContato);
                contatoModel.setExpFreqContato(freqContato);
                contatoModel.setObsContato(obsContato);

                Toast.makeText(getApplicationContext(), sexoContato, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), freqContato, Toast.LENGTH_SHORT).show();

                service.CadContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
                    @Override
                    public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                        String mensagem;
                         if (response.body().isSuccess()){
                         mensagem = "Cadastro efetuado com sucesso";
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
