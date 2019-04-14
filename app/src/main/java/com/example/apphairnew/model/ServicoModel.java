package com.example.apphairnew.model;

public class ServicoModel {



    private int idServico;
    private String nomeServico;
    private String tempoServico;
    private String descServico;
    private Float precoServico;

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getTempoServico(String tempoServico) {
        return this.tempoServico;
    }

    public void setTempoServico(String tempoServico) {
        this.tempoServico = tempoServico;
    }

    public String getDescServico() {
        return descServico;
    }

    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }

    public Float getPrecoServico(float precoServico) {
        return this.precoServico;
    }

    public void setPrecoServico(Float precoServico) {
        this.precoServico = precoServico;
    } //
}
