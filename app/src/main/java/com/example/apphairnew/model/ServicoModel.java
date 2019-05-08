package com.example.apphairnew.model;

public class ServicoModel {



    private int idServico;
    private String nomeServico;
 //   private String tempoServico;
    private String descServico;
    private double precoServico;

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

       public String getDescServico() {
        return descServico;
    }

    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }

    public double getPrecoServico(double precoServico) {
        return this.precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    } //
}
