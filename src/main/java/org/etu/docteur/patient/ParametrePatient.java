package org.etu.docteur.patient;

import org.etu.docteur.aretina.Parametre;
import org.etu.docteur.aretina.ParametreAretina;

public class ParametrePatient extends Parametre {
    int niveauParametrePatient;

    public ParametrePatient(int idParametre, String nomParametre, int niveauParametrePatient) {
        super(idParametre, nomParametre);
        setNiveauParametrePatient(niveauParametrePatient);
    }

    public int getNiveauParametrePatient() {
        return niveauParametrePatient;
    }

    public void setNiveauParametrePatient(int niveauParametrePatient) {
        this.niveauParametrePatient = niveauParametrePatient;
    }
}
