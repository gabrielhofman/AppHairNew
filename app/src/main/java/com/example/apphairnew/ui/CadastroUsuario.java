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
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.Util.Validacao;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.UsuarioModel;
import com.example.apphairnew.response.CadProfResponse;
import com.example.apphairnew.response.CepResponse;
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
    private EditText campoCidade;
    private EditText campoUF;
    private EditText campoBairro;
    private EditText campoLogradouro;
    private EditText campoNumero;
    private EditText campoComplemento;

    private Button botaoCep;
    private Button botaoCadastro;

    private String cepEnviar;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private UsuarioModel usuarioModel;
    private ProfModel profModel;



    private String email, senha, nomeEstab, descEstab, cep, cidade, uf, bairro, logradouro, numero, complemento;

    private ApiService service = ApiControler.CreateController();

    private ApiService serviceCep = ApiControler.CreatecontrollerCep();


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

        //campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoEmail = (EditText) findViewById(R.id.campoEmail);
        campoSenha = (EditText) findViewById(R.id.campoSenha);
        campoNomeEstab = (EditText) findViewById(R.id.campoNomeEstab);
        campoDescEstab = (EditText) findViewById(R.id.campoDescEstab);
        campoCEP = (EditText) findViewById(R.id.campoCEP);
        campoCidade = (EditText) findViewById(R.id.campoCidade);
        campoUF = (EditText) findViewById(R.id.campoUF);
        campoBairro = (EditText) findViewById(R.id.campoBairro);
        campoLogradouro = (EditText) findViewById(R.id.campoRua);
        campoNumero = (EditText) findViewById(R.id.campoNumero);
        campoComplemento = (EditText) findViewById(R.id.campoComplemento);


        campoCEP.addTextChangedListener(MaskEditUtil.mask(campoCEP, MaskEditUtil.FORMAT_CEP));


        Button botaoCadastro = (Button)findViewById(R.id.botaoCadastrar);
        this.botaoCadastro = botaoCadastro;
        botaoCadastro.setOnClickListener(this);

        Button botaoCep = (Button)findViewById(R.id.botaoCEP);
        this.botaoCep = botaoCep;
        botaoCep.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v==botaoCadastro) {
            boolean validaEmail=false;

        email = campoEmail.getText().toString();

            Validacao validacao = new Validacao();

            if(!validacao.isValidEmail(email))
            {
                campoEmail.setError("Email inválido");
                 validaEmail = true;  // sdasdasdasd
            }



            senha = campoSenha.getText().toString();
        nomeEstab = campoNomeEstab.getText().toString();
        descEstab = campoDescEstab.getText().toString();
        cep = campoCEP.getText().toString();
        cidade = campoCidade.getText().toString();
        uf = campoUF.getText().toString();
        bairro = campoBairro.getText().toString();
        logradouro = campoLogradouro.getText().toString();
        numero = campoNumero.getText().toString();
        complemento = campoComplemento.getText().toString();

        if (email.isEmpty() || senha.isEmpty() || nomeEstab.isEmpty() || descEstab.isEmpty() || cep.isEmpty() || cidade.isEmpty() ||  uf.isEmpty() || bairro.isEmpty() || logradouro.isEmpty() || numero.isEmpty() || validaEmail) {
            Toast.makeText(CadastroUsuario.this, "Complete todos os campos corretamente", Toast.LENGTH_LONG).show();
        } else {

            ProfModel profModel = new ProfModel();

            profModel.setEmail(email);
            profModel.setSenha(senha);
            profModel.setNomeEstab(nomeEstab);
            profModel.setDescEstab(descEstab);
            profModel.setCEP(cep);
            profModel.setCidade(cidade);
            profModel.setUf(uf);
            profModel.setBairro(bairro);
            profModel.setLogradouro(logradouro);
            profModel.setNumero(numero);
            profModel.setComplemento(complemento);



            service.CadProf(profModel).enqueue(new Callback<CadProfResponse>() {
                @Override
                public void onResponse(Call<CadProfResponse> call, Response<CadProfResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()) {
                        mensagem = "Cadastro efetuado com sucesso";
                    } else {
                        mensagem = "Falha no cadastro:   " + response.body().getMessage();
                    }

                    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<CadProfResponse> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    t.printStackTrace();

                }
            });

           }
        }

        if(v==botaoCep)
        {
            cepEnviar = campoCEP.getText().toString();

            serviceCep.getCEP(cepEnviar).enqueue(new Callback<CepResponse>() {
                @Override
                public void onResponse(Call<CepResponse> call, Response<CepResponse> response) {
                    String teste;
                    teste = response.body().getBairro();

                    campoUF.setText(response.body().getUf());
                    campoCidade.setText(response.body().getLocalidade());
                    campoBairro.setText(response.body().getBairro());
                    campoLogradouro.setText(response.body().getLogradouro());
                    campoComplemento.setText(response.body().getComplemento());



                    Toast.makeText(getApplicationContext(),teste,Toast.LENGTH_SHORT).show();;
                }

                @Override
                public void onFailure(Call<CepResponse> call, Throwable t) {

                    Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
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

        return false;
    }
}
