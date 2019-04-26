package com.example.apphairnew.response;

import java.io.Serializable;

public class GetHorarioResponse implements Serializable {

    public int idHorario;
    public String nomeContato;
    public String horaInicio;
    public String horaFim;
    public String nomeServico;
    public Float precoServico;

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

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
