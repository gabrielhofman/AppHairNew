package com.example.apphairnew.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apphairnew.R;
import com.example.apphairnew.Service.ApiService;
import com.example.apphairnew.Util.MaskEditUtil;
import com.example.apphairnew.model.ContatoModel;
import com.example.apphairnew.response.CadContatoResponse;
import com.example.apphairnew.response.GetContatoResponse;
import com.example.apphairnew.web.ApiControler;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroContato extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private EditText campoNomeContato;
    private EditText campoTelContato;
    private EditText campoDataNascContado;
   // private Spinner spinnerSexoContato;
   private Context context;

    private RadioGroup rbSexoContato;
    private RadioGroup rbFreqContato;
    private RadioButton radioBtnSexo;
    private RadioButton radioBtnFreqContato;

    //private Spinner spinnerExpecFreqContato;

    private ImageView fotoContato;
    private String bmFotoContato;
    private static final int PICK_IMAGE = 100;
    Uri fotoUri;

    private Button botaoTirarFoto;
    private Button botaoCarregarFoto;
    private Button botaoCadastroContato;
    private Button botaoCancelarContato;

    private Toolbar toolbar;
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ContatoModel contatoModel;

    private String nomeContato, telContato, nascContato,  sexoContato, freqContato;

    private ApiService service = ApiControler.CreateController();

    private GetContatoResponse contato;
    private boolean alterando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(this);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBar.setTitle("Cadastrar contato");

        campoNomeContato = (EditText) findViewById(R.id.campoNomeContato);
        campoTelContato = (EditText) findViewById(R.id.campoTelefoneContato);


        campoDataNascContado = (EditText)findViewById(R.id.campoDataNascContato);


        rbSexoContato = (RadioGroup) findViewById(R.id.rbSexoContato) ;
        rbFreqContato = (RadioGroup) findViewById(R.id.rbExpFrequencia) ;


        fotoContato = (ImageView) findViewById(R.id.fotoContato);

        campoDataNascContado.addTextChangedListener(MaskEditUtil.mask(campoDataNascContado, MaskEditUtil.FORMAT_DATE));
        campoTelContato.addTextChangedListener(MaskEditUtil.mask(campoTelContato, MaskEditUtil.FORMAT_FONE));

        Button botaoTirarFoto = (Button)findViewById(R.id.botaoTirarFoto);
        this.botaoTirarFoto = botaoTirarFoto;
        botaoTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        Button botaoCarregarFoto = (Button)findViewById(R.id.botaoCarregarFoto);
        this.botaoCarregarFoto = botaoCarregarFoto;
        botaoCarregarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();

            }
        });

        Button botaoCadastroContato = (Button)findViewById(R.id.botaoCadastrarContato);
        this.botaoCadastroContato = botaoCadastroContato;
        botaoCadastroContato.setOnClickListener(this);

        Button botaoCancelarContato = (Button)findViewById(R.id.botaoCancelarContato);
        this.botaoCancelarContato = botaoCancelarContato;
        botaoCancelarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirContato(contato.getId());

            }
        });


        //serv = (GetServicoResponse2)getIntent().getSerializableExtra("serv");
        contato = (GetContatoResponse)getIntent().getSerializableExtra("contato");

        if(contato != null)
        {
            campoNomeContato.setText(contato.getNomeContato());
            campoTelContato.setText(contato.getTelContato());
            campoDataNascContado.setText(contato.getDataNascCont());

           // int idSexo = rbSexoContato.getCheckedRadioButtonId();
            //radioBtnSexo = (RadioButton) findViewById(idSexo);
            //String escolhaSexo = (String) radioBtnSexo.getText();
            //contato.getSexoContato();
            //String sexoDefault = "Feminino";
            if(contato.getSexoContato().equals("Feminino")){
                //radioBtnSexo.findViewById(R.id.radio_feminino).setChecked
                rbSexoContato.check(R.id.radio_feminino);
                Toast.makeText(CadastroContato.this, contato.getSexoContato(), Toast.LENGTH_LONG).show();

            }else{
                rbSexoContato.check(R.id.radio_masculino);
                Toast.makeText(CadastroContato.this, contato.getSexoContato(), Toast.LENGTH_LONG).show();
            }

            //radioBtnSexo.setChecked(Boolean.parseBoolean(contato.getSexoContato()));
            //String freqDefault = "30 dias";
            if(contato.getExpFreqContato().equals("30 dias")){
                rbFreqContato.check(R.id.radio_30_dias);
            }else if(contato.getExpFreqContato().equals("60 dias")){
                rbFreqContato.check(R.id.radio_60_dias);
            }else if(contato.getExpFreqContato().equals("90 dias")){
                rbFreqContato.check(R.id.radio_90_dias);
            }else{
                rbFreqContato.check(R.id.radio_120_dias);
            }


            byte[] decodedString = Base64.decode(contato.getFotoContato(), Base64.DEFAULT);
            // System.out.println(contatoModel.getFotoContato());
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            fotoContato.setImageBitmap(decodedByte);

            this.alterando = true;



        }else{
            this.alterando = false;
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //drawerLayout.closeDrawers();

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

        return false;//
    }

    @Override
    public void onClick(View v) {




        if (v==botaoCadastroContato){

            nomeContato = campoNomeContato.getText().toString();
            telContato = campoTelContato.getText().toString();
            nascContato = campoDataNascContado.getText().toString();

            int idSexo = rbSexoContato.getCheckedRadioButtonId();
            radioBtnSexo = (RadioButton) findViewById(idSexo);
            String escolhaSexo = (String) radioBtnSexo.getText();

            int idFreq = rbFreqContato.getCheckedRadioButtonId();
            radioBtnFreqContato = (RadioButton) findViewById(idFreq);
            String escolhaFreq = (String) radioBtnFreqContato.getText();




            BitmapDrawable drawable = (BitmapDrawable) fotoContato.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,0,bos);
            byte[] bb = bos.toByteArray();
            bmFotoContato = Base64.encodeToString(bb,1);

            if(nomeContato.isEmpty() || telContato.isEmpty() || nascContato.isEmpty()){
                Toast.makeText(CadastroContato.this, "Complete todos os campos", Toast.LENGTH_LONG).show();

            }else {
                ContatoModel contatoModel = new ContatoModel();

                contatoModel.setNomeContato(nomeContato);
                contatoModel.setTelContato(telContato);
                contatoModel.setDataNascCont(nascContato);
                contatoModel.setSexoContato(escolhaSexo);
                contatoModel.setExpFreqContato(escolhaFreq);
                contatoModel.setFotoContato(bmFotoContato);


//g

                if(alterando){


                    contatoModel.setId(this.contato.getId());
                    //service.AltContato(ContatoModel).enqueue(Call<CadContatoResponse>);
                    service.AltContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
                        @Override
                        public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                            String mensagem;
                            if (response.body().isSuccess()){
                                mensagem = "Alteração concluida com sucesso";
                                Intent intent = new Intent(getApplicationContext(), ContatoLista.class);
                                startActivity(intent);

                            }else{
                                mensagem = "Falha no cadastro"+ response.body().getMessage();
                            }
                            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<CadContatoResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });



                }else {

                    service.CadContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
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



                    //  service.CadFotoContato(contatoModel).enqueue(new Callback<CadContatoResponse>() {
                    //    @Override
                    //   public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {
                    //     String mensagem;
                    //    if (response.body().isSuccess()){
                    //        mensagem = "Cadastro efetuado com sucesso" + bmFotoContato;
                    //      Intent intent = new Intent(getApplicationContext(), ContatoLista.class);
                    //       startActivity(intent);
                    //    }else{
                    //        mensagem = "Falha no cadastro"+ bmFotoContato;
                    //   }
                    //    Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_SHORT).show();
                    // }

                    //   @Override
                    //  public void onFailure(Call<CadContatoResponse> call, Throwable t) {
                    //     Toast.makeText(getApplicationContext(), "Houve um erro:" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    //    t.printStackTrace();

                    // }
                    //});

                }









            }
        }

    }

    public void excluirContato(int id) {
        //service.ExcluirContato(id).enqueue(new Callback<CadContatoResponse>() {
           service.ExcluirContato(id).enqueue(new Callback<CadContatoResponse>() {
               @Override
               public void onResponse(Call<CadContatoResponse> call, Response<CadContatoResponse> response) {

               }

               @Override
               public void onFailure(Call<CadContatoResponse> call, Throwable t) {

               }
           });

        Intent intent = new Intent(this, ContatoLista.class);
        startActivity(intent);
    }
    public void tirarFoto(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera,1);



    }

    public void abrirGaleria(){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(galeria, PICK_IMAGE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            fotoUri = data.getData();
            fotoContato.setImageURI(fotoUri);
            if(fotoUri == null) {
                Toast.makeText(getApplicationContext(), "Nulaço", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "VEIO ALGO", Toast.LENGTH_SHORT).show();
            }

        } else {
            if (resultCode == RESULT_OK && requestCode == 1) {
                Bitmap fototemp = (Bitmap) data.getExtras().get("data");
                fotoContato.setImageBitmap(fototemp);
                if(fotoUri == null) {
                    Toast.makeText(getApplicationContext(), "Nulaço", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "VEIO ALGO", Toast.LENGTH_SHORT).show();
                }
            }
        }



    }
}

























