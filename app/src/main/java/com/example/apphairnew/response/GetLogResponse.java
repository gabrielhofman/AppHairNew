package com.example.apphairnew.response;

public class GetLogResponse {

    private String nomeCliente;
    private String sobrenomeCliente ;
    private String clienteTelef ;
    private String horaInicioAgenda;
    private String horaFimAgenda;
    private String dataAgenda ;
    private String dataLog;
    private String tipoLog;


    public String getTipoLog() {
        return tipoLog;
    }

    public void setTipoLog(String tipoLog) {
        this.tipoLog = tipoLog;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getClienteTelef() {
        return clienteTelef;
    }

    public void setClienteTelef(String clienteTelef) {
        this.clienteTelef = clienteTelef;
    }

    public String getHoraInicioAgenda() {
        return horaInicioAgenda;
    }

    public void setHoraInicioAgenda(String horaInicioAgenda) {
        this.horaInicioAgenda = horaInicioAgenda;
    }

    public String getHoraFimAgenda() {
        return horaFimAgenda;
    }

    public void setHoraFimAgenda(String horaFimAgenda) {
        this.horaFimAgenda = horaFimAgenda;
    }

    public String getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(String dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public String getDataLog() {
        return dataLog;
    }

    public void setDataLog(String dataLog) {
        this.dataLog = dataLog;
    }
}
