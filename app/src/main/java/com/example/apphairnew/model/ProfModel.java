package com.example.apphairnew.model;

public class ProfModel {
    private static String id_final_prof;
    private int prof_id;
    private String email;
    private String senha;
    private String nomeEstab;
    private String descEstab;
    private String cidade;
    private String uf;
    private String logradouro;
    private String bairro;
    private String complemento;
    private String numero;
    private String CEP;
    private String bmFotoProfissional;


    public static String getId_final_prof() {
        return id_final_prof;
    }

    public static void setId_final_prof(String id_final_prof) {
        ProfModel.id_final_prof = id_final_prof;
    }

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
    }

    public String getBmFotoProfissional() {
        return bmFotoProfissional;
    }

    public void setBmFotoProfissional(String bmFotoProfissional) {
        this.bmFotoProfissional = bmFotoProfissional;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeEstab() {
        return nomeEstab;
    }

    public void setNomeEstab(String nomeEstab) {
        this.nomeEstab = nomeEstab;
    }

    public String getDescEstab() {
        return descEstab;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setDescEstab(String descEstab) {
        this.descEstab = descEstab;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
}
