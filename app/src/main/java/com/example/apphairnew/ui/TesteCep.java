package com.example.apphairnew.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.response.CepResponse;
import com.example.apphairnew.web.ApiControler;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesteCep extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {



    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;


    private DatePickerDialog.OnDateSetListener mDateSetListener;



    private EditText campoTeste1;
    private EditText campoTeste2;
    private String CEP;
 //   private CepResponse cepResponse;


    private Toolbar toolbar;


    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private String cepEnviar;

    private ApiService service = ApiControler.CreatecontrollerCep();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_cep);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);


        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("CEP");
     //   editTextCpf.addTextChangedListener(MaskEditUtil.mask(editTextCpf, MaskEditUtil.FORMAT_CPF));



        campoTeste1 = (EditText) findViewById(R.id.campoTeste1);
        campoTeste2 = (EditText) findViewById(R.id.campoTeste2);



       // campoTeste1.addTextChangedListener(MaskEditUtil.mask(campoTeste1, MaskEditUtil.FORMAT_CPF));

        Button botaoBuscar = (Button) findViewById(R.id.botaoTeste);
        botaoBuscar.setOnClickListener(this);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(TesteCep.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month = month + 1;
                Toast.makeText(getApplicationContext(),"data" + dayOfMonth + month + year,Toast.LENGTH_SHORT).show();
            }
        };

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

    @Override
    public void onClick(View v) {

    cepEnviar = campoTeste1.getText().toString();



    service.getCEP(cepEnviar).enqueue(new Callback<CepResponse>() {
        @Override
        public void onResponse(Call<CepResponse> call, Response<CepResponse> response) {

            String teste;
           teste =  response.body().getLocalidade();

            Toast.makeText(getApplicationContext(),teste,Toast.LENGTH_SHORT).show();

            campoTeste2.setText(teste);
        }

        @Override
        public void onFailure(Call<CepResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(),"Houve um erro:"+t.getMessage(),Toast.LENGTH_SHORT).show();
            t.printStackTrace();

        }
    });

    }
}
