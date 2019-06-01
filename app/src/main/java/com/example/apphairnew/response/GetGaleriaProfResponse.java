package com.example.apphairnew.response;

import java.io.Serializable;

public class GetGaleriaProfResponse implements Serializable {

    public int id;
    public String bmFotoGaleria;
    public int prof_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBmFotoGaleria() {
        return bmFotoGaleria;
    }

    public void setBmFotoGaleria(String bmFotoGaleria) {
        this.bmFotoGaleria = bmFotoGaleria;
    }

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
    }
}
