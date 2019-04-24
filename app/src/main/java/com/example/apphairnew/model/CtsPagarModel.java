package com.example.apphairnew.model;

public class CtsPagarModel {

    private  int idCp;
    private String pagarVencimento;
    private double pagarValor;
    private String pagarNomeContato;
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
        this.pagarObservacao = pagarObservacao;
    }
}
