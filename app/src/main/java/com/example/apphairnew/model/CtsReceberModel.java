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
    private int recebContato;
    private String recebObservacao;
    private String tipoPgto;
    private String condPgto;
    private int idAgenda;

    public String getTipoPgto() {
        return tipoPgto;
    }

    public void setTipoPgto(String tipoPgto) {
        this.tipoPgto = tipoPgto;
    }

    public String getCondPgto() {
        return condPgto;
    }

    public void setCondPgto(String condPgto) {
        this.condPgto = condPgto;
    }

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

    public int getRecebContato() {
        return recebContato;
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

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }
}
