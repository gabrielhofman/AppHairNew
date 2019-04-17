package com.example.apphairnew.response;

import java.io.Serializable;

public class GetContatoResponse implements Serializable {

    public int id;
    public String nomeContato;
    public String telContato;
    public String dataNascCont;
    public String obsContato;
    public String sexoContato;
    public String expFreqContato;

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
}
