package com.example.apphairnew.response;

import java.io.Serializable;

public class GetTotalFluxoResponse implements Serializable {


    public double totalFluxo;

    public double getTotalFluxo() {
        return totalFluxo;
    }

    public void setTotalFluxo(double totalFluxo) {
        this.totalFluxo = totalFluxo;
    }
}
