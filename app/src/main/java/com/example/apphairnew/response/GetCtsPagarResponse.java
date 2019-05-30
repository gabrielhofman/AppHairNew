package com.example.apphairnew.response;

import java.io.Serializable;

public class GetCtsPagarResponse implements Serializable {

    public int idCp;
    public String pagarVencimento;
    public Float pagarValor;
    public int pagarContato;

    public int getIdCp() {
        return idCp;
    }

    public void setIdCp(int idCp) {
        this.idCp = idCp;
    }

    public String getPagarVencimento() {
        return pagarVencimento;
    }

    public void setPagarVencimento(String pagarVencimento) {
        this.pagarVencimento = pagarVencimento;
    }

    public Float getPagarValor() {
        return pagarValor;
    }

    public void setPagarValor(Float pagarValor) {
        this.pagarValor = pagarValor;
    }

    public int getPagarContato() {
        return pagarContato;
    }

    public void setPagarContato(int pagarContato) {
        this.pagarContato = pagarContato;
    }


}
