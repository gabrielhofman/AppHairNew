package com.example.apphairnew.ui.cliente;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apphairnew.R;
import com.example.apphairnew.model.ProfModel;
import com.example.apphairnew.model.UsuarioModel;
import com.example.apphairnew.response.GetProfResponse;
import com.example.apphairnew.ui.GaleriaProf;

import java.util.ArrayList;
import java.util.List;

public class DetalheProfCliente extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{


    private TextView labelNomeEstabCliente;
    private TextView labelDescEstabCliente;
    private TextView labelCEPCliente;
    private TextView labelCidadeCliente;
    private TextView labelUFCliente;
    private TextView labelBairroCliente;
    private TextView labelLogradouroCliente;
    private TextView labelNumeroCliente;
    private TextView labelComplementoCliente;

    private ImageView fotoProfissional;
    private String bmFotoProfissional;
    private static final int PICK_IMAGE = 100;
    Uri fotoUri;

    private Button botaoFotosProfCliente;
    private Button botaoMarcarHorarioCliente;

    private LinearLayoutManager linearLayoutManager;
    public List<GetProfResponse> teste = new ArrayList<>();



    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProfModel profModel;

    private GetProfResponse profissional;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_prof_cliente);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setTitle("Detalhes do profissional");

        labelNomeEstabCliente = (TextView) findViewById(R.id.labelNomeEstabDetCliente);
        labelDescEstabCliente = (TextView) findViewById(R.id.labelDescEstabProfDetCliente);
        labelCEPCliente = (TextView) findViewById(R.id.labelCEPProfDetCliente);
        labelCidadeCliente = (TextView) findViewById(R.id.labelCidadeProfDetCliente);
        labelUFCliente = (TextView) findViewById(R.id.labelUFProfDetCliente);
        labelBairroCliente = (TextView) findViewById(R.id.labelBairroProfDetCliente);
        labelLogradouroCliente = (TextView) findViewById(R.id.labelRuaProfDetCliente);
        labelNumeroCliente = (TextView) findViewById(R.id.labelNumeroProfDetCliente);
        labelComplementoCliente = (TextView) findViewById(R.id.labelComplementoProfDetCliente);
        fotoProfissional = (ImageView) findViewById(R.id.fotoProfDetCliente);

        Button botaoFotosProfCliente = (Button) findViewById(R.id.botaoAbrirFotoGaleiraProfDetCliente);
        this.botaoFotosProfCliente = botaoFotosProfCliente;
        botaoFotosProfCliente.setOnClickListener(this);

        Button botaoMarcarHorarioCliente = (Button) findViewById(R.id.botaoMarcarHrProfDetCliente);
        this.botaoMarcarHorarioCliente = botaoMarcarHorarioCliente;
        this.botaoMarcarHorarioCliente.setOnClickListener(this);

        profissional = (GetProfResponse) getIntent().getSerializableExtra("profissional") ;
        this.profModel = new ProfModel();


       // this.modelDetalhe = new GetAgendaDetalhe();

       // this.modelDetalhe.contato = detalhe.getContato();

        //campoHoraInicio.setText(detalhe.horaInicio);

        labelNomeEstabCliente.setText(profissional.nomeEstab);
        labelDescEstabCliente.setText(profissional.descEstab);
        labelCEPCliente.setText(profissional.cep);
        labelCidadeCliente.setText(profissional.cidade);
        labelUFCliente.setText(profissional.uf);
        labelBairroCliente.setText(profissional.bairro);
        labelLogradouroCliente.setText(profissional.logradouro);
        labelNumeroCliente.setText(profissional.numero);
        labelComplementoCliente.setText(profissional.complemento);

        byte[] decodedString = Base64.decode(profissional.getBmFotoProfissional(), Base64.DEFAULT);
        // System.out.println(contatoModel.getFotoContato());
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        fotoProfissional.setImageBitmap(decodedByte);


    }

    @Override
    public void onClick(View v) {

        if(v== botaoFotosProfCliente){
            Intent addGaleria = new Intent(this, GaleriaProf.class);
            startActivity(addGaleria);
        }

        if(v == botaoMarcarHorarioCliente)
        {
            Intent intent = new Intent(this, HorariosSalaoCliente.class);
            intent.putExtra("profissional", profissional);
            startActivity(intent);

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
