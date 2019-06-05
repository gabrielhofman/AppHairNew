package com.example.apphairnew.model.cliente;

public class LogModel {



    private int idLog;
    private int clienteId;
    private int AgendaId;
    private String logStatus;
    private int profId;



    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getAgendaId() {
        return AgendaId;
    }

    public void setAgendaId(int agendaId) {
        AgendaId = agendaId;
    }

    public String getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(String logStatus) {
        this.logStatus = logStatus;
    }

    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }
}
