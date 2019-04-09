package com.example.apphairnew.response;

import com.example.apphairnew.model.ServicoModel;

import java.util.ArrayList;

public class GetServicoResponse {

    private ArrayList<ServicoModel> servicos = new ArrayList<>();

    public ArrayList<ServicoModel> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<ServicoModel> servicos) {
        this.servicos = servicos;
    }
}
