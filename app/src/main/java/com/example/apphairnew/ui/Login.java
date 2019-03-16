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

import com.example.apphairnew.MainActivity;
import com.example.apphairnew.R;

public class Login extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Login login;
    private EditText campoEmail;
    private EditText campoSenha;
    private String loginUsuario;
    private String senhaUsuario;
    private Toolbar toolbar;
    //douglas


    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    // coisas teste


    private String email, senha;

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

        actionBar.setTitle("Teste");


        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoSenha = (EditText) findViewById(R.id.campoSenha);

        Button botaoLogin = (Button)findViewById(R.id.botaoLogin);
        botaoLogin.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(Login.this, "Digite e-mail e senha", Toast.LENGTH_LONG).show();
        }else {
            Intent Myhome = new Intent(Login.this, MainActivity.class);
            Myhome.putExtra("GetEmail", email);
            startActivity(Myhome);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        drawerLayout.closeDrawers();

        switch (item.getItemId()) {
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

