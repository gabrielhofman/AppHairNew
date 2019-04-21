package com.example.apphairnew.response;

import java.io.Serializable;

public class GetCtsReceberResponse implements Serializable {

    public int idCr;
    public String recebVencimento;
    public Float recebValor;
    public String recebNomeContato;
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

    public String getRecebNomeContato() {
        return recebNomeContato;
    }

    public void setRecebNomeContato(String recebNomeContato) {
        this.recebNomeContato = recebNomeContato;
    }

    public String getRecebObservacao() {
        return recebObservacao;
    }

    public void setRecebObservacao(String recebObservacao) {
        this.recebObservacao = recebObservacao;
    }
}
