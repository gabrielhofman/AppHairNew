package com.example.apphairnew.model;

public class CtsReceberModel {

    private int idCr;

    public int getIdCr() {
        return idCr;
    }

    public void setIdCr(int idCr) {
        this.idCr = idCr;
    }

    private String recebVencimento;
    private double recebValor;
    private String recebNomeContato;
    private String recebObservacao;

    public String getRecebVencimento() {
        return recebVencimento;
    }

    public void setRecebVencimento(String recebVencimento) {
        this.recebVencimento = recebVencimento;
    }

    public double getRecebValor() {
        return recebValor;
    }

    public void setRecebValor(double recebValor) {
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
