package com.example.apphairnew.model;

import java.util.Date;

public class HorarioModel {

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    private int idAgenda;
    private int contato;
    private String dataAgenda;
    private String horaInicio;
    private String horaFim;
    private int servico;
    private double precoServico;
    private String statusAgenda;
    private int idCliente;


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getStatusAgenda() {
        return statusAgenda;
    }

    public void setStatusAgenda(String statusAgenda) {
        this.statusAgenda = statusAgenda;
    }




    public int getContato() {
        return contato;
    }

    public void setContato(int contato) {
        this.contato = contato;
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

    public int getServico() {
        return servico;
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public double getPrecoServico() {
        return precoServico;
    }

    public void setPrecoServico(double precoServico) {
        this.precoServico = precoServico;
    }


    public String getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(String dataAgenda) {
        this.dataAgenda = dataAgenda;
    }
}
