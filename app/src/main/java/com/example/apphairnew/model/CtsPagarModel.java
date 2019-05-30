package com.example.apphairnew.model;

public class CtsPagarModel {

    private  int idCp;

    public int getIdCp() {
        return idCp;
    }

    public void setIdCp(int idCp) {
        this.idCp = idCp;
    }

    private String pagarVencimento;
    private double pagarValor;
    private int pagarContato;
    private String pagarObservacao;

    public String getPagarVencimento() {
        return pagarVencimento;
    }

    public void setPagarVencimento(String pagarVencimento) {
        this.pagarVencimento = pagarVencimento;
    }

    public double getPagarValor() {
        return pagarValor;
    }

    public void setPagarValor(double pagarValor) {
        this.pagarValor = pagarValor;
    }

    public int getPagarContato() {
        return pagarContato;
    }

    public void setPagarContato(int pagarContato) {
        this.pagarContato = pagarContato;
    }
//
    public String getPagarObservacao() {
        return pagarObservacao;
    }

    public void setPagarObservacao(String pagarObservacao) {
        this.pagarObservacao = pagarObservacao;
    }
}
