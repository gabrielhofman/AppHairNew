package com.example.apphairnew.response;

import java.io.Serializable;

public class GetCtsReceberResponse implements Serializable {

    public int idCr;
    public String recebVencimento;
    public Float recebValor;
    public int recebContato;
    public String recebObservacao;

    public int getIdCr() {
        return idCr;
    }

    public void setIdCr(int idCr) {
        this.idCr = idCr;
    }

    public String getRecebVencimento() {
        return recebVencimento;
    }

    public void setRecebVencimento(String recebVencimento) {
        this.recebVencimento = recebVencimento;
    }

    public Float getRecebValor() {
        return recebValor;
    }

    public void setRecebValor(Float recebValor) {
        this.recebValor = recebValor;
    }

    public int getRecebContato(int recebContato) {
        return this.recebContato;
    }

    public void setRecebContato(int recebContato) {
        this.recebContato = recebContato;
    }

    public String getRecebObservacao() {
        return recebObservacao;
    }

    public void setRecebObservacao(String recebObservacao) {
        this.recebObservacao = recebObservacao;
    }
}
