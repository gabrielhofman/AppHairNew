package com.example.apphairnew.model;

public class CtsReceberModel {

    private int idCr;
    private String recebVencimento;
    private Float recebValor;
    private String recebNomeContato;
    private String recebObservacao;

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
