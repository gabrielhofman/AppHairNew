package com.example.apphairnew.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.FluxoModel;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Login login;
    private EditText campoEmail;
    private EditText campoSenha;
    private String loginUsuario;
    private String senhaUsuario;
    private String ReturnResult;
    private Toolbar toolbar;


    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private String email, senha;

    private ApiService service = ApiControler.CreateController();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);


        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        //actionBar.setTitle("Login");



        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoSenha = (EditText) findViewById(R.id.campoSenha);

        Button botaoLogin = (Button) findViewById(R.id.botaoLogin);
        botaoLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(Login.this, "Digite e-mail e senha", Toast.LENGTH_LONG).show();
        } else {

            LoginModel loginModel = new LoginModel();
            loginModel.setLogin(email);
            loginModel.setSenha(senha);

            service.Login(loginModel).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                    String mensagem;
                    if(response.body().isSuccess()){
                        mensagem = "Login efetuado com sucesso!";
                    }else{
                        mensagem = "Usuário ou senha inválidos, tente novamente!";
                    }

                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        //drawerLayout.closeDrawers();

        switch (item.getItemId()) {
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
                Intent intent9 = new Intent(this, CadastroUsuario.class);
                startActivity(intent9);
                return true;


        }
        return false;
    }





    }


