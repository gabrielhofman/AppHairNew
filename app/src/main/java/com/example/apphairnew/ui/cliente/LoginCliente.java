package com.example.apphairnew.ui.cliente;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.LoginModel;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.Agenda;
import com.example.apphairnew.ui.CadastroContato;
import com.example.apphairnew.ui.CadastroServico;
import com.example.apphairnew.ui.CadastroUsuario;
import com.example.apphairnew.ui.ContatoLista;
import com.example.apphairnew.ui.CtsPagarLista;
import com.example.apphairnew.ui.CtsReceberLista;
import com.example.apphairnew.ui.DashBoard;
import com.example.apphairnew.ui.Login;
import com.example.apphairnew.ui.ServicoLista;
import com.example.apphairnew.web.ApiControler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Login login;
    private EditText campoEmail;
    private EditText campoSenha;
    private Button botaoLogin;
    private TextView criarConta;
    private String loginUsuario;
    private String senhaUsuario;
    private String ReturnResult;
    private Toolbar toolbar;


    private ActionBar actionBar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;



    private String email, senha;

    private ApiService service = ApiControler.CreateController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

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
        criarConta = (TextView)findViewById(R.id.labelCriarConta);
        criarConta.setOnClickListener(this);

        Button botaoLogin = (Button) findViewById(R.id.botaoLogin);
        this.botaoLogin = botaoLogin;
        botaoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==botaoLogin) {
            email = campoEmail.getText().toString();
            senha = campoSenha.getText().toString();
            if (email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(LoginCliente.this, "Digite e-mail e senha", Toast.LENGTH_LONG).show();
            } else {

                LoginModel loginModel = new LoginModel();
                loginModel.setLogin(email);
                loginModel.setSenha(senha);

                service.LoginCliente(loginModel).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                        String mensagem;
                        if (response.body().isSuccess()) {
                            mensagem = "Login efetuado com sucesso!";
                            //  LoginResponse.setIdProf(response.body().getIdProf());

                            LoginResponse.setIdCliente(response.body().getId());
                            Intent intent = new Intent(getApplicationContext(), HorariosSalaoCliente.class);
                            startActivity(intent);



                        } else {
                            mensagem = "Usuário ou senha inválidos, tente novamente!";
                        }


                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                    }
                });

            }
        }
        if(v==criarConta)
        {
            Intent intent = new Intent(this, CadastroCliente.class);
            startActivity(intent);

        }

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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        //drawerLayout.closeDrawers();

        switch (item.getItemId()) {
            case R.id.login_cliente:
                Intent intent = new Intent(this, LoginCliente.class);
                startActivity(intent);
                return true;

            case R.id.salao:
                Intent intent3 = new Intent(this,DashBoard.class);
                startActivity(intent3);
                return true;

            case R.id.meus_horarios:
                Intent intent1 = new Intent(this, HorariosAgendadosCliente.class);
                startActivity(intent1);
                return true;

            case R.id.perfil:
                Intent intent2 = new Intent(this, CadastroCliente.class);
                startActivity(intent2);
                return true;

            case R.id.profissional:
                Intent intent4 = new Intent(this, Login.class);
                startActivity(intent4);
                return true;





        }
        return false;
    }





}


