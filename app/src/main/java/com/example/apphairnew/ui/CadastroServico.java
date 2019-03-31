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

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.model.ServicoModel;
import com.example.apphairnew.web.ApiControler;

public class CadastroServico extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeServico;
    private EditText campoDescServico;
    private EditText campoPrecoServico;
    private Spinner spinnerTempoServico;


    private Button botaoCadastro;
    private Button botaoCancelar;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ServicoModel servicoModel;

    private String nomeServico, descServico;
    private String tempoServico;
    private float precoServico;

    private ApiService service = ApiControler.CreateController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_servico);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar serviço");

        campoNomeServico = (EditText) findViewById(R.id.campoNomeServico);
        campoDescServico = (EditText) findViewById(R.id.campoDescServico);
        campoPrecoServico = (EditText) findViewById(R.id.campoPrecoServico);
        spinnerTempoServico = (Spinner) findViewById(R.id.spinnerTempoServico);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tempoServicoItens, R.layout.support_simple_spinner_dropdown_item);
        spinnerTempoServico.setAdapter(adapter);

        Button botaoCadastro = (Button)findViewById(R.id.botaoCadastrarServico);
        this.botaoCadastro = botaoCadastro;
        botaoCadastro.setOnClickListener(this);

        Button botaoCancelar = (Button)findViewById(R.id.botaoCancelarServico);
        this.botaoCancelar = botaoCancelar;
        botaoCancelar.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {

        if (v==botaoCadastro){
            nomeServico = campoNomeServico.getText().toString();
            descServico = campoDescServico.getText().toString();
            precoServico = Float.parseFloat(campoPrecoServico.getText().toString());
            tempoServico = spinnerTempoServico.getSelectedItem().toString();

            if (nomeServico.isEmpty() || descServico.isEmpty() || tempoServico.isEmpty()){

            }

        }

    }
}
