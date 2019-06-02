package com.example.apphairnew.response;

import java.io.Serializable;

public class GetProfResponse implements Serializable {

    public int prof_id;
    public String email;
    public String senha;
    public String nomeEstab;
    public String descEstab;
    public String cidade;
    public String uf;
    public String logradouro;
    public String bairro;
    public String complemento;
    public String numero;
    public String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public String bmFotoProfissional;

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
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

    public void setDescEstab(String descEstab) {
        this.descEstab = descEstab;
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



    public String getBmFotoProfissional() {
        return bmFotoProfissional;
    }

    public void setBmFotoProfissional(String bmFotoProfissional) {
        this.bmFotoProfissional = bmFotoProfissional;
    }
}
