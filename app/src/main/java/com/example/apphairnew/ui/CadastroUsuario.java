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
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.Usuario;
import com.example.apphairnew.web.ApiControler;

public class CadastroUsuario extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoNomeEstab;
    private EditText campoDescEstab;
    private EditText campoCEP;
    private EditText campoBairro;
    private EditText campoRua;
    private EditText campoNumero;
    private EditText campoComplemento;



    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Usuario usuario;

    private String email, senha, nomeEstab, descEstab, cep, bairro, rua, numero, complemento;

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
        rua = campoRua.getText().toString();
        numero = campoNumero.getText().toString();
        complemento = campoComplemento.getText().toString();

        if (email.isEmpty() || senha.isEmpty() || nomeEstab.isEmpty() || descEstab.isEmpty() || cep.isEmpty() || bairro.isEmpty() || rua.isEmpty() || numero.isEmpty() || complemento.isEmpty()){
            Toast.makeText(CadastroUsuario.this, "Complete todos os campos", Toast.LENGTH_LONG).show();
        }else{

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
