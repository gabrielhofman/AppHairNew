package com.example.apphairnew.model;

import java.io.Serializable;

public class GetAgendaDetalhe implements Serializable {

    public int contato;
    public int servico;
    public double valorServico;

    public int getContato() {
        return contato;
    }

    public void setContato(int contato) {
        this.contato = contato;
    }

    public int getServico() {
        return servico;
    }

    public void setServico(int servico) {
        this.servico = servico;
    }

    public double isValorServico() {
        return valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }
}
