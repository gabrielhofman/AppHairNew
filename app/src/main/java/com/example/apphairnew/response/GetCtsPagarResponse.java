package com.example.apphairnew.response;

import java.io.Serializable;

public class GetCtsPagarResponse implements Serializable {

    public int idCp;
    public String pagarVencimento;
    public Float pagarValor;
    public String pagarNomeContato;
    public String pagarObservacao;

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

    public String getPagarNomeContato() {
        return pagarNomeContato;
    }

    public void setPagarNomeContato(String pagarNomeContato) {
        this.pagarNomeContato = pagarNomeContato;
    }

    public String getPagarObservacao() {
        return pagarObservacao;
    }

    public void setPagarObservacao(String pagarObservacao) {
        this.pagarObservacao = pagarObservacao;//maoe
    }
}
