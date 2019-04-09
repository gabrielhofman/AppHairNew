package com.example.apphairnew.model;

public class HorarioModel {

    private String nomeContato;
    private String horaInicio;
    private String horaFim;
    private String nomeServico;
    private Float precoServico;

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public Float getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(Float precoServico) {
        this.precoServico = precoServico;
    }
}
