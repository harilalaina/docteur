package org.etu.docteur.aretina;

import org.etu.docteur.patient.ParametrePatient;

import java.util.List;

public class Aretina {
    int idAretina;
    String nomAretina;
    List<ParametreAretina> parametreAretinaList;

    public Aretina(int idAretina, String nomAretina) {
        setIdAretina(idAretina);
        setNomAretina(nomAretina);
    }

    public int getIdAretina() {
        return idAretina;
    }

    public void setIdAretina(int idAretina) {
        this.idAretina = idAretina;
    }

    public String getNomAretina() {
        return nomAretina;
    }

    public void setNomAretina(String nomAretina) {
        this.nomAretina = nomAretina;
    }

    public List<ParametreAretina> getParametreAretinaList() {
        return parametreAretinaList;
    }

    public void setParametreAretinaList(List<ParametreAretina> parametreAretinaList) {
        this.parametreAretinaList = parametreAretinaList;
    }
}
