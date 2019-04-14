package com.example.apphairnew.response;

import java.io.Serializable;

public class GetServicoResponse2 implements Serializable {

    public int idServico;
    public String nomeServico;
    public float tempoServico;
    public String descServico;
    public float precoServico;

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int id) {
        this.idServico = id;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public float getTempoServico() {
        return tempoServico;
    }

    public void setTempoServico(float tempoServico) {
        this.tempoServico = tempoServico;
    }

    public String getDescServico() {
        return descServico;
    }

    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }

    public float getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(float precoServico) {
        this.precoServico = precoServico;
    }
}
