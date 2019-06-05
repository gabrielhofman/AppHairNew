package com.example.apphairnew.response;

public class LoginResponse {

    private static int idProf;
    private static int idCliente;
    private int id;
    private boolean success;
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getIdProf() {
        return idProf;
    }

    public static void setIdProf(int idProf) {
        LoginResponse.idProf = idProf;
    }

    public static int getIdCliente() {
        return idCliente;
    }

    public static void setIdCliente(int idCliente) {
        LoginResponse.idCliente = idCliente;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
