package com.example.apphairnew.ui;

import android.content.Intent;
import android.os.AsyncTask;
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

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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


    //webserivce
//    public static String URL="http://localhost:63048/WebService1.asmx?WSDL";
    //   public static String NAMESPACE="http://localhost:63048/";




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

        Button botaoLogin = (Button) findViewById(R.id.botaoLogin);
        botaoLogin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        email = campoEmail.getText().toString();
        senha = campoSenha.getText().toString();


        Thread  nt= new Thread(){

            @Override
            public void run(){
                 String res;
                Toast.makeText(Login.this, "EU to aquix", Toast.LENGTH_LONG).show();
                String URL = "http://wcfservice120190319081657.azurewebsites.net/WebService1.asmx";
                String NAMESPACE = "http://apphair.com/";
                String SOAP_ACTION_LOGIN = "http://apphair.com/Cadastro";
                String METHOD_NAME_LOGIN = "Cadastro";

                SoapObject request = new SoapObject(NAMESPACE,METHOD_NAME_LOGIN);
                request.addProperty("email", email);
                request.addProperty("senha", senha);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet=true;

                envelope.setOutputSoapObject(request);

                HttpTransportSE transporte = new HttpTransportSE(URL);
                res=null;

                try
                {
                    transporte.call(SOAP_ACTION_LOGIN, envelope);
                    SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                    res = resultado_xml.toString();
                    Toast.makeText(Login.this, "EU to aqui", Toast.LENGTH_LONG).show();
                } catch (HttpResponseException e) {
                    e.printStackTrace();
                    Toast.makeText(Login.this, "EU to aqui2", Toast.LENGTH_LONG).show();
                } catch (XmlPullParserException e) {
                    Toast.makeText(Login.this, "EU to aqui3", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e) {
                    Toast.makeText(Login.this, "EU to aqui4", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

                final String finalRes = res;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Login.this, "EU to aqui", Toast.LENGTH_LONG).show();
                     //   Toast.makeText(Login.this, finalRes, Toast.LENGTH_LONG).show();

                    }
                });


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




}