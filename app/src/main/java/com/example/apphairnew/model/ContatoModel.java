package com.example.apphairnew.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import com.google.gson.annotations.JsonAdapter;

public class ContatoModel {

    private int id;
    private String nomeContato;
    private String telContato;
    private String dataNascCont;
    private String obsContato;
    private String sexoContato;
    private String expFreqContato;
    private String bmFotoContato;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getTelContato() {
        return telContato;
    }

    public void setTelContato(String telContato) {
        this.telContato = telContato;
    }

    public String getDataNascCont() {
        return dataNascCont;
    }

    public void setDataNascCont(String dataNascCont) {
        this.dataNascCont = dataNascCont;
    }

    public String getObsContato() {
        return obsContato;
    }

    public void setObsContato(String obsContato) {
        this.obsContato = obsContato;
    }

    public String getSexoContato() {
        return sexoContato;
    }

    public void setSexoContato(String sexoContato) {
        this.sexoContato = sexoContato;
    }

    public String getExpFreqContato() {
        return expFreqContato;
    }

    public void setExpFreqContato(String expFreqContato) {
        this.expFreqContato = expFreqContato;
    }

    public String getFotoContato() {
        return bmFotoContato;
    }

    public void setFotoContato(String bmFotoContato) {
        this.bmFotoContato = bmFotoContato;
    }
}
