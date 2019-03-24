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
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.EnderecoModel;
import com.example.apphairnew.model.EstabModel;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.UsuarioModel;
import com.example.apphairnew.response.CadEnderecoResponse;
import com.example.apphairnew.response.CadEstabResponse;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroUsuario extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoNomeEstab;
    private EditText campoDescEstab;
    private EditText campoCEP;
    private EditText campoBairro;
    private EditText campoLogradouro;
    private EditText campoNumero;
    private EditText campoComplemento;



    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private UsuarioModel usuarioModel;
    private ProfModel profModel;
    private EstabModel estabModel;
    private EnderecoModel enderecoModel;


    private String email, senha, nomeEstab, descEstab, cep, bairro, logradouro, numero, complemento;

    private ApiService service = ApiControler.CreateController();


//teste


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar usuário");





    }

    @Override
    public void onClick(View v) {
        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();
        nomeEstab = campoNomeEstab.getText().toString();
        descEstab = campoDescEstab.getText().toString();
        cep = campoCEP.getText().toString();
        bairro = campoBairro.getText().toString();
        logradouro = campoLogradouro.getText().toString();
        numero = campoNumero.getText().toString();
        complemento = campoComplemento.getText().toString();

        if (email.isEmpty() || senha.isEmpty() || nomeEstab.isEmpty() || descEstab.isEmpty() || cep.isEmpty() || bairro.isEmpty() || logradouro.isEmpty() || numero.isEmpty() || complemento.isEmpty()){
            Toast.makeText(CadastroUsuario.this, "Complete todos os campos", Toast.LENGTH_LONG).show();
        }else{

            ProfModel profModel = new ProfModel();
            profModel.setEmail(email);
            profModel.setSenha(senha);

            service.CadProf(profModel).enqueue(new Callback<CadProfResponse>() {
                @Override
                public void onResponse(Call<CadProfResponse> call, Response<CadProfResponse> response) {
                    String mensagem;
                    if(response.body().isSuccess()){
                      mensagem = "Cadastro de profissional efetuado com sucesso";
                    }else{
                        mensagem = "Falha no cadastro de estabelecimento, tente novamente!";
                    }
                }

                @Override
                public void onFailure(Call<CadProfResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

            //Cadastro de estabelecimento

            EstabModel estabModel = new EstabModel();
            estabModel.setNomeEstab(nomeEstab);
            estabModel.setDescEstab(descEstab);

            service.CadEstab(estabModel).enqueue(new Callback<CadEstabResponse>() {
                @Override
                public void onResponse(Call<CadEstabResponse> call, Response<CadEstabResponse> response) {
                    String mensagem;
                    if(response.body().isSuccess()){
                        mensagem = "Cadastro de estabelecimento efetuado com sucesso";
                    }else{
                        mensagem = "Falha no cadastro de estabelecimento, tente novamente!";
                    }
                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<CadEstabResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

            //Parte de endereço

            EnderecoModel enderecoModel = new EnderecoModel();
            enderecoModel.setCEP(cep);
            enderecoModel.setBairro(bairro);
            enderecoModel.setLogradouro(logradouro);
            enderecoModel.setNumero(numero);
            enderecoModel.setComplemento(complemento);

            service.CadEndereco(enderecoModel).enqueue(new Callback<CadEnderecoResponse>() {
                @Override
                public void onResponse(Call<CadEnderecoResponse> call, Response<CadEnderecoResponse> response) {
                    String mensagem;
                    if(response.body().isSuccess()){
                        mensagem = "Cadastro de endereço efetuado com sucesso";
                    }else{
                        mensagem = "Falha no cadastro de endereço, tente novamente";
                    }

                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<CadEnderecoResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

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

            case R.id.cadastrar_usuario:
                Intent intent1 = new Intent(this, CadastroUsuario.class);
                startActivity(intent1);
                return true;

            case R.id.novo_contato:
                Intent intent2 = new Intent(this, NovoContato.class);
                startActivity(intent2);
                return true;
        }

        return false;
    }
}
