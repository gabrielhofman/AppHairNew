package com.example.apphairnew.ui.cliente;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.Util.Validacao;
import com.example.apphairnew.model.ClienteModel;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.LoginResponse;
import com.example.apphairnew.ui.AddContasPagar;
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

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroCliente extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private EditText campoNome;
    private EditText campoSobrenome;
    private EditText campoEmail;
    private EditText campoSenha;
    private EditText campoTelefone;
    private EditText campoNascimento;
    private RadioGroup rbSexoContato;
    private RadioButton radioBtnSexo;
    private ImageView calendario;
    private Button botaoCadastrar;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    private boolean alterando;


    private ClienteModel clienteModel;

    private Toolbar toolbar;


    private ActionBar actionBar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    private ApiService service = ApiControler.CreateController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);


        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        //actionBar.setTitle("Login");

        //    private EditText campoNome;
        //    private EditText campoSobrenome;
        //    private EditText campoEmail;
        //    private EditText campoSenha;
        //    private EditText campoTelefone;
        //    private EditText campoNascimento;

        campoNome = (EditText) findViewById(R.id.campoNomeCliente);
        campoSobrenome = (EditText) findViewById(R.id.campoSobreNomeCliente);
        campoEmail = (EditText) findViewById(R.id.campoEmailCliente);
        campoSenha = (EditText) findViewById(R.id.campoSenhaCliente);
        campoTelefone = (EditText) findViewById(R.id.campoTelefoneCliente);
        campoTelefone.addTextChangedListener(MaskEditUtil.mask(campoTelefone, MaskEditUtil.FORMAT_FONE));
        campoNascimento = (EditText) findViewById(R.id.campoDataNascCliente);
        //private RadioGroup rbFreqContato;
        rbSexoContato = (RadioGroup) findViewById(R.id.rbSexoContato);
        this.calendario = (ImageView) findViewById(R.id.calendario);
        calendario.setOnClickListener(this);
        this.botaoCadastrar = (Button) findViewById(R.id.botaoCadastrar);
        botaoCadastrar.setOnClickListener(this);


        calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(CadastroCliente.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Toast.makeText(getApplicationContext(), "data" + dayOfMonth + month + year, Toast.LENGTH_SHORT).show();

                String dia;
                String mes;
                String ano;

                if (dayOfMonth < 10) {
                    dia = "0" + dayOfMonth;
                } else {
                    dia = String.valueOf(dayOfMonth);
                }

                if (month < 10) {
                    mes = "0" + month;
                } else {
                    mes = String.valueOf(month);
                }


                String data = (dia + "/" + mes + "/" + year);

                campoNascimento.setText(data);

            }
        };

        service.getCliente(LoginResponse.getIdCliente()).enqueue(new Callback<ClienteModel>() {
            @Override
            public void onResponse(Call<ClienteModel> call, Response<ClienteModel> response) {
                clienteModel = new ClienteModel();
                clienteModel = response.body();
                GerarTela();
            }

            @Override
            public void onFailure(Call<ClienteModel> call, Throwable t) {

            }
        });

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
                Intent intent3 = new Intent(this,ProfListaCliente.class);
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


    public void GerarTela() {
        if (clienteModel.getIdCliente() != 0) {
            campoNome.setText(clienteModel.getNomeCliente());
            campoSobrenome.setText(clienteModel.getSobrenomeCliente());
            campoEmail.setText(clienteModel.getEmailCliente());
            campoTelefone.setText(clienteModel.getTelefCliente());
            campoNascimento.setText(clienteModel.getDataNascCliente());


            this.alterando = true;

        }else {

            this.alterando = false;
        }
    }





    @Override
    public void onClick(View v) {


        if(v==botaoCadastrar)
        {



            //   campoNome = (EditText)findViewById(R.id.campoNomeCliente);
            //        campoSobrenome = (EditText)findViewById(R.id.campoSobreNomeCliente);
            //        campoEmail = (EditText)findViewById(R.id.campoEmailCliente);
            //        campoSenha = (EditText)findViewById(R.id.campoSenhaCliente);
            //        campoTelefone = (EditText)findViewById(R.id.campoTelefoneCliente);
            //        campoNascimento = (EditText)findViewById(R.id.campoDataNascCliente);
            //        //private RadioGroup rbFreqContato;
            //        rbSexoContato = (RadioGroup)findViewById(R.id.rbSexoContato);

            clienteModel = new ClienteModel();

            clienteModel.setNomeCliente(campoNome.getText().toString());
            clienteModel.setSobrenomeCliente(campoSobrenome.getText().toString());
            clienteModel.setEmailCliente(campoEmail.getText().toString());

            boolean validaEmail=false;

              Validacao validacao = new Validacao();

                       if(!validacao.isValidEmail(clienteModel.getEmailCliente()))
                        {
                            campoEmail.setError("Email inválido");
                             validaEmail = true;  // sdasdasdasd
                       }

            clienteModel.setTelefCliente(campoTelefone.getText().toString());
            clienteModel.setDataNascCliente(campoNascimento.getText().toString());
            clienteModel.setSenhaCliente(campoSenha.getText().toString());


            int idSexo = rbSexoContato.getCheckedRadioButtonId();
            radioBtnSexo = (RadioButton) findViewById(idSexo);
            String escolhaSexo = (String) radioBtnSexo.getText();
            clienteModel.setSexoCliente(escolhaSexo);


            service.addCliente(clienteModel).enqueue(new Callback<CadContatoResponse>() {
                @Override
                public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                    String mensagem;
                    if (response.body().isSuccess()){
                        mensagem = "Cadastro efetuado com sucesso";
                        Intent intent = new Intent(getApplicationContext(), ContatoLista.class);
                        startActivity(intent);
                    }else{
                        mensagem = "Falha no cadastro" + response.body().getMessage();
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
